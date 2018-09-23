package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.List;

/**
 * 取最大的前3个数字
 * Created by tweinyan on 2018/9/23.
 */
public class Top3 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("top3").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/top.txt");
        JavaPairRDD<Integer, String> pairs = lines.mapToPair(
                t -> new Tuple2<>(Integer.valueOf(t), t)
        );

        JavaPairRDD<Integer, String> sortedPairs = pairs.sortByKey(false);
        JavaRDD<Integer> sortedNumbers = sortedPairs.map(
                t -> t._1
        );
        List<Integer> top3 = sortedNumbers.take(3);
        for (int num : top3) {
            System.out.println(num);
        }
        sc.close();
    }
}
