resolvers ++= Resolver.sonatypeOssRepos("public")
resolvers ++= Resolver.sonatypeOssRepos("snapshots")

inThisBuild(
  Seq(
    scalaVersion := "3.3.0", // Also update docs/publishWebsite.sh and any ref to scala-3.3.0
  )
)

/** Versions */
lazy val V = new {
  val scalaDID = "0.1.0-M5"
//   val scalajsJavaSecureRandom = "1.0.0"

  // FIXME another bug in the test framework https://github.com/scalameta/munit/issues/554
  val munit = "1.0.0-M8" // "0.7.29"

//   // https://mvnrepository.com/artifact/org.scala-js/scalajs-dom
//   val scalajsDom = "2.4.0"
//   // val scalajsLogging = "1.1.2-SNAPSHOT" //"1.1.2"

//   // https://mvnrepository.com/artifact/dev.zio/zio
  val zio = "2.0.13"
  val zioJson = "0.4.2"
  // val zioMunitTest = "0.1.1"
  val zioHttp = "0.0.5"
  val zioConfig = "4.0.0-RC16"
  val zioLogging = "2.1.13"
  val zioSl4j = "2.1.13"
  val logback = "1.3.8"
  val logstash = "7.4"
  val jansi = "2.4.0"
  val mongo = "1.1.0-RC10"
  val embedMongo = "4.7.0"
  val munitZio = "0.1.1"
  val zioTest = "2.0.15"
  val zioTestSbt = "2.0.15"
  val zioTestMagnolia = "2.0.15"

  // For WEBAPP
  val laminar = "16.0.0"
  val waypoint = "7.0.0"
  val upickle = "3.1.0"
  // https://www.npmjs.com/package/material-components-web
  val materialComponents = "12.0.0"
}

