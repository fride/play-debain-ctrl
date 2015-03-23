import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes.ServerLoader.SystemV

name := """play-debian-ctrl"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
	.enablePlugins(PlayScala)

daemonUser in Linux := "sometest"
serverLoading in Debian := SystemV
packageBin in Debian <<= debianJDebPackaging in Debian
maintainer in Debian := "Jan Friderici <jnfrd@outlook.com>"
packageSummary := "Some More Tests"
packageDescription := """Even much more test, or text?."""
debianMaintainerScripts := {       
  Seq(
  	file("src/debian/DEBIAN/postrm") -> "postrm",
 	file("src/debian/DEBIAN/postinst") -> "postinst")
}
debianMakePostinstScript := {        
	Some(sourceDirectory.value / "src/debian/DEBIAN" / "override")
}      


scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)