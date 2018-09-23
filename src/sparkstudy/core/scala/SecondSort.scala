package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/23.
  */
object SecondSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("SecondSort")
      .setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/sort.txt", 1)
    val pairs = lines.map(
      line => (new SecondSortKey(line.split(" ")(0).toInt, line.split(" ")(1).toInt), line)
    )
    val sortedPairs = pairs.sortByKey()
    val sortedLines = sortedPairs.map(t => t._2)
    sortedLines.foreach(println)
  }
}
