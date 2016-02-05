import io.gatling.sbt.GatlingPlugin
import sbt.Keys._
import sbt._
import spray.revolver.RevolverPlugin._

object Build extends Build {
  import DependentProjects._

  val moduleName = "scala-presentation"

  lazy val root = Project(id = moduleName, base = file("."))
    .enablePlugins(GatlingPlugin)
    .dependsOn(rtpTestLib, rtpIoLib, rtpMongoLib)
    .configs(IntegrationTest)
    .settings(Revolver.settings)
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
        "org.scalactic" %% "scalactic" % "2.2.6" withSources()
      ),
      libraryDependencies ++= {
        val specs2Version = "3.7"
        val gatlingVersion = "2.1.7"

        Seq(
          "com.lihaoyi" % "ammonite-repl" % "0.5.3" % Test cross CrossVersion.full,
          "org.mockito" % "mockito-all" % "1.10.19" withSources(),
          "org.specs2" %% "specs2-core" % specs2Version withSources(),
          "org.specs2" %% "specs2-mock" % specs2Version withSources() excludeAll ExclusionRule(organization = "org.mockito"),
          "org.specs2" %% "specs2-matcher-extra" % specs2Version withSources(),
          "org.specs2" %% "specs2-junit" % specs2Version withSources(),
          "com.github.fakemongo" % "fongo" % "1.6.2" % Test withSources(),
          "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % IntegrationTest withSources(),
          "io.gatling" % "gatling-test-framework" % gatlingVersion % IntegrationTest withSources()
        )
      }
    )
    //.settings(javaOptions += "-Dconfig.resource=application.no.pollers.conf")
    .settings(run := (run in Runtime).evaluated) // Required to stop Gatling plugin overriding the default "run".
}

object DependentProjects {
  lazy val rtpTestLib = RootProject(uri("https://github.com/UKHomeOffice/rtp-test-lib.git"))

  lazy val rtpIoLib = RootProject(uri("https://github.com/UKHomeOffice/rtp-io-lib.git"))

  lazy val rtpMongoLib = RootProject(uri("https://github.com/UKHomeOffice/rtp-mongo-lib.git"))
}