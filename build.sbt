lazy val commonSettings = Seq(
  resolvers += Resolver.sonatypeRepo("snapshots"),
  organization := "co.fs2",
  scalaVersion := "2.12.6",
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:existentials",
    "-language:postfixOps",
    "-Ypartial-unification",
    "-Xlint",
    "-Xfatal-warnings",
    "-Yno-adapted-args",
    "-Ywarn-value-discard",
    "-Ywarn-unused-import"
  ),
  scalacOptions in (Compile, console) ~= {
    _.filterNot("-Ywarn-unused-import" == _)
      .filterNot("-Xlint" == _)
      .filterNot("-Xfatal-warnings" == _)
  },
  scalacOptions in (Compile, console) += "-Ydelambdafy:inline",
  scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value
)

addCommandAlias("benchmark", s"jmh:run -rf json -t 2 -wi 5 -i 5")

lazy val root = project.in(file(".")).
  settings(commonSettings).
  aggregate(zeroNine, zeroTen, oneZero)

lazy val common = project.in(file("common")).settings(commonSettings)

lazy val zeroNine = project.in(file("0.9")).
  enablePlugins(JmhPlugin).
  dependsOn(common).
  settings(commonSettings).
  settings(
    libraryDependencies += "co.fs2" %% "fs2-io" % "0.9.6"
  )

lazy val zeroTen = project.in(file("0.10")).
  enablePlugins(JmhPlugin).
  dependsOn(common).
  settings(commonSettings).
  settings(
    libraryDependencies += "co.fs2" %% "fs2-io" % "0.10.5"
  )

lazy val oneZero = project.in(file("1.0")).
  enablePlugins(JmhPlugin).
  dependsOn(common).
  settings(commonSettings).
  settings(
    libraryDependencies += "co.fs2" %% "fs2-io" % "1.0.0-SNAPSHOT"
  )
