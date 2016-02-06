import sbt.Keys._
import sbt._

object Build extends Build {
  val moduleName = "scala-presentation"

  lazy val root = Project(id = moduleName, base = file("."))
    .settings(Defaults.itSettings: _*)
    .settings(
      name := moduleName,
      organization := "uk.gov.homeoffice",
      version := "1.0.0-SNAPSHOT",
      scalaVersion := "2.11.7",
      scalacOptions ++= Seq(
        "-feature",
        "-language:implicitConversions",
        "-language:higherKinds",
        "-language:existentials",
        "-language:reflectiveCalls",
        "-language:postfixOps",
        "-Yrangepos",
        "-Yrepl-sync"
      ),
      ivyScala := ivyScala.value map {
        _.copy(overrideScalaVersion = true)
      },
      resolvers ++= Seq(
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
        "Kamon Repository" at "http://repo.kamon.io"
      ),
      libraryDependencies ++= Seq(
      ),
      libraryDependencies ++= {
        val specs2Version = "3.7"

        Seq(
          "com.lihaoyi" % "ammonite-repl" % "0.5.3" % Test cross CrossVersion.full,
          "org.mockito" % "mockito-all" % "1.10.19" % Test withSources(),
          "org.specs2" %% "specs2-core" % specs2Version % Test withSources(),
          "org.specs2" %% "specs2-mock" % specs2Version % Test withSources() excludeAll ExclusionRule(organization = "org.mockito"),
          "org.specs2" %% "specs2-matcher-extra" % specs2Version % Test withSources(),
          "org.specs2" %% "specs2-junit" % specs2Version % Test withSources()
        )
      }
    )
}