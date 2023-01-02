package fmgp.did.demo

import zio._
import zio.json._
import zio.stream._
import zio.http._
import zio.http.model._
import zio.http.socket._

import fmgp.did._
import fmgp.crypto.error._
import fmgp.did.comm._

import scala.io.Source

/** demoJVM/runMain fmgp.did.demo.AppServer
  *
  * curl localhost:8080/hello
  *
  * wscat -c ws://localhost:8080/ws
  *
  * curl -X POST localhost:8080 -H 'content-type: application/didcomm-encrypted+json' -d '{}'
  *
  * curl
  * localhost:8080/resolver/did:peer:2.Ez6LSq12DePnP5rSzuuy2HDNyVshdraAbKzywSBq6KweFZ3WH.Vz6MksEtp5uusk11aUuwRHzdwfTxJBUaKaUVVXwFSVsmUkxKF.SeyJ0IjoiZG0iLCJzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6OTA5My8iLCJyIjpbXSwiYSI6WyJkaWRjb21tL3YyIl19
  */
object AppServer extends ZIOAppDefault {

  val app: Http[Hub[String] & Ref[DIDSocketManager], Throwable, Request, Response] = Http
    .collectZIO[Request] {
      case Method.GET -> !! / "hello" => ZIO.succeed(Response.text("Hello World! DEMO DID APP")).debug
      case req @ Method.GET -> !! / "socket" =>
        for {
          sm <- ZIO.service[Ref[DIDSocketManager]].flatMap(_.get)
          ret <- ZIO.succeed(Response.text(sm.toJsonPretty))
        } yield (ret)
      case req @ Method.POST -> !! / "socket" / id =>
        for {
          hub <- ZIO.service[Hub[String]]
          sm <- ZIO.service[Ref[DIDSocketManager]].flatMap(_.get)
          ret <- sm.ids
            .get(DIDSubject(id))
            .toSeq
            .flatMap { socketsID =>
              socketsID.flatMap(id => sm.sockets.get(id).map(e => (id, e))).toSeq
            } match {
            case Seq() =>
              req.body.asString.flatMap(e => hub.publish(s"socket missing for $id"))
                *> ZIO.succeed(Response.text(s"socket missing"))
            case seq =>
              ZIO.foreach(seq) { (socketID, channel) =>
                req.body.asString.flatMap(e => channel.socketOutHub.publish(e))
              } *> ZIO.succeed(Response.text(s"message sended"))
          }
        } yield (ret)
      case req @ Method.GET -> !! / "headers" =>
        val data = req.headersAsList.toSeq.map(e => (e.key.toString(), e.value.toString()))
        ZIO.succeed(Response.text("HEADERS:\n" + data.mkString("\n"))).debug
      case Method.GET -> !! / "ws" => DIDSocketManager.socketApp.toResponse
      case req @ Method.POST -> !! if req.headersAsList.exists { h =>
            h.key == "content-type" &&
            (h.value == MediaTypes.SIGNED || h.value == MediaTypes.ENCRYPTED.typ)
          } =>
        for {
          data <- req.body.asString
          msg <- ZIO.fromEither(
            data
              .fromJson[Message]
              .left
              .map(error => DidException(FailToParse(error)))
          )
          _ <- ZIO.log(msg.toJsonPretty)
        } yield Response.text(msg.toJson)

      case Method.POST -> !! =>
        ZIO
          .succeed(Response.text(s"The content-type must be ${MediaTypes.SIGNED.typ} and ${MediaTypes.ENCRYPTED.typ}"))
          .debug
      case req @ Method.POST -> !! / "ops" =>
        req.body.asString
          .flatMap(e => OperationsServerRPC.ops(e))
          .map(e => Response.text(e))
          .debug
      case Method.GET -> !! / "resolver" / did =>
        DIDSubject.either(did) match
          case Left(error)  => ZIO.succeed(Response.text(error.error).setStatus(Status.BadRequest)).debug
          case Right(value) => ZIO.succeed(Response.text("DID:" + value)).debug
      case req @ Method.GET -> !! => { // html.Html.fromDomElement()
        val data = Source.fromResource(s"public/index.html").mkString("")
        ZIO.succeed(Response.html(data)).debug
      }
    }
    ++ {
      Http.fromResource(s"public/fmgp-webapp-fastopt-bundle.js").when {
        case Method.GET -> !! / "public" / path => true
        // Response(
        //   body = Body.fromStream(ZStream.fromIterator(Source.fromResource(s"public/$path").iter).map(_.toByte)),
        //   headers = Headers(HeaderNames.contentType, HeaderValues.applicationJson),
        // )
        case _ => false
      }
    }

  override val run = for {
    _ <- Console.printLine(
      """██████╗ ██╗██████╗     ██████╗ ███████╗███╗   ███╗ ██████╗ 
        |██╔══██╗██║██╔══██╗    ██╔══██╗██╔════╝████╗ ████║██╔═══██╗
        |██║  ██║██║██║  ██║    ██║  ██║█████╗  ██╔████╔██║██║   ██║
        |██║  ██║██║██║  ██║    ██║  ██║██╔══╝  ██║╚██╔╝██║██║   ██║
        |██████╔╝██║██████╔╝    ██████╔╝███████╗██║ ╚═╝ ██║╚██████╔╝
        |╚═════╝ ╚═╝╚═════╝     ╚═════╝ ╚══════╝╚═╝     ╚═╝ ╚═════╝ 
        |Yet another server simpler server to demo DID Comm v2.
        |Vist: https://github.com/FabioPinheiro/scala-did""".stripMargin
    )
    myHub <- Hub.sliding[String](5)
    _ <- ZStream.fromHub(myHub).run(ZSink.foreach((str: String) => Console.printLine("HUB: " + str))).fork
    port <- System
      .property("PORT")
      .flatMap {
        case None        => System.property("port")
        case Some(value) => ZIO.succeed(Some(value))
      }
      .map(_.flatMap(_.toIntOption).getOrElse(8080))

    _ <- Console.printLine(s"Starting server on port: $port")
    server = {
      val config = ServerConfig(address = new java.net.InetSocketAddress(port))
      ServerConfig.live(config)(using Trace.empty) >>> Server.live
    }

    inboundHub <- Hub.bounded[String](5)
    ref <- Ref.make(DIDSocketManager("alice.did.fmgp.app"))
    myServer <- Server
      .serve(app)
      // .provideSomeLayer(DIDSocketManager.aliceLayer)
      .provideSomeEnvironment { (env: ZEnvironment[Server]) => env.add(myHub).add(ref) }
      .provide(server)
      .fork
    _ <- Console.printLine(s"Server Started")
    _ <- myServer.join *> Console.printLine(s"Server End")
  } yield ()

}
