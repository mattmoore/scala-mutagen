package io.mattmoore.scala.mutagen.plugins

import scala.tools.nsc.plugins.{Plugin, PluginComponent}
import scala.tools.nsc.{Global, Phase}

class DivisionByZero(val global: Global) extends Plugin {

  import global._

  val name = "division-by-zero"
  val description = "checks for division by zero"
  val components = List[PluginComponent](Component)

  private object Component extends PluginComponent {
    val global: DivisionByZero.this.global.type = DivisionByZero.this.global
    val runsAfter = List[String]("refchecks")
    val phaseName = DivisionByZero.this.name

    def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)

    class DivByZeroPhase(prev: Phase) extends StdPhase(prev) {
      override def name = DivisionByZero.this.name

      def apply(unit: CompilationUnit): Unit = {
        for (
          tree @ Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <-
            unit.body
          if rcvr.tpe <:< definitions.IntClass.tpe
        ) {
          global.reporter.error(tree.pos, "Attempting division by zero.")
        }
      }
    }

  }

}
