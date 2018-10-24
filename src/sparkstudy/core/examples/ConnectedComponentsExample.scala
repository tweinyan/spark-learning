package sparkstudy.core.examples

import org.apache.spark.sql.SparkSession
import org.apache.spark.graphx.GraphLoader

/**
  * Created by tweinyan on 2018/10/22.
  */
object ConnectedComponentsExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("ConnectedComponents")
      .master("local")
      .getOrCreate()

    val sc = spark.sparkContext
    val graph = GraphLoader.edgeListFile(sc, "/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/followers.txt")
    val cc = graph.connectedComponents().vertices

    val users = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ccByUsername = users.join(cc).map {
      case (id, (username, cc)) => (username, cc)
    }

    println(ccByUsername.collect().mkString("\n"))

//    cc.foreach(println)
//    println("------------")
//    graph.vertices.foreach(println)
  }
}
