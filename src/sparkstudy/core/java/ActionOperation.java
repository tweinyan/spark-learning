package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

/**
 * action 操作
 * Created by tweinyan on 2018/9/22.
 */
public class ActionOperation {
    public static void main(String[] args) {
        reduce();
    }

    private static void reduce() {
        // 创建SparkConf和JavaSparkContext
        SparkConf conf = new SparkConf().setAppName("reduce").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面有1到10，10个数字，现在要对10个数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // 使用reduce对集合中的数字进行累加
        // reduce操作的原理：
        // 首先将第一个和第二个元素，传入call()方法，进行计算，会获取一个结果，比如1 + 2 = 3
        // 接着将该结果与下一个元素传入call()方法，进行计算，比如3 + 3 = 6
        // 以此类推
        // 所以reduce操作的本质就是聚合，将多个元素聚合成一个元素
        int sum = numbers.reduce(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }
        );


        System.out.println(sum);

        // 关闭JavaSparkContext
        sc.close();
    }
}
