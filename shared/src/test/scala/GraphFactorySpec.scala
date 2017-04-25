package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class GraphFactorySpec extends FlatSpec {

  "A graph factory"  should "be able to create a single directed edge from CSV source" in  {
    val csv = "urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318"

    val edge = GraphFactory.edgeFromCsv(csv)

    edge match {
      case ldi : LDiEdge[HistoricalEvent] => assert(true)
      case _ => fail("Should have created a labelled directed edge")
    }
  }

  it should "be able to create a directed graph from a multi-line CSV source string" in {
        val csv = """urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318
urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,precedes,epoch,urn:cite2:chron.epoch:pm,1310
urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,contemporary,eponym,urn:cite:chron.atticrulers:1,0
  """

      val g = GraphFactory.fromCsv(csv)
      g match {
        case graph: Graph[HistoricalEvent,LDiEdge] => assert(true)
        case _ => fail("Should have created a labelled directed graph")
      }
    }


}
