package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/22.
  */
object ActionOperation {
  def main(args: Array[String]): Unit = {
//    reduce()
    collect()
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
    val conf = new SparkConf().setAppName("reduce").setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)

    val doubleNumbers = numbers.map(num => num * 2)
    val doubleNumberList = doubleNumbers.collect()
    doubleNumberList.foreach(println)
  }
}
