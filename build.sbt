
sbtPlugin := true

organization := "com.bicou.sbt"

name := "sbt-hbs"

scalaVersion := "2.12.8"

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "org.webjars" % "mkdirp" % "0.5.0"
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.2.3")

publishMavenStyle := false

bintrayOrganization := None
bintrayRepository := "sbt-plugins"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))


scriptedLaunchOpts := { 
  Seq("-Dproject.version=" + version.value)
}

scriptedBufferLog := false


