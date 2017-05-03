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



  def sumInterval( evt1: String, evt2: String) : Map[String,Int] = {
    val pth = (findEvtById(evt1) shortestPathTo findEvtById(evt2)).get
    val pthEdges =  pth.edges.toVector
    //println(s"\n\nSum interval from ${evt1} to ${evt2}" )
    //println("TOTAL EDGES: " + pthEdges.size)

    val intervalMap = Map[String,Int]()
    sumEdges(pthEdges, intervalMap)
  }



  def directedAmount(relation: String, amount: Int) : Int =  {
    relation match {
      case p if p.contains("pre") =>  -1 * amount
      case f if f.contains("follow") => amount
      case c if c.contains("contemporary") => 0
      case mystery => throw new Exception("unrecognized relation: " + mystery)
    }
  }

  def sumEdges(edgeV: Vector[ChronologicalGraph.this.graph.EdgeT], results : Map[String,Int]): Map[String,Int] = {
    val relationData = edgeV(0).label
    //println("\n" + edgeV(0)._1 + " -> " + edgeV(0)._2)

    val quant = directedAmount(relationData.rel, relationData.amt)
    //println("QUANT: " + quant)
    if (results.keySet.exists(_ == relationData.sys)) {
      val newTotal = results(relationData.sys) + quant
      //println("Relation " + relationData.rel + ", augmenting result for " + relationData.sys + " by " + quant)
      //println("New total: " + newTotal)
      if (edgeV.size == 1) {
        results + (relationData.sys -> newTotal)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys -> newTotal) )
      }




    } else {
      //println("New entry for " + relationData.rel + ", " + relationData.sys + ", amount " + quant)
      if (edgeV.size == 1) {
        results + (relationData.sys -> quant)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys -> quant) )
      }

    }



  }

}

object ChronologicalGraph {




}
