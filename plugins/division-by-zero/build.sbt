ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / sbtPlugin := false

val scala2V = "2.13.7"
val scala3V = "3.1.0"

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    crossScalaVersions := Seq(scala2V, scala3V),
    publish / skip := true
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala.mutagen",
    name := "division-by-zero",
    scalaVersion := scala2V,
    libraryDependencies ++= List(
      "org.scala-lang" % "scala-compiler" % scala2V
    )
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala.mutagen",
    name := "division-by-zero",
    scalaVersion := scala3V,
    libraryDependencies ++= List(
      "org.scala-lang" %% "scala3-compiler" % scala3V
    )
  )
