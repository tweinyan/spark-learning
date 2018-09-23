package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 分组取top3，每个班级的成绩前三名
 * Created by tweinyan on 2018/9/23.
 */
public class GroupTop3 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("groupTop3").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("/Users/tweinyan/IdeaProjects/sparktest/src/sparkstudy/core/resources/score.txt");
        JavaPairRDD<String, Integer> pairs = lines.mapToPair(
                t -> {
                    String[] lineSplited = t.split(" ");
                    return new Tuple2<>(lineSplited[0], Integer.valueOf(lineSplited[1]));
                }
        );
        JavaPairRDD<String, Iterable<Integer>> groupedPairs = pairs.groupByKey();
        JavaPairRDD<String, Iterable<Integer>> top3Score = groupedPairs.mapToPair(
                t -> {
                    Integer[] top3 = new Integer[3];
                    String className = t._1;
                    Iterator<Integer> scores = t._2.iterator();
                    while (scores.hasNext()) {
                        Integer score = scores.next();

                        for (int i = 0; i < 3; i++) {
                            if (top3[i] == null) {
                                top3[i] = score;
                                break;
                            } else if (score > top3[i]) {
//                                Integer tmp = top3[i];
//                                top3[i] = score;
//
//                                if (i < top3.length - 1) {
//                                    top3[i + 1] = tmp;
//                                }

                                for (int j = 2; j > i; j--) {
                                    top3[j] = top3[j - 1];
                                }
                                top3[i] = score;
                                break;
                            }
                        }
                    }
                    return new Tuple2<>(className, Arrays.asList(top3));
                }
        );

        top3Score.foreach(
                t -> {
                    System.out.println("Class: " + t._1);
//                    Iterator<Integer> scoreIterator = t._2.iterator();
                    for (int i : t._2) {
                        System.out.println(i);
                    }
                    System.out.println("====================");
                }
        );

        sc.close();
    }
}
