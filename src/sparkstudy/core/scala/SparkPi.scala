package sparkstudy.core.scala

import scala.math.random

import org.apache.spark.sql.SparkSession

object SparkPi {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("test")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext

    var counter = 0
    val data = Array(1, 2, 3, 4, 5)
    var rdd = sc.parallelize(data)

    rdd.foreach(x => counter += 2)
    rdd.foreach(println)

    spark.stop()
  }
}
