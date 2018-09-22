package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/22.
  */
object ActionOperation {
  def main(args: Array[String]): Unit = {
    //    reduce()
    //    collect()
    //    count()
    //    take()
    countByKey()
  }

  def reduce(): Unit = {
    val conf = new SparkConf().setAppName("reduce").setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val sum = numbers.reduce(_ + _)
    println(sum)
  }

  def collect(): Unit = {
    val conf = new SparkConf().setAppName("collect").setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)

    val doubleNumbers = numbers.map(num => num * 2)
    val doubleNumberArray = doubleNumbers.collect()
    //    doubleNumberList.foreach(println)
    for (num <- doubleNumberArray) {
      println(num)
    }
  }

  def count(): Unit = {
    val conf = new SparkConf().setAppName("count").setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)

    val count = numbers.count()
    println(count)
  }

  def take(): Unit = {
    val conf = new SparkConf().setAppName("take").setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)

    val top3Number = numbers.take(3)
    for (num <- top3Number) {
      println(num)
    }
  }

  def countByKey(): Unit = {
    val conf = new SparkConf().setAppName("take").setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(
      ("class1", "leo"),
      ("class2", "jack"),
      ("class1", "marry"),
      ("class2", "tom"),
      ("class2", "david")
    )
    val students = sc.parallelize(scoreList)
    val studentCounts = students.countByKey()
    for (t <- studentCounts) {
      println(t._1 + ": " + t._2)
    }

  }
}
