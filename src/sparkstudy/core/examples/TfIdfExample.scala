package sparkstudy.core.examples

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{HasingTF, IDF, Tokenizer}

/**
  * Created by tweinyan on 2018/10/26.
  */
object TfIdfExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("TfIdfExample")
      .master("local")
      .getOrCreate()

    val sentenceData = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark"),
      (0.0, "I wish Java could use case classes"),
      (1.0, "Logistic regression models are neat")
    )).toDF("label", "sentence")

    val tokenizer = new Tokenizer

  }
}
