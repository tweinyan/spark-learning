package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/19.
  */
object TransformationOperation {
  def main(args: Array[String]): Unit = {
//    filter()
//    flatMap()
//    groupByKey()
    reduceByKey()
  }

  def map(): Unit = {
    val conf = new SparkConf().setAppName("map").setMaster("local")
    val sc = new SparkContext(conf)
    val numbers = Array(1, 2, 3, 4, 5)
    val numberRDD = sc.parallelize(numbers, 1)
    val multipleNumberRDD = numberRDD.map(num => num * 2)

    multipleNumberRDD.foreach(println)
  }

  def filter(): Unit = {
    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)
    val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 22)
    val numberRDD = sc.parallelize(numbers, 1)
    val evenNumberRDD = numberRDD.filter(n => n % 2 == 0)

    evenNumberRDD.foreach(println)
  }

  def flatMap(): Unit = {
    val conf = new SparkConf().setAppName("flatMap").setMaster("local")
    val sc = new SparkContext(conf)
    val lineArray = Array("hello you", "hello me", "hello world")
    val lines = sc.parallelize(lineArray, 1)
    val words = lines.flatMap(line => line.split(" "))
    words.foreach(println)
  }

  def groupByKey(): Unit = {
    val conf = new SparkConf().setAppName("groupByKey").setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(
      ("class1", 80),
      ("class2", 75),
      ("class1", 90),
      ("class2", 65)
    )
    val scores = sc.parallelize(scoreList, 1)
    val groupedScores = scores.groupByKey()
    groupedScores.foreach(t => {
      println("class: " + t._1 + " ")
      t._2.foreach(println)
      println("====================")
    })
  }

  def reduceByKey(): Unit = {
    val conf = new SparkConf().setAppName("reduceByKey").setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(
      ("class1", 80),
      ("class2", 75),
      ("class1", 90),
      ("class2", 65)
    )
    val scores = sc.parallelize(scoreList, 1)
    val totalScores = scores.reduceByKey(_ + _)
    totalScores.foreach(
      t => println(t._1 + ": " + t._2)
    )
  }
}
