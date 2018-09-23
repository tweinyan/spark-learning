package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * 排序的wordcount程序
 * Created by tweinyan on 2018/9/22.
 */
public class SortWordCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("SortWordCount").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 创建lines RDD
        JavaRDD<String> lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/hello.text");
        // 执行之前的单词计数
        JavaRDD<String> words = lines.flatMap(
                t -> Arrays.asList(t.split(" ")).iterator()
        );
        JavaPairRDD<String, Integer> pairs = words.mapToPair(
                t -> new Tuple2<>(t, 1)
        );
        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(
                (a, b) -> (a + b)
        );
        // 到这里为止，就的到了每个单词出现的次数
        // 但是，问题是，我们的新需求，是要按照每个单词出现次数的顺序，降序排序
        // wordCounts RDD 内元素格式？ (hello, 3) (you, 2)
        // 我们需要将RDD转换成(3, hello) (2, you)的这种格式，才能根据出现次数排序

        // 进行key-value的反转映射
        JavaPairRDD<Integer, String> countWords = wordCounts.mapToPair(
                t -> new Tuple2<>(t._2, t._1)
        );
        // 按照key进行排序
        JavaPairRDD<Integer, String> sortCountWords = countWords.sortByKey(false);

        // 再次将value-key进行反转映射
        JavaPairRDD<String, Integer> sortWordCounts = sortCountWords.mapToPair(
                t -> new Tuple2<>(t._2, t._1)
        );
        // 到此为止，我们获得了按照单词出现次数排序后的单词计数
        // 打印
        sortWordCounts.foreach(
                t -> System.out.println(t._1 + ": " + t._2)
        );

        sc.close();
    }
}
