package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/23.
  */
object Top3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("top3").setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/top.txt")
    val pairs = lines.map(
      t => (t.toInt, t)
    )
    val sortedNumbers = pairs.sortByKey(false).map(t => t._2)
    val top3 = sortedNumbers.take(3)
    top3.foreach(println)
  }
}
