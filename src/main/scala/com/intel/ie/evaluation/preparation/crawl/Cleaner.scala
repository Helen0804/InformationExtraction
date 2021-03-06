package com.intel.ie.evaluation.preparation.crawl

import com.intel.ie.devUtils.RegexNerTest

import scala.collection.JavaConverters._

/**
  * Created by xianyan on 7/28/16.
  */
object Cleaner {

  def isStartOfContent(line: String): Boolean = {
    val list = RegexNerTest.extractNER(line).asScala.filter(_ != "O")
    if (!list.isEmpty) {
      if (list.contains("TITLE") || list.contains("PERSON")) return true
    }
    return false
  }


  def clean(content: String): String = {
    val lines = content.split("\\n").toList
    var res = ""
    for (line <- lines) {
      if (isStartOfContent(line)) {
        res += "\n" + line
      }
    }
    res
  }

  def containsMultiplePerson(parentStr: String): Boolean = {
    val list = RegexNerTest.extractNER(parentStr).asScala.filter(_ != "O")
    if (!list.isEmpty) {
      if (list.contains("TITLE") || list.contains("PERSON")) return true
    }
    return false
  }

}
