package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class GraphFactorySpec extends FlatSpec {

  "A chronological graph"  should "do something" in  {
    //Event,Synchronism,Source,Relation,SyncType,SyncedWith,Amount,Label,Source
    //urn:cite:chron:event.2,
    //urn:cts:chronepig:chron.pm:1,precedes,epoch,urn:cite:chron:epoch.pm,1318,Reign of Cecrops in Athens,
    val csv = "urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318"
    val edge = GraphFactory.edgeFromCsv(csv)

    edge match {
      case ldi : LDiEdge[HistoricalEvent] => assert(true)
      case _ => fail("Should have created a labelled directed edge")
    }


  }


}