/** Dependencies */
lazy val D = new {
  val scalaDID = Def.setting("app.fmgp" %%% "did" % V.scalaDID)
  val scalaDID_imp = Def.setting("app.fmgp" %%% "did-imp" % V.scalaDID)
  val scalaDID_peer = Def.setting("app.fmgp" %%% "did-method-peer" % V.scalaDID)

//   /** The [[java.security.SecureRandom]] is used by the [[java.util.UUID.randomUUID()]] method in [[MsgId]].
//     *
//     * See more https://github.com/scala-js/scala-js-java-securerandom
//     */
//   val scalajsJavaSecureRandom = Def.setting(
//     ("org.scala-js" %%% "scalajs-java-securerandom" % V.scalajsJavaSecureRandom)
//       .cross(CrossVersion.for3Use2_13)
//   )

//   val dom = Def.setting("org.scala-js" %%% "scalajs-dom" % V.scalajsDom)

  val zio = Def.setting("dev.zio" %%% "zio" % V.zio)
//   val zioStreams = Def.setting("dev.zio" %%% "zio-streams" % V.zio)
  val zioJson = Def.setting("dev.zio" %%% "zio-json" % V.zioJson)

  val zioHttp = Def.setting("dev.zio" %% "zio-http" % V.zioHttp)
  val zioConfig = Def.setting("dev.zio" %% "zio-config" % V.zioConfig)
  val zioConfigMagnolia = Def.setting("dev.zio" %% "zio-config-magnolia" % V.zioConfig) // For deriveConfig
  val zioConfigTypesafe = Def.setting("dev.zio" %% "zio-config-typesafe" % V.zioConfig) // For HOCON
  val zioLogging = Def.setting("dev.zio" %% "zio-logging" % V.zioLogging)
  val zioLoggingSl4j = Def.setting("dev.zio" %% "zio-logging-slf4j" % V.zioSl4j)
  val logback = Def.setting("ch.qos.logback" % "logback-classic" % V.logback)
  val logstash = Def.setting("net.logstash.logback" % "logstash-logback-encoder" % V.logstash)

  val jansi = Def.setting("org.fusesource.jansi" % "jansi" % V.jansi)

  val mongo = Def.setting("org.reactivemongo" %% "reactivemongo" % V.mongo)
//   // For munit https://scalameta.org/munit/docs/getting-started.html#scalajs-setup
  val munit = Def.setting("org.scalameta" %%% "munit" % V.munit % Test)
  val embedMongo = Def.setting("de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % V.embedMongo % Test)
  // For munit zio https://github.com/poslegm/munit-zio
  val munitZio = Def.setting("com.github.poslegm" %% "munit-zio" % V.munitZio % Test)
  val zioTest = Def.setting("dev.zio" %% "zio-test" % V.zioTest % Test)
  val zioTestSbt = Def.setting("dev.zio" %% "zio-test-sbt" % V.zioTestSbt % Test)
  val zioTestMagnolia = Def.setting("dev.zio" %% "zio-test-magnolia" % V.zioTestMagnolia % Test)

  // For WEBAPP
  val laminar = Def.setting("com.raquo" %%% "laminar" % V.laminar)
  val waypoint = Def.setting("com.raquo" %%% "waypoint" % V.waypoint)
  val upickle = Def.setting("com.lihaoyi" %%% "upickle" % V.upickle)
}

/** NPM Dependencies */
lazy val NPM = new {
  val qrcode = Seq("qrcode-generator" -> "1.4.4")

  val materialDesign = Seq("material-components-web" -> V.materialComponents)

  val sha1 = Seq("js-sha1" -> "0.6.0", "@types/js-sha1" -> "0.6.0")
  val sha256 = Seq("js-sha256" -> "0.9.0")
}

inThisBuild(
  Seq(
    scalacOptions ++= Seq(
      // ### https://docs.scala-lang.org/scala3/guides/migration/options-new.html
      // ### https://docs.scala-lang.org/scala3/guides/migration/options-lookup.html
      //
      "-encoding", // if an option takes an arg, supply it on the same line
      "UTF-8", // source files are in UTF-8
      "-deprecation", // warn about use of deprecated APIs
      "-unchecked", // warn about unchecked type parameters
      "-feature", // warn about misused language features (Note we are using 'language:implicitConversions')
      "-Xfatal-warnings",
      // TODO "-Yexplicit-nulls",
      // "-Ysafe-init", // https://dotty.epfl.ch/docs/reference/other-new-features/safe-initialization.html
      "-language:implicitConversions", // we can use with the flag '-feature'
      // NO NEED ATM "-language:reflectiveCalls",
      // "-Xprint-diff",
      // "-Xprint-diff-del",
      // "-Xprint-inline",
      // NO NEED ATM "-Xsemanticdb"
      // NO NEED ATM "-Ykind-projector"
    ),

    // ### commonSettings ###
    // Compile / doc / sources := Nil,
    // ### setupTestConfig ### //lazy val settingsFlags: Seq[sbt.Def.SettingsDefinition] = ???
    // libraryDependencies += D.munit.value, // BUG? "JS's Tests does not stop"
  )
)

lazy val setupTestConfig: Seq[sbt.Def.SettingsDefinition] = Seq(
  libraryDependencies += D.munit.value,
)

lazy val scalaJSBundlerConfigure: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      scalaJSLinkerConfig ~= {
        _.withSourceMap(false) // disabled because it somehow triggers warnings and errors
          .withModuleKind(ModuleKind.CommonJSModule) // ModuleKind.ESModule
        // must be set to ModuleKind.CommonJSModule in projects where ScalaJSBundler plugin is enabled
        // .withJSHeader(
        //   """/* FMGP scala-did examples and tool
        //   | * https://github.com/FabioPinheiro/scala-did
        //   | * Copyright: Fabio Pinheiro - fabiomgpinheiro@gmail.com
        //   | */""".stripMargin.trim() + "\n"
        // )
      }
    )
    // .settings( //TODO https://scalacenter.github.io/scalajs-bundler/reference.html#jsdom
    //   //jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    //   //Test / requireJsDomEnv := true)
    // )
    .enablePlugins(ScalablyTypedConverterPlugin)
    .settings(
      // Compile / fastOptJS / webpackExtraArgs += "--mode=development",
      // Compile / fullOptJS / webpackExtraArgs += "--mode=production",
      Compile / fastOptJS / webpackDevServerExtraArgs += "--mode=development",
      Compile / fullOptJS / webpackDevServerExtraArgs += "--mode=production",
      useYarn := true
    )

lazy val buildInfoConfigure: Project => Project = _.enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoPackage := "io.iohk.atala.mediator",
    // buildInfoObject := "BuildInfo",
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      scalaVersion,
      sbtVersion,
      BuildInfoKey.action("buildTime") { System.currentTimeMillis }, // re-computed each time at compile
    ),
  )

lazy val httpUtils = crossProject(JSPlatform, JVMPlatform) // project
  .in(file("http-utils"))
  .settings(publish / skip := true)
  .settings((setupTestConfig): _*)
  .settings(
    libraryDependencies += D.scalaDID.value,
  )
  .jsConfigure(scalaJSBundlerConfigure)
  .jsSettings(Compile / npmDependencies ++= NPM.sha1 ++ NPM.sha256)
  .jvmSettings(
    libraryDependencies += D.zioHttp.value,
  )

