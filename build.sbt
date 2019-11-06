name := "hello-stream-redis"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
libraryDependencies += "dev.profunktor" %% "redis4cats-effects" % "0.9.1"
libraryDependencies += "dev.profunktor" %% "redis4cats-log4cats" % "0.9.1"
libraryDependencies += "io.chrisdavenport" %% "log4cats-slf4j" % "1.0.1"
