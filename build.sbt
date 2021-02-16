name := "rt-solar-cats-effect"

version := "1.0.0"

scalaVersion := Dependencies.scalaVersion

libraryDependencies ++= Dependencies.All

scalacOptions ++= List(
"-unchecked",
"-deprecation",
"-encoding",
"UTF8",
"-feature",
"-language:higherKinds"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "maven", "org.webjars", "swagger-ui", "pom.properties") =>
    MergeStrategy.singleOrError
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
