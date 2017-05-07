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



  def sumInterval( evt1: String, evt2: String,verbose: Boolean = false) : Map[String,Int] = {
    val pth = (findEvtById(evt1) shortestPathTo findEvtById(evt2)).get
    val pthEdges =  pth.edges.toVector
    if (verbose) {
      println(s"\n\nSum interval from ${evt1} to ${evt2}" )
      println("TOTAL CONNECTIONS: " + pthEdges.size)
  } else {}
    val intervalMap = Map[String,Int]()
    sumEdges(pthEdges, intervalMap,verbose)
  }



  def directedAmount(relation: String, amount: Int) : Int =  {
    relation match {
      case p if p.toLowerCase.contains("pre") =>  -1 * amount
      case f if f.toLowerCase.contains("follow") => amount
      case c if c.toLowerCase.contains("contemporary") => 0
      case mystery => throw new Exception("unrecognized relation: " + mystery)
    }
  }

  def sumEdges(edgeV: Vector[ChronologicalGraph.this.graph.EdgeT], results : Map[String,Int], verbose: Boolean = false): Map[String,Int] = {
    val relationData = edgeV(0).label
    if (verbose) {println("\n" + edgeV(0)._1 + " -> " + edgeV(0)._2)} else {}

    val quant = directedAmount(relationData.rel, relationData.amt)
    if (verbose) {println("quantity: " + quant)} else {}
    if (results.keySet.exists(_ == relationData.sys.toLowerCase)) {
      val newTotal = results(relationData.sys.toLowerCase) + quant
      if (verbose) {
        println("Relation " + relationData.rel + ", augmenting result for " + relationData.sys + " by " + quant)
        println("New total: " + newTotal)
      } else {}
      if (edgeV.size == 1) {
        results + (relationData.sys.toLowerCase -> newTotal)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys.toLowerCase -> newTotal), verbose )
      }




    } else {
      if (verbose) { println("New entry for " + relationData.rel + ", " + relationData.sys + ", amount " + quant)} else {}
      if (edgeV.size == 1) {
        results + (relationData.sys.toLowerCase -> quant)
      } else {
        sumEdges(edgeV.drop(1),results + (relationData.sys.toLowerCase -> quant) , verbose)
      }

    }



  }

}

object ChronologicalGraph {




}
