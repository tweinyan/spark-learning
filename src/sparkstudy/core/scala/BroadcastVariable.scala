package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/22.
  */
object BroadcastVariable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("broadcast").setMaster("local")
    val sc = new SparkContext(conf)

    val numberArray = Array(1, 2, 3, 4, 5)
    val numbers = sc.parallelize(numberArray)

    val factor = 3
    val factorBroadcast = sc.broadcast(factor)

    val multipleNumbers = numbers.map(n => n * factorBroadcast.value)
    multipleNumbers.foreach(println)
  }
}
