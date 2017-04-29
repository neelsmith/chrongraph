package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

//import implicits._


// Factory for creating Graph structures from text sources.
@JSExport object GraphFactory {

  /** Create directed graph from a CSV source with
  * each line representing a single edge.
  *
  * @param s CSV source data.
  */
  def fromCsv(s: String): ChronologicalGraph = {

    val rows = s.split("\n")
    val edge1 = edgeFromCsv(rows(0))
    var g = scalax.collection.mutable.Graph(edge1)
    val edges = for (r <- rows.drop(1) if r.split(",").size > 5) yield {
      g = g + edgeFromCsv(r)
    }
    ChronologicalGraph(g)
  }

  /** Create directed edge from a string of csv data.
  *
  * @param s String of CSV data representing a single edge.
  * It should be composed of 6 columns, sequentially giving
  * 1) the source for the relation, 2) starting event, 3) chronological relation,
  * 4) type or system of relation, 5) target event, and 6) number of units difference.
  */
  def edgeFromCsv(csv: String): LDiEdge[HistoricalEvent] = {
      val columns = csv.split(",")
      if (columns.size < 6) {
        val msg = s"Can't make edge from ${columns.size} columns in " + csv
        println(msg)
        throw(new Exception(msg))

      } else {
        val sourceEvent = HistoricalEvent(columns(1), "label for " + columns(1))
        val targetEvent = HistoricalEvent(columns(4), "label for " + columns(4))

        val src = columns(0).trim
        val relation = columns(2).trim
        val relationType  = columns(3).trim
        val unitsDiff = columns(5).trim.toInt

        val simpleRelation = SimpleRelation(relationType,unitsDiff,relationType,src)
        LDiEdge(sourceEvent,targetEvent)(simpleRelation)
      }
  }

}
