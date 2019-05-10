import bintray.Keys._

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

bintraySettings

repository in bintray := "sbt-plugins"

bintrayOrganization in bintray := None

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scriptedSettings

scriptedBufferLog := false

scriptedLaunchOpts += ("-Dproject.version=" + version.value )

