package edu.holycross.shot.chrongraph


import java.io.PrintWriter
import sys.process._

object DotWriter {

  def writeDot(cg : ChronologicalGraph, e1: HistoricalEvent , e2 : HistoricalEvent, fName: String = "chrongraph")  = {
    new PrintWriter(fName + ".dot"){write(GraphDotter.dotString(cg,e1,e2)); close;}

    val cmd =  s"dot -Tpng ${fName}.dot -o ${fName}.png"
    cmd.!
  }
}
