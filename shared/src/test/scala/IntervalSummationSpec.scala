package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec



import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class IntervalSummationSpec extends FlatSpec {

  val labels = Map(
    "urn:cite2:chron:epoch:ptol1" -> "Epoch of Nabonassaar in Ptolemy's Canon of Kings",
    "urn:cite2:chron:epoch:ptol2" -> "Epoch of Philip in Ptolemy's Canon of Kings",
    "urn:cite2:chron:epoch:pm" -> "Epoch of the Parian Marble",
    "urn:cite2:chron:atticrulers:1" -> "Kingship of Cecrops in Athens",
    "urn:cite2:chron:event:3" -> "Deucalion king in Lykoreia",
    "urn:cite2:chron:event:22"  -> "Apsephion archon in Athens",
    "urn:cite2:chron:event:22a"  -> "Sophocles wins prize in tragedy",
    "urn:cite2:chron:event:22b"  -> "Sophocles born",
    "urn:cite2:chron:event:15"  -> "Aristides archon in Athens",
    "urn:cite2:chron:jer_persians:ruler4"  -> "Xerxes becomes king"

  )

  val csv = """urn:cts:chronepig:chron.pm:1,urn:cite2:chron:atticrulers:1,precedes,epoch,urn:cite2:chron:epoch:pm,1318
  urn:cts:chronepig:chron.pm:2,urn:cite2:chron:event:3,precedes,epoch,urn:cite2:chron:epoch:pm,1310
  urn:cts:chronepig:chron.pm.hc:a.56,urn:cite2:chron:event:22,precedes,epoch,urn:cite2:chron:epoch:pm,206
  urn:cts:chronepig:chron.pm.hc:a.56,urn:cite2:chron:event:22a,precedes,epoch,urn:cite2:chron:epoch:pm,206
  urn:cts:chronepig:chron.pm.hc:a.56,urn:cite2:chron:event:22b,precedes,years,urn:cite2:chron:event:22a,28
  urn:cts:chronepig:chron.pm.hc:a.49,urn:cite2:chron:event:15,precedes,epoch,urn:cite2:chron:epoch:pm,226
  urn:cts:chronepig:chron.pm.hc:a.49,urn:cite2:chron:jer_persians:ruler4,precedes,epoch,urn:cite2:chron:epoch:pm,226
  urn:cts:chronepig:chron.pm.hc:a.49,urn:cite2:chron:epoch:pm,follows,epoch,urn:cite2:chron:jer_persians:ruler4,226
  PTOL CANON,urn:cite2:chron:jer_persians:ruler4,follows,epoch,urn:cite2:chron:epoch:ptol1,262
  """


  "A chronological graph"  should "create a map of systems to counts" in {
    val g = GraphFactory.fromCsv(csv,labels)
    val epochStr = "urn:cite2:chron:epoch:ptol1"
    val tragStr =  "urn:cite2:chron:event:22a"

    val epoch = g findEvtById epochStr
    val trag = g findEvtById   tragStr
    val intervals =  g.sumInterval(tragStr,epochStr)
    assert(intervals.size == 1)
    assert(intervals("epoch") == 694)

  }


}
