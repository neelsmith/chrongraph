package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class LabelledGraphFactorySpec extends FlatSpec {

  val labelMap = Map("urn:cite2:chron:atticrulers:1" -> "Kingship of Cecrops in Athens",
"urn:cite2:chron:epoch:pm" -> "Epoch of the Parian Marble",
"urn:cite2:chron:event:3" -> "Deucalion king in Lykoreia"
  )
  val csv = """urn:cts:chronepig:chron.pm:1,urn:cite2:chron:atticrulers:1,precedes,epoch,urn:cite2:chron:epoch:pm,1318
urn:cts:chronepig:chron.pm:2,urn:cite2:chron:event:3,precedes,epoch,urn:cite2:chron:epoch:pm,1310
"""

  "A graph factory"  should  "accept an optional map of labels for historical events" in  {
    val g = GraphFactory.fromCsv(csv, labelMap)
    assert(g.graph.nodes.size == 3)
    val nvect = g.graph.nodes.toVector
    val labels = List("Kingship of Cecrops in Athens","Epoch of the Parian Marble","Deucalion king in Lykoreia")
    assert(nvect.size == labels.size)
    for (n <-  nvect) {
      assert(labels.contains(n.toString))
    }
  }

  it should "find events in the graph by ID" in {
    val g = GraphFactory.fromCsv(csv,labelMap)
    assert(g.graph.nodes.size == 3)
    assert(g.findEvtById("urn:cite2:chron:epoch:pm").toString == "Epoch of the Parian Marble")
  }
}
