import sbt._
import Keys._

name := "scala-sql-compare"

lazy val commonSettings = Seq(
  organization := "com.softwaremill",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.15",
)

lazy val scalaSqlCompare = (project in file("."))
  .settings(commonSettings)
  .aggregate(slick, doobie, quill, scalikejdbc, ziosql)

lazy val common = (project in file("common"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "42.3.5",
      "org.flywaydb" % "flyway-core" % "8.5.10",
      "org.slf4j" % "slf4j-simple" % "1.7.36",
    )
  )

lazy val slick = (project in file("slick"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.3.3"
    )
  )
  .dependsOn(common)

lazy val doobie = (project in file("doobie"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1"
    )
  )
  .dependsOn(common)

lazy val quill = (project in file("quill"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "io.getquill" %% "quill-async-postgres" % "3.12.0",
    )
  )
  .dependsOn(common)

lazy val scalikejdbc = (project in file("scalikejdbc"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.5.0",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
    )
  )
  .dependsOn(common)

lazy val ziosql = (project in file("ziosql"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-sql-postgres" % "0.0.1",
      "dev.zio" %% "zio" % "2.0.0-RC2",
      "dev.zio" %% "zio-schema" % "0.1.7",
      "dev.zio" %% "zio-schema-derivation" % "0.1.7",
      "org.postgresql" % "postgresql" % "42.2.24" % Compile,
      "com.dimafeng" %% "testcontainers-scala-postgresql" % "0.39.12",
    )
  )
  .dependsOn(common)
