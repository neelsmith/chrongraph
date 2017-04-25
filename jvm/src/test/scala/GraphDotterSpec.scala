package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec


class GraphDotterSpec extends FlatSpec {

  "A graph dotter"  should "create a dot string from a graph" in  {
    val csv = """urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318
  urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,precedes,epoch,urn:cite2:chron.epoch:pm,1310
  urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,contemporary,eponym,urn:cite:chron.atticrulers:1,0
  """

    val g = GraphFactory.fromCsv(csv)
      val epochEvt = HistoricalEvent("urn:cite2:chron.epoch:pm", "label for urn:cite2:chron.epoch:pm")
      val evt2 = HistoricalEvent("urn:cite2:chron.event:2", "label for urn:cite2:chron.event:2")

    ///urn:cts:chronepig:chron.pm:1,urn:cite2:chron.event:2,precedes,epoch,urn:cite2:chron.epoch:pm,1318
//urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,precedes,epoch,urn:cite2:chron.epoch:pm,1310
//urn:cts:chronepig:chron.pm:2,urn:cite2:chron.event:3,contemporary,eponym,urn:cite:chron.atticrulers:1,0

    print(GraphDotter.dotString(g,evt2, epochEvt))


  }




}
