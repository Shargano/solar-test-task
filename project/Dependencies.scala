import sbt._

object Dependencies {

  val scalaVersion = "2.13.4"

  val pureConfigVersion     = "0.12.3"
  val typesafeConfigVersion = "1.4.0"

  val logbackVersion      = "1.2.3"
  val scalaLoggingVersion = "3.9.2"

  val sttpVersion  = "2.1.5"
  val tapirVersion = "0.12.23"

  val circeVersion = "0.13.0"

  val catsVersion = "2.1.1"

  val fs2Version = "2.2.2"

  val pureConfig     = "com.github.pureconfig" %% "pureconfig" % pureConfigVersion
  val typesafeConfig = "com.typesafe"           % "config"     % typesafeConfigVersion

  val logback      = "ch.qos.logback"              % "logback-classic" % logbackVersion
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"   % scalaLoggingVersion

  val tapirCore       = "com.softwaremill.sttp.tapir"  %% "tapir-core"                     % tapirVersion
  val tapirDocs       = "com.softwaremill.sttp.tapir"  %% "tapir-openapi-docs"             % tapirVersion
  val tapirJsonCirce  = "com.softwaremill.sttp.tapir"  %% "tapir-json-circe"               % tapirVersion
  val tapirYamlCirce  = "com.softwaremill.sttp.tapir"  %% "tapir-openapi-circe-yaml"       % tapirVersion
  val tapirSttpClient = "com.softwaremill.sttp.tapir"  %% "tapir-sttp-client"              % tapirVersion
  val tapirHttp4s     = "com.softwaremill.sttp.tapir"  %% "tapir-http4s-server"            % tapirVersion
  val tapirHttp4sDocs = "com.softwaremill.sttp.tapir"  %% "tapir-swagger-ui-http4s"        % tapirVersion
  val sttpCatsEffect  = "com.softwaremill.sttp.client" %% "async-http-client-backend-cats" % sttpVersion

  val circeCore    = "io.circe" %% "circe-core"    % circeVersion
  val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  val circeParser  = "io.circe" %% "circe-parser"  % circeVersion

  val cats = "org.typelevel" %% "cats-core" % catsVersion

  val fs2Reactive = "co.fs2" %% "fs2-reactive-streams" % fs2Version
  val fs2IO       = "co.fs2" %% "fs2-io"               % fs2Version

  val configDependencies = Seq(
    pureConfig,
    typesafeConfig
  )

  val loggingDependencies = Seq(
    logback,
    scalaLogging
  )

  val tapirDependencies = Seq(
    tapirCore,
    tapirDocs,
    tapirHttp4s,
    tapirHttp4sDocs,
    tapirJsonCirce,
    tapirYamlCirce,
    sttpCatsEffect,
    tapirSttpClient
  )

  val circeDependencies = Seq(
    circeCore,
    circeGeneric,
    circeParser
  )

  val utilsDependencies = Seq(cats, fs2Reactive, fs2IO)

  val All: Seq[ModuleID] = configDependencies ++
    loggingDependencies ++
    tapirDependencies ++
    circeDependencies ++
    utilsDependencies
}
