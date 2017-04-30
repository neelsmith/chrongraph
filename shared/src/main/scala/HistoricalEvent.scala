package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class HistoricalEvent(id: String, label: String) {
  override  def toString = label


  override def equals(evt: Any): Boolean = {
    evt match {
      case evt: HistoricalEvent => evt.id == this.id
      case _ => false
    }
  }
  override def hashCode = id.##

}
