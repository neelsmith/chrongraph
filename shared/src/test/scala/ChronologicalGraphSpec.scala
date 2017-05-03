package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class ChronologicalGraphSpec extends FlatSpec {

  val csv = """urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318
urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,precedes,epoch,urn:cite2:chron.epoch:pm,1310
urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,contemporary,eponym,urn:cite:chron.atticrulers:1,0
"""


  "A chronological graph"  should "be buildable from csv source" in {
    val g = GraphFactory.fromCsv(csv)
    assert(g.graph.edges.size == 3)
  }


  it should "find events in a graph" in {
    val evt = HistoricalEvent("urn:cite2:chron.epoch:pm", "label for urn:cite2:chron.epoch:pm")
    val g = GraphFactory.fromCsv(csv)
    assert(g.graph.edges.size == 3)
    assert(g.findEvt(evt).toString == "label for urn:cite2:chron.epoch:pm")
  }



  it should "have HistoricalEvent objects for nodes" in {
    val g = GraphFactory.fromCsv(csv)
    for (n <- g.graph.nodes) {
      n.toOuter match {
        case evt: HistoricalEvent => assert(true)
        case _ => fail("Should have found historical events")
      }
    }
  }

  it should "take account of direction in summing up intervals" in pending
  

}
