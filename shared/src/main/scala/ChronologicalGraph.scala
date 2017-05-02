package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._


import scalax.collection.edge.LBase.LEdgeImplicits
object MyImplicit extends LEdgeImplicits[SimpleRelation]; import MyImplicit._



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


  /**

  def sumInterval( evt1: HistoricalEvent, evt2: HistoricalEvent) = {
    val pth = (findEvt(evt1) pathTo findEvt(evt2)).get
    val edgeVector =  pth.edges.toVector.map(_.toOuter)
    val intervalMap = Map[String,Int]()
    println("START FROM " + edgeVector(0).label)
    ChronologicalGraph.sumEdges(edgeVector, intervalMap)
  }
  */
  def sumInterval( evt1: String, evt2: String) = {
    //sumInterval(findEvtById(evt1), findEvtById(evt2))
    val pth = (findEvtById(evt1) pathTo findEvtById(evt2)).get
    val pthEdges =  pth.edges.toVector
    val intervalMap = Map[String,Int]()


    val edge1label = pthEdges(0).label
    //println(s"START FROM  edge 1: ${edge1label.amt} in units of ${edge1label.sys}")


    sumEdges(pthEdges, intervalMap)
  }


  def sumEdges(edgeV: Vector[ChronologicalGraph.this.graph.EdgeT], results : Map[String,Int]): Map[String,Int] = {
    // : HistoricalEvent = { // : Map [String,Int]= {

    val relationData = edgeV(0).label
    if (results.keySet.exists(_ == relationData.sys)) {
      val newTotal = results(relationData.sys) + relationData.amt
      //println("Augmenting result for " + relationData.sys + " by " + relationData.amt)
      if (edgeV.size == 1) {
        results + (relationData.sys -> newTotal)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys -> newTotal) )
      }




    } else {
      //println("New entry for " + relationData.sys + ", amount " + relationData.amt)
      if (edgeV.size == 1) {
        results + (relationData.sys -> relationData.amt)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys -> relationData.amt) )
      }

    }



  }

}

object ChronologicalGraph {




}
