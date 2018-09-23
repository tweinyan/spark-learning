package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/22.
  */
object SortWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SortWordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/hello.text")
    val words = lines.flatMap(t => t.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    val countWords = wordCounts.map(t => (t._2, t._1))
    val sortCountWords = countWords.sortByKey(false)
    val sortWordCounts = sortCountWords.map(t => (t._2, t._1))
    sortWordCounts.foreach(
      t => println(t._1 + ": " + t._2)
    )

    // 不用来回反转
//    val res = wordCounts.sortBy(_._2, false)
//    res.foreach(
//      t => println(t._1 + ": " + t._2)
//    )
  }
}
