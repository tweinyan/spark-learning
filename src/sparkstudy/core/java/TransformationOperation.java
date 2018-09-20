package sparkstudy.core.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tweinyan on 2018/9/19.
 */
public class TransformationOperation {
    public static void main(String[] args) {
//        map();
//        filter);
//        flatMap();
//        groupByKey();
//        reduceByKey();
        sortByKey();
    }
    /**
     * map算子
     */
    private static void map() {
        // 创建SparkConf
        SparkConf conf = new SparkConf().setAppName("map").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 并行化集合，创建初始RDD
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);

        // 使用map算子，将集合中的每个元素都乘以2
        // map算子，是对任何类型的RDD，都可以调用的
        // 在java中，map算子接收的参数是Function对象
        // 创建的Function对象一定会让你设置第二个泛型参数，这个泛型类型，就是返回去的新元素类型
        // 同时call()方法的返回类型，也必须与第二个泛型类型同步
        JavaRDD<Integer> multipleNuberRDD = numberRDD.map(

                new Function<Integer, Integer>() {
                    public Integer call(Integer v) throws Exception {
                        return v * 2;
                    }
                }
        );

        multipleNuberRDD.foreach(
                new VoidFunction<Integer>() {
                    public void call(Integer t) throws Exception {
                        System.out.println(t);
                    }
                }
        );


        sc .close();
    }

    private static void filter() {
        SparkConf conf = new SparkConf().setAppName("filter").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);

        // 对初始RDD使用filter算子，过滤出集合中的偶数
        // filter算子，传入的也是Function，其他的使用注意点，实际上和map是一样的
        // 但是，唯一的不同，就是call()方法的返回类型是Boolean
        // 每一个初始RDD中的元素，都会传入call()方法，此时你可以执行各种自定义的计算逻辑
        // 如果你想在新的RDD中保留这个元素，那么就返回true；否则返回false
        JavaRDD<Integer> evenNumberRDD = numberRDD.filter(
                new Function<Integer, Boolean>() {
                    public Boolean call(Integer n) throws Exception {
//                        if (n % 2 == 0)
//                            return true;
//                        else
//                            return false;
                        return n % 2 == 0;
                    }
                }
        );

        evenNumberRDD.foreach(
                new VoidFunction<Integer>() {
                    public void call(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                }
        );
        sc.close();
    }

    /**
     * flatMap案例，将文本行拆分为多个单词
     */
    private static void flatMap() {
        SparkConf conf = new SparkConf().setAppName("flapMap").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<String> lineList = Arrays.asList("hello you", "hello me", "hello world");

        // 并行化集合，创建RDD
        JavaRDD<String> lines = sc.parallelize(lineList);

        // 对RDD执行flatMap算子，将每一行文本，拆分为多个单词
        // flatMap算子，在java中，接收的参数是FlatMapFunction
        // 我们需要自己定义FlatMapFunction的第二个泛型类型，即，代表了返回的新元素的类型
        // call()方法，返回的类型，不是U，Iterator<U>，这里的U也与第二个泛型类型相同
        // flatMap其实就是，接收原始RDD中的每个元素，并进行各种逻辑的计算和处理，返回可以返回多个元素
        // 多个元素，即封装在Iterable集合中，可以使用ArrayList集合
        // 新的RDD中，即封装了所有的新元素，也就是说，新的RDD的大小一定是>=原始RDD的大小
        JavaRDD<String> words = lines.flatMap(
                new FlatMapFunction<String, String>() {
                    public Iterator<String> call(String s) throws Exception {
                        return Arrays.asList(s.split(" ")).iterator();
                    }
                }
        );
//        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
        words.foreach(word -> System.out.println(word));

        sc.close();
    }

    /**
     * groupByKey案例，按照班级对成绩进行分组
     */
    private static void groupByKey() {
        SparkConf conf = new SparkConf().setAppName("groupByKey").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 模拟集合
        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
                new Tuple2<>("class1", 80),
                new Tuple2<>("class2", 75),
                new Tuple2<>("class1", 90),
                new Tuple2<>("class2", 65)
        );

        // 并行化集合，创建JavaPairRDD
        JavaPairRDD<String, Integer> scores = sc.parallelizePairs(scoreList);

        // 针对scores RDD，执行groupByKey算子，对每个班级的成绩进行分组
        // groupByKey算子，返回的还是JavaPairRDD
        // 但是，JavaPairRDD的第一个泛型类型不变，第二个泛型类型变成Iterable这种集合类型
        // 也就是说，按照了key进行分组，那么每个key可能都会有多个value，此时多个value聚合成了Iterable
        // 那么接下来，那么接下来就可以通过groupedScores这种JavaPairRDD，很方便地处理某个分组内的数据
        JavaPairRDD<String, Iterable<Integer>> groupedScores = scores.groupByKey();

        groupedScores.foreach(t -> {
            System.out.println("class: " + t._1 + "");
            Iterator<Integer> ite = t._2.iterator();
            while (ite.hasNext()) {
                System.out.println(ite.next());
            }
            System.out.println("====================");
        });

        sc.close();
    }

    /**
     * reduceByKey案例，按班级对成绩求和
     */
    private static void reduceByKey() {
        SparkConf conf = new SparkConf().setAppName("reduceByKey").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 模拟集合
        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
                new Tuple2<>("class1", 80),
                new Tuple2<>("class2", 75),
                new Tuple2<>("class1", 90),
                new Tuple2<>("class2", 65)
        );

        // 并行化集合，创建JavaPairRDD
        JavaPairRDD<String, Integer> scores = sc.parallelizePairs(scoreList);

        // 针对scores RDD，执行reduceByKey算子
        // reduceByKey， 接收的参数是Function2类型，它有三个泛型参数，实际上代表了三个值
        // 第一个泛型类型和第二个泛型类型，代表call()方法的两个传入参数的类型
            // 因此对每个key进行reduce，都会依次将第一个、第二个value传入，将值再与第三个value传入
            // 因此此处，会自动定义两个泛型类型，代表call()方法的两个传入参数的类型
        // 第三个泛型类型，代表了每次reduce操作返回值的类型，默认也是与原始RDD的value类型相同的
        // reduceByKey算子返回的RDD，还是JavaPairRDD<key, value>
        JavaPairRDD<String, Integer> totalScores = scores.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }
        );

        totalScores.foreach(
                t -> System.out.println(t._1 + ": " + t._2)
        );
        sc.close();
    }

    // sortByKey案例：按照学生分数进行排序
    private static void sortByKey() {
        SparkConf conf = new SparkConf().setAppName("sortByKey").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 模拟集合
        List<Tuple2<Integer, String>> scoreList = Arrays.asList(
                new Tuple2<>(65, "leo"),
                new Tuple2<>(50, "Tom"),
                new Tuple2<>(100, "marry"),
                new Tuple2<>(80, "jack")
        );

        JavaPairRDD<Integer, String> scores = sc.parallelizePairs(scoreList);

        // 对scores RDD执行sortByKey算子
        // sortByKey其实就是根据key进行排序，可以手动指定升序，或者降序
        // 返回的还是JavaPairRDD，其中的元素内容都是和原始的RDD一模一样的
        // 但是就是RDD中的元素顺序不同了
//        JavaPairRDD<Integer, String> sortedScores = scores.sortByKey();
        JavaPairRDD<Integer, String> sortedScores = scores.sortByKey(false);

        sortedScores.foreach(
                t -> System.out.println(t._1 + ": " + t._2)
        );

        sc.close();
    }
}
