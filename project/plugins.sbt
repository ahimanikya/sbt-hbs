resolvers += Resolver.sbtPluginRepo("releases")

libraryDependencies ++= Seq(
  "org.scala-sbt" % "scripted-plugin" % sbtVersion.value
)

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.5.4")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.11")

