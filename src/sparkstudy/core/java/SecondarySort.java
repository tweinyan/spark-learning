package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 * 二次排序
 * 1. 实现自定义的key，要实现Ordered接口和Serializable接口，在key中实现自己对多个列的排序算法
 * 2. 将包含文本的RDD，映射成key为自定义key，value为文本的JavaPairRDD
 * 3. 使用sortByKey算子按照自定义的key进行排序
 * 4. 再次映射，剔除自定义的key，只保留文本行
 * Created by tweinyan on 2018/9/22.
 */
public class SecondarySort {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("SecondarySort").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/sort.txt");

        JavaPairRDD<SecondarySortKey, String> pairs = lines.mapToPair(
                line -> {
                    String[] lineSplited = line.split(" ");
                    SecondarySortKey key = new SecondarySortKey(
                            Integer.valueOf(lineSplited[0]),
                            Integer.valueOf(lineSplited[1])
                    );
                    return new Tuple2<>(key, line);
                }
        );

        JavaPairRDD<SecondarySortKey, String> sortedPairs = pairs.sortByKey();
        JavaRDD<String> sortedLines = sortedPairs.map(
                t -> t._2
        );
        sortedLines.foreach(
                t -> System.out.println(t)
        );
        sc.close();
    }
}
