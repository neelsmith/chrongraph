package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class HistoricalEvent(id: String, label: String) {
  override  def toString = label

/*
  override def equals(evt: HistoricalEvent): Boolean = {
    (this.id == evt.id)
  }
*/
}
