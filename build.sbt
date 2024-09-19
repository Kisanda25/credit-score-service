name := "CreditScoreService"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.6.16"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.2.6"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.16"
libraryDependencies += "de.heikoseeberger" %% "akka-http-circe" % "1.37.0" // for JSON support
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.19"
