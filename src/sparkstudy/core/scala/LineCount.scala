package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/19.
  */
object LineCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("LineCount").setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/hello.text")
    val paris = lines.map(line => (line, 1))
    val lineCounts = paris.reduceByKey(_ + _)
    lineCounts.foreach(lineCount => println(lineCount._1 + " appears " + lineCount._2 + " times "))
  }
}
