package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class ChronologicalGraphSpec extends FlatSpec {

  val csv = """urn:cts:chronepig:chron.pm:2,urn:cite2:chron:atticrulers:1,precedes,epochyear,urn:cite2:chron:epoch:pm,1318
urn:cts:chronepig:chron.pm:2,urn:cite2:chron:event:3,precedes,epochyear,urn:cite2:chron:epoch:pm,1310
urn:cts:chronepig:chron.pm:2,urn:cite2:chron:event:3,contemporary,eponym,urn:cite2:chron:atticrulers:1,0
"""


  "A chronological graph"  should "be buildable from csv source" in {
    val labelMap = Map("urn:cite2:chron:atticrulers:1" -> "Kingship of Cecrops in Athens",
  "urn:cite2:chron:epoch:pm" -> "Epoch of the Parian Marble",
  "urn:cite2:chron:event:3" -> "Deucalion king in Lykoreia"
    )
    val g = GraphFactory.fromCsv(csv, labelMap)
    assert(g.graph.edges.size == 6)
  }

  it should "find events in a graph" in {
    val evt = HistoricalEvent("urn:cite2:chron:epoch:pm", "Epoch of the Parian marble")
    val g = GraphFactory.fromCsv(csv)
    assert(g.graph.edges.size == 6)
    assert(g.findEvt(evt).toString == "label for urn:cite2:chron:epoch:pm")
  }

  it should "find events by ID value" in {
    val g = GraphFactory.fromCsv(csv)
    val evt = g.findEvtById("urn:cite2:chron:epoch:pm")
    assert (evt.toString == "label for urn:cite2:chron:epoch:pm")
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


}
