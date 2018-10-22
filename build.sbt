import Dependencies._

val scalazVersion = "7.2.26"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.Angelo",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ScalaZTutorial",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-typelevel" % "7.1.17",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.20" % "test"
),
scalacOptions += "-feature",
scalacOptions += "-language:implicitConversions",
initialCommands in console := "import scalaz._, Scalaz._"
  )


