val scala3Version = "3.0.3-RC1-bin-20210809-38b983c-NIGHTLY"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dep-types",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
