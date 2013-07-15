import sbt._
import Keys._

object build extends Build {
  lazy val scalazDependencies = Seq(
    "org.scalaz"     %% "scalaz-core"               % "7.0.2",
    "org.scalaz"     %% "scalaz-effect"             % "7.0.2",
    "org.scalaz"     %% "scalaz-typelevel"          % "7.0.2",
    "org.scalaz"     %% "scalaz-scalacheck-binding" % "7.0.2" % "test"
  )

  lazy val shapelessDependencies = Seq(
    "com.chuusai" %% "shapeless" % "1.2.4"
  )

  lazy val spireDependencies = Seq(
    "org.spire-math" %% "spire"                    % "0.4.0",
    "org.spire-math" %% "spire-scalacheck-binding" % "0.4.0" % "test"
  )

  lazy val scalazContribDependencies = Seq(
    "org.typelevel" %% "scalaz-contrib-210"        % "0.1.4",
    "org.typelevel" %% "scalaz-contrib-validation" % "0.1.4",
    "org.typelevel" %% "scalaz-contrib-undo"       % "0.1.4",
    "org.typelevel" %% "scalaz-dispatch"           % "0.1.4",
    "org.typelevel" %% "scalaz-lift"               % "0.1.4",
    "org.typelevel" %% "scalaz-nscala-time"        % "0.1.4",
    "org.typelevel" %% "scalaz-spire"              % "0.1.4"
  )

  lazy val shapelessContribDependencies = Seq(
    "org.typelevel" %% "shapeless-scalacheck" % "0.1.1",
    "org.typelevel" %% "shapeless-spire"      % "0.1.1",
    "org.typelevel" %% "shapeless-scalaz"     % "0.1.1"
  )

  lazy val activator = Project(
    id = "typelevel-activator",
    base = file("."),
    settings = Defaults.defaultSettings ++
    Seq(
      scalaVersion := "2.10.2",
      initialCommands := "import scalaz._, shapeless._, spire._",
      init := initImpl,
      help := helpImpl,
      shellPrompt := { _ ⇒ "λ typelevel → " },
      libraryDependencies := scalazDependencies ++
        shapelessDependencies ++
        spireDependencies ++
        scalazContribDependencies ++
        shapelessContribDependencies
    )
  )

  lazy val init = TaskKey[Unit]("init")
  lazy val help = TaskKey[Unit]("usage")
  lazy val initImpl = {
    Process("g8" :: "folone/typelevel-activator" :: Nil, Path.userHome).!<
    println("Done")
  }
  lazy val helpImpl = {
    val helpMsg = """console                       -- to launch repl
                    |init <name> <organization>    -- to create a new typelevel project at the direcroty you are currently in
                    |exit                          -- exit typelevel activator
                    |help                          -- print sbt help
                    |typelevel-help                -- print this message""".stripMargin
    println(helpMsg)
  }
}
