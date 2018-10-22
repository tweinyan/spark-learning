package sparkstudy.core.tuto

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tweinyan on 2018/10/22.
  * 案例：移除第一个负数之后的所有负数
  */
object ex1 {
  def main(args: Array[String]): Unit = {
    val a = ArrayBuffer[Int]()
    a += (1, 2, 3, 4, 5, -1, -3, -5, -9)

    //每发现一个第一个负数之后的负数，就进行移除，性能较差，多次移动数组
    def func1(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
      var foundFirstNegative = false
      var arrayLength = a.length
      var index = 0
      while (index < arrayLength) {
        if (a(index) >= 0) {
          index += 1
        } else {
          if (!foundFirstNegative) {
            foundFirstNegative = true;
            index += 1
          }
          else {
            a.remove(index);
            arrayLength -= 1
          }
        }
      }
      a
    }

    //记录所有不需要移除的元素的索引，稍后一次性移除所有需要移除的元素
    //性能较高，数组内的元素迁移只需要执行一次即可
    def func2(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
      var foundFirstNegative = false
      val keepIndexs = for (i <- 0 until a.length if !foundFirstNegative || a(i) >= 0) yield {
        if (a(i) < 0) foundFirstNegative = true
        i
      }
      for (i <- 0 until keepIndexs.length) {
        a(i) = a(keepIndexs(i))
      }
      a.trimEnd(a.length - keepIndexs.length)
      a

//      keepIndexs.to[ArrayBuffer]
    }

    val a1 = func2(a)
    println(a1)
  }
}
