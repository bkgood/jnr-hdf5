import de.johoop.testngplugin.TestNGPlugin._

name := """jnr-hdf5"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += Resolver.sbtPluginRepo("releases")

libraryDependencies ++= Seq(
  "com.github.jnr" % "jnr-ffi" % "2.0.7",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.testng" % "testng" % "6.8.8" % Test)

testNGSettings
