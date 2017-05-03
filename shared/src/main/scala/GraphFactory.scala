package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

//import implicits._


/** Factory for creating Graph structures from structured text sources.
*/
@JSExport object GraphFactory {

  /** Create directed graph from a CSV source with
  * each line representing a single edge. Optionally, provide
  * a map from identifiers for historical events to human-readable labels.
  *
  * @param s CSV source data.
  * @param labels Map of ID strings to labels.
  */
  def fromCsv(s: String, labels: Map[String,String] = Map[String,String]()): ChronologicalGraph = {

    val rows = s.split("\n")
    // initialize a graph:
    val edge1 = edgeFromCsv(rows(0),labels)
    var g = scalax.collection.mutable.Graph(edge1)
    // don't forget to make it bidirectional:
    g = g + invertEdgeFromCsv(rows(0),labels)

    val edges = for (r <- rows.drop(1) if r.split(",").size > 5) yield {
      //println("\nADD EDGE + " + edgeFromCsv(r, labels))
      //println("ADD INVERTED EDGE + " + invertEdgeFromCsv(r, labels))
      //println("\n")
      g = g + edgeFromCsv(r, labels)
      g = g + invertEdgeFromCsv(r,labels)
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
  def edgeFromCsv(csv: String, labels: Map[String,String] = Map[String,String]() ) : LDiEdge[HistoricalEvent] = {
      val columns = csv.split(",")
      if (columns.size < 6) {
        val msg = s"Can't make edge from ${columns.size} columns in " + csv
        println(msg)
        throw(new Exception(msg))

      } else {

        val sourceEvent = {
          if (labels.keySet.contains(columns(1))) {
            val evt = HistoricalEvent(columns(1), labels(columns(1)))
            evt
          } else {
            HistoricalEvent(columns(1), "label for " + columns(1))
          }
        }
        val targetEvent = {
          if (labels.keySet.contains(columns(4))) {
            val evt = HistoricalEvent(columns(4), labels(columns(4)))
            evt
          } else {
            HistoricalEvent(columns(4), "label for " + columns(4))
          }
        }
        val src = columns(0).trim
        val relation = columns(2).trim
        val relationType  = columns(3).trim
        val unitsDiff = columns(5).trim.toInt

        val simpleRelation = SimpleRelation(relation,unitsDiff,relationType,src)
        LDiEdge(sourceEvent,targetEvent)(simpleRelation)

      }
  }



  def invertRelation(relation: String) : String = {
    relation match {
      case c if c.contains("contemporary") => "contemporary"
      case f if f.contains("follows") => "precedes"
      case p if p.contains("precede") => "follows"
      case _ => "unrecognized relation"
    }
  }

  def integerUnits(i: Int, relation: String) : Int = {
    relation match {
      case p if p.contains("precede") => i * -1
      case f if f.contains("follow") => i
      case c if c.contains("contemporary") => 0
      case msg: String => throw new Exception("unrecognized relation: " + msg)
    }
  }
  /** Create directed edge from a string of csv data.
  *
  * @param s String of CSV data representing a single edge.
  * It should be composed of 6 columns, sequentially giving
  * 1) the source for the relation, 2) starting event, 3) chronological relation,
  * 4) type or system of relation, 5) target event, and 6) number of units difference.
  */
  def invertEdgeFromCsv(csv: String, labels: Map[String,String] = Map[String,String]() ) : LDiEdge[HistoricalEvent] = {
      val columns = csv.split(",")
      if (columns.size < 6) {
        val msg = s"Can't make edge from ${columns.size} columns in " + csv
        println(msg)
        throw(new Exception(msg))

      } else {

        val targetEvent = {
          if (labels.keySet.contains(columns(1))) {
            val evt = HistoricalEvent(columns(1), labels(columns(1)))
            evt
          } else {
            HistoricalEvent(columns(1), "label for " + columns(1))
          }
        }
        val sourceEvent = {
          if (labels.keySet.contains(columns(4))) {
            val evt = HistoricalEvent(columns(4), labels(columns(4)))
            evt
          } else {
            HistoricalEvent(columns(4), "label for " + columns(4))
          }
        }
        val src = columns(0).trim
        val relation = invertRelation(columns(2).trim.toLowerCase)
        val relationType  = columns(3).trim.toLowerCase
        val unitsDiff = integerUnits(columns(5).trim.toInt, relation)
        val invertedRelation =  SimpleRelation(relation,unitsDiff,relationType,src)

       LDiEdge(sourceEvent,targetEvent)(invertedRelation)
      }
  }

}
