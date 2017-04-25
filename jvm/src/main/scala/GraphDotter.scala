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


/*
  def dotString(cg : ChronologicalGraph, e1: HistoricalEvent , e2 : HistoricalEvent) : String  = {
    val root = new DotRootGraph(true, id = Some(Id("Chronological graph")))
    val path = (cg.graph.n(e1) shortestPathTo cg.graph.n(e2)).get
    cg.graph.toDot(root, edgeTransformer(root, cg.graph, path, _))

  }

  def edgeTransformer(
    root: DotRootGraph,
    graph: Graph[HistoricalEvent, LDiEdge],
    path: Graph[HistoricalEvent, LDiEdge]#Path,
    innerEdge: Graph[HistoricalEvent,LDiEdge]#EdgeT) :
    Option[(DotGraph,DotEdgeStmt)] = {
    innerEdge match {
      case graph.EdgeT(source, target) => {
        if (path.edges.exists(e => e.equals(innerEdge))) {
          Some((root,
            DotEdgeStmt(
              source.toString,
              target.toString,
              List(DotAttr("color", "#ff0000")))))
        }else {
          Some((root, DotEdgeStmt(source.toString, target.toString)))
        }
      }
    }
  } */
}
