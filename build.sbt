name := "Utilities for working with graphs of chronological relations"

//crossScalaVersions := Seq("2.11.8")

crossScalaVersions := Seq("2.12.1")
lazy val root = project.in(file(".")).
    aggregate(crossedJVM, crossedJS).
    settings(
      publish := {},
      publishLocal := {}

    )

lazy val crossed = crossProject.in(file(".")).
    settings(
      name := "chrongraph",
      organization := "edu.holycross.shot",
      version := "0.1.2",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),

      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
        "org.scala-graph" %%% "graph-core" % "1.11.4"
      )
    ).
    jvmSettings(
        libraryDependencies ++= Seq(
        "org.scala-graph" % "graph-dot_2.12" % "1.11.5"
      )
    ).
    jsSettings(
      skip in packageJSDependencies := false,
      persistLauncher in Compile := true,
      persistLauncher in Test := false
    )

lazy val crossedJVM = crossed.jvm
lazy val crossedJS = crossed.js.enablePlugins(ScalaJSPlugin)
