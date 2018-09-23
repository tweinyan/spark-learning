package sparkstudy.core.java;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tweinyan on 2018/9/22.
 */
public class AccumulatorVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("accumulator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 创建Accumulator变量
        // 需要调用SparkContext的accumulator()方法
        Accumulator<Integer> sum = sc.accumulator(0);
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        numbers.foreach(
                // 然后在函数内部，就可以对Accumulator变量，调用add()方法，累加值
//               t -> sum.add(t)
                sum::add
        );

        // 在driver程序中，可以调用Accumulator的value()方法，获取值
        // Accumulator变量只在driver程序中可读
        System.out.println(sum.value());

        sc.close();
    }
}
