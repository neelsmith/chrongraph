package edu.holycross.shot.chrongraph
import org.scalatest.FlatSpec

import scala.io.Source

import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

class IntervalFilesSpec extends FlatSpec {


  def catalogFromFile(f: String): Map[String,String] = {
    var catalog = scala.collection.immutable.Map[String,String]()
    val lns = Source.fromFile(f).getLines.toVector.drop(1)
    for (l <- lns) {
      val pr = l.split(",")
      if (pr.size < 2) {
        println("ERROR PARSING " + l)
      } else {
        catalog += (pr(0) -> pr(1))
      }
    }
    catalog
  }
  val catalogFile = "jvm/src/test/resources/clas267-catalog.csv"
  val labels = catalogFromFile(catalogFile)

  val synchronsFile = "jvm/src/test/resources/clas267.csv"


  "A chronological graph"  should "track intervals from loosely structured statements" in   {

    val g = GraphSource(synchronsFile,labels)
    val epochStr = "urn:cite2:chron:epoch:ptol1"
    val actiumStr =  "urn:cite2:chron:jsmv:event:1"
    val verbose = true
    val intervals =  g.sumInterval(actiumStr,epochStr,verbose)



    assert(intervals.size == 2)
    println("\nFinal result:")
    for( k <- intervals.keySet) {
      println(k + ": " + intervals(k))
    }

  }

}
