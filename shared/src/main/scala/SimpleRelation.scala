package edu.holycross.shot.chrongraph


import scala.scalajs.js
import js.annotation.JSExport

@JSExport case class SimpleRelation(rel: String, amt: Int, sys: String, src: String) {
  override def toString = {
    s"According to ${src}, ${rel} by ${amt} ${sys}"
  }
}