lazy val mediator = project
  .in(file("mediator"))
  .settings(publish / skip := true)
  .settings(
    // FIX TODO (maybe the next version of the library will hide this compilation error)
    // [error] -- Error: api/target/shaded/scala-3.2.2/src_managed/main/velocity/DefaultBSONHandlers.scala:379:2
    // [error] undefined: new com.github.ghik.silencer.silent # -1: TermRef(TypeRef(TermRef(TermRef(TermRef(TermRef(ThisType(TypeRef(NoPrefix,module class <root>)),object com),object github),object ghik),object silencer),silent),<init>) at readTasty
    // [error] one error found
    // [error] (mediator / Compile / doc) DottyDoc Compilation Failed
    Compile / doc / sources := Seq.empty
  )
  .settings((setupTestConfig): _*)
  .settings(
    libraryDependencies += D.scalaDID_imp.value,
    libraryDependencies += D.scalaDID_peer.value,
    libraryDependencies += D.zioHttp.value,
    libraryDependencies ++= Seq(
      D.zioConfig.value,
      D.zioConfigMagnolia.value,
      D.zioConfigTypesafe.value,
      D.zioLogging.value,
      D.zioLoggingSl4j.value,
      D.logback.value,
      D.jansi.value,
      D.logstash.value,
    ),
    libraryDependencies += D.mongo.value,
    libraryDependencies ++= Seq(
      D.munit.value,
      D.embedMongo.value,
      D.zioTest.value,
      D.zioTestSbt.value,
      D.zioTestMagnolia.value,
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
  .settings(
    Compile / mainClass := Some("io.iohk.atala.mediator.app.MediatorStandalone"),
    Docker / maintainer := "atala-coredid@iohk.io",
    Docker / dockerUsername := Some("input-output-hk"),
    Docker / dockerRepository := Some("ghcr.io"),
    dockerExposedPorts := Seq(8080),
    dockerBaseImage := "openjdk:11",
  )
  .settings(Test / parallelExecution := false)
  .settings(
    // WebScalaJSBundlerPlugin
    scalaJSProjects := Seq(webapp),
    /** scalaJSPipeline task runs scalaJSDev when isDevMode is true, runs scalaJSProd otherwise. scalaJSProd task runs
      * all tasks for production, including Scala.js fullOptJS task and source maps scalaJSDev task runs all tasks for
      * development, including Scala.js fastOptJS task and source maps.
      */
    Assets / pipelineStages := Seq(scalaJSPipeline, gzip),
    // pipelineStages ++= Seq(digest, gzip), //Compression - If you serve your Scala.js application from a web server, you should additionally gzip the resulting .js files.
    Compile / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "extra-resources",
    // Compile / unmanagedResourceDirectories += (baseDirectory.value.toPath.getParent.getParent / "docs-build" / "target" / "mdoc").toFile,
    // Compile / unmanagedResourceDirectories += (baseDirectory.value.toPath.getParent.getParent / "serviceworker" / "target" / "scala-3.3.0" / "fmgp-serviceworker-fastopt").toFile,
    Compile / compile := ((Compile / compile) dependsOn scalaJSPipeline).value,
    // Frontend dependency configuration
    Assets / WebKeys.packagePrefix := "public/",
    Runtime / managedClasspath += (Assets / packageBin).value,
  )
  .enablePlugins(WebScalaJSBundlerPlugin)
  .dependsOn(httpUtils.jvm) // did, didExample,
  .enablePlugins(JavaAppPackaging, DockerPlugin)

lazy val webapp = project
  .in(file("webapp"))
  .settings(publish / skip := true)
  .settings(Test / test := {})
  .settings(name := "webapp")
  .configure(scalaJSBundlerConfigure)
  .configure(buildInfoConfigure)
  .settings(
    libraryDependencies ++= Seq(D.laminar.value, D.waypoint.value, D.upickle.value),
    libraryDependencies ++= Seq(D.zio.value, D.zioJson.value),
    libraryDependencies ++= Seq(D.scalaDID.value, D.scalaDID_peer.value),
    Compile / npmDependencies ++= NPM.qrcode ++ NPM.materialDesign ++ NPM.sha1 ++ NPM.sha256,
  )
  .settings(
    stShortModuleNames := true,
    webpackBundlingMode := BundlingMode.LibraryAndApplication(), // BundlingMode.Application,
    Compile / scalaJSModuleInitializers += {
      org.scalajs.linker.interface.ModuleInitializer.mainMethod("io.iohk.atala.mediator.App", "main")
    },
  )

// ############################
// ####  Release process  #####
// ############################
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations.*
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  ReleaseStep(releaseStepTask(mediator / Docker / stage)),
  setNextVersion
)
