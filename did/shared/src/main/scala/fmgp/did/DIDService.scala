package fmgp.did

import zio.json._
import scala.util.chaining._

/** DIDService
  *
  * https://w3c.github.io/did-core/#service-properties
  *
  * https://w3c.github.io/did-core/#services
  */
trait DIDService extends DID {
  def id: Required[URI]
  def `type`: Required[SetU[String]]
  def serviceEndpoint: Required[SetU[URI]] // FIXME or MAP ???
}

trait DIDServiceForDIDPeer extends DIDService {
  def routingKeys: NotRequired[Set[String]]
  def accept: NotRequired[Set[String]]
}

object DIDService {
  given decoder: JsonDecoder[DIDService] =
    DIDServiceClass.decoder.map(e => e)
  given encoder: JsonEncoder[DIDService] =
    DIDServiceClass.encoder.contramap(e =>
      e match {
        case a: DIDServiceClass => a
        case other: DIDService =>
          DIDServiceClass(
            id = e.id,
            `type` = e.`type`,
            serviceEndpoint = e.serviceEndpoint,
          )
      }
    )
}

final case class DIDServiceClass(
    id: Required[URI],
    `type`: Required[SetU[String]],
    serviceEndpoint: Required[SetU[URI]], // FIXME or MAP ???

    // extra for did Peer
    routingKeys: NotRequired[Set[String]] = None,
    accept: NotRequired[Set[String]] = None,
) extends DIDService
    with DIDServiceForDIDPeer {
  // val (namespace, specificId) = DID.getNamespaceAndSpecificId(id)
  val (namespace, specificId) = DIDSubject(id).pipe(did => (did.namespace, did.specificId))
}
object DIDServiceClass {
  import SetU.{given}
  implicit val decoder: JsonDecoder[DIDServiceClass] = DeriveJsonDecoder.gen[DIDServiceClass]
  implicit val encoder: JsonEncoder[DIDServiceClass] = DeriveJsonEncoder.gen[DIDServiceClass]
}
