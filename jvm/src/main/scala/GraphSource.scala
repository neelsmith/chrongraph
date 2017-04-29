package edu.holycross.shot.chrongraph

import scala.io.Source


object GraphSource {

  def apply(fName: String) : ChronologicalGraph = {
    val csv = Source.fromFile(fName).getLines.toVector.drop(1).mkString("\n")
    GraphFactory.fromCsv(csv)
  }
}
