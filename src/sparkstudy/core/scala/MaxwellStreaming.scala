package sparkstudy.core.scala

/**
  * Created by tweinyan on 2018/7/11.
  */
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010._

object MaxwellStreaming {
  def main(args: Array[String]): Unit = {
    val brokers = "localhost:9092"
    val groupId = "consumer-group"
    val topics = "maxwell"

    val sparkConf = new SparkConf().setAppName("StreamingKafkaTest").setMaster("local")
//      val sparkConf = new SparkConf().setAppName("StreamingKafkaTest")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
      ConsumerConfig.GROUP_ID_CONFIG -> groupId,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer]
    )
    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams)
    )

    val lines = messages.map(_.value)
    //    val words = lines.flatMap(_.split(" "))
    //    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    //    wordCounts.print()
    lines.print()
    ssc.start()
    ssc.awaitTermination()
  }
}

