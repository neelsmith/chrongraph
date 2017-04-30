package edu.holycross.shot.chrongraph

import scala.io.Source

import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._
import scalax.collection.io.dot._
import implicits._

import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

object GraphDotter {

  def dotString(cg : ChronologicalGraph) : String  = {
    val root = new DotRootGraph(true, id = Some(Id("Chronological graph")))
    cg.graph.toDot(root, simpleTransformer(root, cg.graph, _))
  }

  def dotString(cg : ChronologicalGraph, id1: String , id2 : String) : String  = {
    dotString(cg,HistoricalEvent(id1,id1), HistoricalEvent(id2,id2))
  }

  def dotString(cg : ChronologicalGraph, e1: HistoricalEvent , e2 : HistoricalEvent) : String  = {
    val root = new DotRootGraph(true, id = Some(Id("Chronological graph")))
    val pathOpt =  (cg.findEvt(e1) shortestPathTo cg.findEvt(e2))
    pathOpt match {
      case None => {
        println(s"Failed to find path from ${e1} to ${e2} in ${cg}")
          ""
      }
      case _ =>  {
        val path = pathOpt.get
        cg.graph.toDot(root, edgeTransformer(root, cg.graph, path, _))
      }
    }
  }

  def simpleTransformer(
    root: DotRootGraph,
    graph: Graph[HistoricalEvent, LDiEdge],
    innerEdge: Graph[HistoricalEvent,LDiEdge]#EdgeT) :
    Option[(DotGraph,DotEdgeStmt)] = {
    innerEdge match {
      case graph.EdgeT(source, target) => {
        Some((root, DotEdgeStmt(source.toString, target.toString)))
      }
    }
  }

  def edgeTransformer(
    root: DotRootGraph,
    graph: Graph[HistoricalEvent, LDiEdge],
    path: Graph[HistoricalEvent, LDiEdge]#Path,
    innerEdge: Graph[HistoricalEvent,LDiEdge]#EdgeT) :
    Option[(DotGraph,DotEdgeStmt)] = {
      val  red = "#ff0000"
    innerEdge match {
      case graph.EdgeT(source, target) => {
        if (path.edges.exists(e => e.equals(innerEdge))) {
          Some((root,
            DotEdgeStmt(
              source.toString,
              target.toString,
              List(DotAttr("color",red)))))
        }else {
          Some((root, DotEdgeStmt(source.toString, target.toString)))
        }
      }
    }
  }
}
