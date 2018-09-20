package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * 统计每行出现的次数
 * Created by tweinyan on 2018/9/19.
 */
public class LineCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("LineCount").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        JavaRDD<String> lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/hello.text");

        JavaPairRDD<String, Integer> pairs = lines.mapToPair(

                new PairFunction<String, String, Integer>() {

                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2<String, Integer>(s, 1);
                    }
                });

        JavaPairRDD<String, Integer> lineCounts = pairs.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    public Integer call(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }
        );

        // action
        lineCounts.foreach(
                new VoidFunction<Tuple2<String, Integer>>() {
                    public void call(Tuple2<String, Integer> t) throws Exception {
                            System.out.println(t._1 + " appears " + t._2 + " times. ");
                        }
                }
        );

        sc.close();
    }
}
