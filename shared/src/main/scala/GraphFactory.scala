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
  def fromCsv(s: String) = {
    val rows = s.split("\n")
    val edges = for (r <- rows) yield {
      edgeFromCsv(r)
    }
    Graph(edges)
  }

  /** Create directed edge from a string of csv data.
  *
  * @param s String of CSV data representing a single edge.
  * It should be composed of 6 columns, sequentially giving
  * 1) the source for the relation, 2) starting event, 3) chronological relation,
  * 4) type or system of relation, 5) target event, and 6) number of units difference.
  */
  def edgeFromCsv(s: String): LDiEdge[HistoricalEvent] = {
      val columns = s.split(",")


      val sourceEvent = HistoricalEvent(columns(1), "label for " + columns(0))
      val targetEvent = HistoricalEvent(columns(4), "label for " + columns(4))

      val src = columns(0)
      val relation = columns(2)
      val relationType  = columns(3)
      val unitsDiff = columns(5).toInt

      val simpleRelation = SimpleRelation(relationType,unitsDiff,relationType,src)
      LDiEdge(sourceEvent,targetEvent)(simpleRelation)
  }

}
