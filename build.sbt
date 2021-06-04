name := "scalarl"

version := "0.0.1"

scalaVersion := "2.13.3"

libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "1.1",

  // Native libraries are not included by default. add this if you want them
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "1.1",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "1.1",

)

// ====
// ScalaPy

// JVM
libraryDependencies +="me.shadaj" %% "scalapy-core" % "0.5.0"

fork := true

import scala.sys.process._
lazy val pythonLdFlags = {

  val withoutEmbed = "python3-config --ldflags".!!

  if (withoutEmbed.contains("-lpython")) {
    withoutEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  }
  else {
    val withEmbed = "python3-config --ldflags --embed".!!
    withEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  }
}

lazy val pythonLibsDir = {
  pythonLdFlags.find(_.startsWith("-L")).get.drop("-L".length)
}

javaOptions += s"-Djna.library.path=$pythonLibsDir"
//javaOptions += s"-Djna.library.path=/usr/lib/python3.8/config-3.8-x86_64-linux-gnu"

// ===