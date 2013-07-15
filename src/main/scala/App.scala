package info.folone

import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter._

/** The launched conscript entry point */
class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    Exit(App.run(config.arguments))
  }
}

object App {
  /** Shared by the launched version and the runnable version,
   * returns the process status code */
  def run(args: Array[String]): Int = {
    val settings = new Settings
    settings.usejavacp.value = true
    settings.deprecation.value = true

    new ILoop().process(settings)
    0
  }
  /** Standard runnable class entrypoint */
  def main(args: Array[String]) {
    System.exit(run(args))
  }
}

case class Exit(val code: Int) extends xsbti.Exit
