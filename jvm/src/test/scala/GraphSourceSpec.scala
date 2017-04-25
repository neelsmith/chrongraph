package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec


class GraphSourceSpec extends FlatSpec {

  "A graph source"  should "create a chronological graph from a CSV file" in  {
    val f = "jvm/src/test/resources/pm.csv"
    val g = GraphSource(f)
    g match {
      case cg : ChronologicalGraph => assert(true)
      case _ => fail("Should have created a chronological graph")
    }

  }




}
