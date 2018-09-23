package sparkstudy.core.scala

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/22.
  */
object AccumulatorVariable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local")
    val sc = new SparkContext(conf)

    val numberArray = Array(1, 2, 3, 4, 5)
    val numbers = sc.parallelize(numberArray)

    val sum = sc.accumulator(0)

    numbers.foreach(sum.add)
    println(sum.value)

  }
}
