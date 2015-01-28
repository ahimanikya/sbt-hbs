sbtPlugin := true

organization := "com.bicou.sbt"

name := "sbt-hbs"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  Resolver.mavenLocal
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.0.2")

publishMavenStyle := false

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

pomExtra := (
  <scm>
    <url>git@github.com:bicouy0/sbt-hbs.git</url>
    <connection>scm:git:git@github.com:bicouy0/sbt-hbs.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bicouy0</id>
      <name>Benjamin Viellard</name>
      <url>https://github.com/bicouy0</url>
    </developer>
  </developers>
)

publishTo := {
  if (isSnapshot.value) Some(Classpaths.sbtPluginSnapshots)
  else Some(Classpaths.sbtPluginReleases)
}

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }
