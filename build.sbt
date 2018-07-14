lazy val commonSettings = Seq(
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

lazy val root = project.in(file(".")).settings(commonSettings).aggregate(zeroNine, zeroTen, oneZero)

lazy val zeroNine = project.in(file("0.9")).settings(commonSettings)
lazy val zeroTen = project.in(file("0.10")).settings(commonSettings)
lazy val oneZero = project.in(file("1.0")).settings(commonSettings)

