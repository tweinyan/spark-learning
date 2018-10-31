package sparkstudy.core.tuto

import scala.collection.mutable

/**
  * Created by tweinyan on 2018/10/22.
  */
object MapTuple {
  def main(args: Array[String]): Unit = {
    // 创建一个不可变的Map
    val ages = Map("Leo" -> 30, "jen" -> 25, "Jack" -> 23)
    // ("Leo") = 31

    val ages1 = scala.collection.mutable.Map("Leo" -> 30, "jen" -> 25, "Jack" -> 23)
    ages1("Leo") = 31

    // 使用另外一种方式定义Map元素
    val ages2 = Map(("Leo", 30), ("Jen", 25), ("Jack", 23))

    // 创建一个空的HashMap
    val ages3 = new mutable.HashMap[String, Int]

    // 获取指定key对应的value，如果key不存在，会报错
    val leoAge = ages("Leo")
    //    val leoAge = ages("leo")

    // 使用contains函数
    val age = if (ages.contains("leo")) ages("leo") else 0
    // getOrElse函数
    val leoAge1 = ages.getOrElse("leo", 0)


    // 更新Map的元素
    ages1("Leo") = 31
    // 增加多个元素
    ages1 += ("Mike" -> 35, "Tom" -> 40)
    // 移除元素
    ages1 -= "Mike"

    // 更新不可变的map
    val r = ages + ("Mike" -> 36, "Tom" -> 40)
    //    val r = ages + ("Leo" -> 31)
    // 移除不可变map的元素
    val r1 = ages - "Tom"
    //    println(r)
    //    println(r1)


    // 遍历map的entrySet
    for ((key, value) <- ages) println(key + " " + value)

    // 遍历map的key
    for (key <- ages.keySet) println(key)

    // 遍历map的value
    for (value <- ages.values) println(value)

    // 生成新map，反转key和value
    for ((key, value) <- ages) yield (value, key)

    // SortedMap可以自动对Map的key排序
    val ages4 = scala.collection.immutable.SortedMap(
      "leo" -> 30,
      "alice" -> 15,
      "jen" -> 25
    )

    // LinkedHashMap可以记住插入entry的顺序
    val agesMap = new scala.collection.mutable.LinkedHashMap[String, Int]
    agesMap("leo") = 30
    agesMap("alice") = 15
    agesMap("jen") = 25

  }
}
