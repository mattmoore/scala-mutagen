ThisBuild / version := "0.1.0-SNAPSHOT"

val scala2V = "2.13.7"
val scala3V = "3.1.0"

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    name := "Using the divison-by-zero plugin.",
    crossScalaVersions := Seq(scala2V, scala3V),
    publish / skip := true,
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala.mutagen",
    name := "Using the division-by-zero plugin for Scala 2.",
    scalaVersion := scala2V,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.mutagen" %% "division-by-zero" % "0.1.0-SNAPSHOT")
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala.mutagen",
    name := "Using the division-by-zero plugin for Scala 3.",
    scalaVersion := scala3V,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.mutagen" %% "division-by-zero" % "0.1.0-SNAPSHOT")
  )

resolvers += Resolver.mavenLocal
