package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._



/** A ChronologicalGraph is a directed graph of historical events.
*/
@JSExport case class ChronologicalGraph(graph: Graph[HistoricalEvent,LDiEdge]) {

  def findEvt(evt: HistoricalEvent) = {
    this.graph get evt
  }
}
