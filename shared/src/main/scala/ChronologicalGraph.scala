package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._



/** A ChronologicalGraph is a directed graph of historical events.
*/
@JSExport case class ChronologicalGraph(graph: Graph[HistoricalEvent,LDiEdge]) {


  /** Get node within graph matching a historical event.
  *
  * @param evt Event to match.
  */
  def findEvt(evt: HistoricalEvent) = {
    this.graph get evt
  }



    /** Get node within graph matching a historical event
    * identified by ID.
    *
    * @param id ID of event to match.
    */
  def findEvtById(id: String) = {
    this.graph get HistoricalEvent(id,id)
  }
}
