package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class HistoricalEventSpec extends FlatSpec {



  "A historical event"  should "have an id and a label" in {
    val evt = HistoricalEvent("epoch,urn:cite2:chron.epoch:pm","Epoch of the Parian Marble")
    assert (evt.id == "epoch,urn:cite2:chron.epoch:pm")
    assert (evt.label == "Epoch of the Parian Marble")
  }

  it should "use determine equality based solely on the ID" in {
    val evt1 = HistoricalEvent("epoch,urn:cite2:chron.epoch:pm","Epoch of the Parian Marble")
    val evt2 = HistoricalEvent("epoch,urn:cite2:chron.epoch:pm","Start date for the Parian Marble")
    assert (evt1 == evt2)
    assert(evt1.label != evt2.label)
  }

}
