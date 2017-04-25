package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._

//import implicits._


// Factory for creating Graph structures from text sources.
@JSExport object GraphFactory {


//1. Source of evidence
// Event to date	Relation
// Type of related object
// Related event or chronological object
//Units of difference


  /** Create directed edge from string of csv data.
  *
  * @param s String of CSV data.
  */
  def edgeFromCsv(s: String): LDiEdge[HistoricalEvent] = {
      val columns = s.split(",")


      val sourceEvent = HistoricalEvent(columns(1), "label for " + columns(0))
      val targetEvent = HistoricalEvent(columns(4), "label for " + columns(4))

      val src = columns(0)
      val relation = columns(2)
      val relationType  = columns(3)
      val unitsDiff = columns(5).toInt

      val simpleRelation = SimpleRelation(relationType,unitsDiff,relationType,src)
      LDiEdge(sourceEvent,targetEvent)(simpleRelation)



/*
Event,Synchronism,Source,Relation,SyncType,SyncedWith,Amount,Label,Source
urn:cite:chron:event.2,urn:cite:chron:synchronism.2,urn:cts:chronepig:chron.pm:1,precedes,epoch,urn:cite:chron:epoch.pm,1318,Reign of Cecrops in Athens,urn:cts:chronepig:chron.pm:1
urn:cite:chron:event.3,urn:cite:chron:synchronism.3,urn:cts:chronepig:chron.pm:2,precedes,epoch,urn:cite:chron:epoch.pm,1310,Reign of Deucalion near Parnassus in Lycoria,urn:cts:chronepig:chron.pm:2
urn:cite:chron:event.3,urn:cite:chron:synchronism.4,urn:cts:chronepig:chron.pm:79,contemporary,eponym,urn:cite:chron:archon.1,0,Reign of Deucalion near Parnassus in Lycoria,urn:cts:chronepig:chron.pm:2
*/
  }

}
