package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class GraphFactorySpec extends FlatSpec {

  "A graph factory"  should "be able to create a single directed edge from CSV source" in  {
    val csv = "urn:cts:chronepig:chron:pm:1,urn:cite2:chron:event:2,precedes,epoch,urn:cite2:chron:epoch:pm,1318"

    val edge = GraphFactory.edgeFromCsv(csv)

    edge match {
      case ldi : LDiEdge[HistoricalEvent] => assert(true)
      case _ => fail("Should have created a labelled directed edge")
    }
  }


  it should "be able to create a single directed edge with a label taken from a map" in  {
    val labelMap = Map("urn:cite2:chron:atticrulers:1" -> "Kingship of Cecrops in Athens",
"urn:cite2:chron:epoch:pm" -> "Epoch of the Parian Marble" )

   val csv = "urn:cts:chronepig:chron:pm:1,urn:cite2:chron:atticrulers:1,precedes,epoch,urn:cite2:chron:epoch:pm,1318"

   val edge = GraphFactory.edgeFromCsv(csv, labelMap)

   edge match {
     case ldi : LDiEdge[HistoricalEvent] =>{
       assert (ldi._1.toString == "Kingship of Cecrops in Athens")
       assert (ldi._2.toString == "Epoch of the Parian Marble")
     }
     case _ => fail("Should have created a labelled directed edge")
   }
 }


  it should "be able to create a directed graph from a multi-line CSV source string" in {
    val csv = """urn:cts:chronepig:chron:pm:1,urn:cite2:chron:event:2,precedes,epoch,urn:cite2:chron:epoch:pm,1318
urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,precedes,epoch,urn:cite2:chron:epoch:pm,1310
urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,contemporary,eponym,urn:cite:chron:atticrulers:1,0
"""

    val g = GraphFactory.fromCsv(csv)
    g match {
      case cgr: ChronologicalGraph => {
        assert(cgr.graph.edges.size == 6)
      }
      case _ => fail("Should have created a labelled directed graph")
    }
  }

  it should "tolerate blank lines in input" in {
    val labelMap = Map("urn:cite2:chron:atticrulers:1" -> "Kingship of Cecrops in Athens",
"urn:cite2:chron:epoch:pm" -> "Epoch of the Parian Marble" )
    val csv = """


urn:cts:chronepig:chron:pm:1,urn:cite2:chron:atticrulers:1,precedes,epoch,urn:cite2:chron:epoch:pm,1318"""

    val edge = GraphFactory.edgeFromCsv(csv, labelMap)

    edge match {
      case ldi : LDiEdge[HistoricalEvent] =>{
        assert (ldi._1.toString == "Kingship of Cecrops in Athens")
        assert (ldi._2.toString == "Epoch of the Parian Marble")
      }
      case _ => fail("Should have created a labelled directed edge")
    }
  }

  it should "make all links bidirectional when importing csv" in  {
    val csv = """urn:cts:chronepig:chron:pm:1,urn:cite2:chron:event:2,precedes,epoch,urn:cite2:chron:epoch:pm,1318
urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,precedes,epoch,urn:cite2:chron:epoch:pm,1310
urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,contemporary,eponym,urn:cite:chron:atticrulers:1,0
"""

    val g = GraphFactory.fromCsv(csv)
    assert (g.graph.edges.size == 6)

  }


}
