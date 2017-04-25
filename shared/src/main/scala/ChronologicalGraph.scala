package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._



/** A ChronologicalGraph is a directed graph of historical events.
*/
@JSExport case class ChronologicalGraph(g: Graph[HistoricalEvent,LDiEdge]) { 

}
