resolvers += Resolver.sbtPluginRepo("releases")

libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % "1.2.8"

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.11")

