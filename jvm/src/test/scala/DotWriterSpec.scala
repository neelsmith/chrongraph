package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec


class DotWriterSpec extends FlatSpec {

  "A graph dot writer"  should "write dot strings to local files" in  {
    val f = "jvm/src/test/resources/pm.csv"
    val g = GraphSource(f)
    val epochEvt = HistoricalEvent("urn:cite2:chron.epoch:pm", "label for urn:cite2:chron.epoch:pm")
    val evt2 = HistoricalEvent("urn:cite2:chron.event:2", "label for urn:cite2:chron.event:2")

    DotWriter.writeDot(g,evt2,epochEvt,"unittest")
  }




}
