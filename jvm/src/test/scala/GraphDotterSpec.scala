package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec


class GraphDotterSpec extends FlatSpec {

  "A graph dotter"  should "create a dot string from a graph" in  {

    val epochEvt = HistoricalEvent("urn:cite2:chron:epoch:pm", "label for urn:cite2:chron:epoch:pm")
    val evt2 = HistoricalEvent("urn:cite2:chron:event:2", "label for urn:cite2:chron:event:2")


    val csv = """urn:cts:chronepig:chron:pm:1,urn:cite2:chron:event:2,precedes,epoch,urn:cite2:chron:epoch:pm,1318
  urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,precedes,epoch,urn:cite2:chron:epoch:pm,1310
  urn:cts:chronepig:chron:pm:2,urn:cite2:chron:event:3,contemporary,eponym,urn:cite:chron:atticrulers:1,0
  """

    val g = GraphFactory.fromCsv(csv)
    val dot =  GraphDotter.dotString(g,  evt2, epochEvt)



val  expected = """digraph"Chronologicalgraph"{
"labelforurn:cite2:chron:epoch:pm"->"labelforurn:cite2:chron:event:2"
"labelforurn:cite2:chron:event:2"->"labelforurn:cite2:chron:epoch:pm"[color="#ff0000"]
"labelforurn:cite2:chron:event:3"->"labelforurn:cite:chron:atticrulers:1"
"labelforurn:cite2:chron:epoch:pm"->"labelforurn:cite2:chron:event:3"
"labelforurn:cite2:chron:event:3"->"labelforurn:cite2:chron:epoch:pm"
"labelforurn:cite:chron:atticrulers:1"->"labelforurn:cite2:chron:event:3"
}"""

  val whitesp = "[ \t]+".r
  assert(whitesp.replaceAllIn(dot, "") == expected)

  }







}
