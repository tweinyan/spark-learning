package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

/**
 * action 操作
 * Created by tweinyan on 2018/9/22.
 */
public class ActionOperation {
    public static void main(String[] args) {
//        reduce();
        collect();
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

    private static void collect() {
        // 创建SparkConf和JavaSparkContext
        SparkConf conf = new SparkConf().setAppName("reduce").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面有1到10，10个数字，现在要对10个数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // 使用map操作将集合里的所有数字乘以2
        JavaRDD<Integer> doubleNumbers = numbers.map(
                new Function<Integer, Integer>() {
                    @Override
                    public Integer call(Integer v) throws Exception {
                        return v * 2;
                    }
                }
        );

        // 不用foreach action操作，在远程集群上遍历rdd中的元素
        // 而使用collect操作，将分布在远程集群上的doubleNumbers RDD的数据拉取到本地
        // 这种方式，一般不建议使用，因为如果rdd中的数据量比较大的话，比如超过1万条
            // 那么性能会比较差，因为要从远程走大量的网络传输，将数据获取到本地
            // 此外，除了性能差，还可能在rdd中数据量特别大的情况下，发生oom异常，内存溢出
        // 因此，通常还是推荐使用foreach action 操作，来对最终的rdd元素进行处理
        List<Integer> doubleNumberList = doubleNumbers.collect();
        for (Integer num : doubleNumberList) {
            System.out.println(num);
        }

        sc.close();
    }
}
