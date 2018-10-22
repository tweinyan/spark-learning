package sparkstudy.core.tuto

import runtime.ScalaRunTime.replStringOf

/**
  * Created by tweinyan on 2018/10/21.
  */
object ArrayExample {
  def main(args: Array[String]): Unit = {
    // array 长度不可变
    // 数组初始化后，长度固定，元素根据其类型初始化

    val a = new Array[Int](10)
//    println(replStringOf(a, 2))

//    val repl = replStringOf(_:Any, _:Int)
//    println(repl(a, 20))

//    println(a.deep.toString())

    // 如果需要类似java的ArrayList这种长度可变的集合类，可以使用ArrayBuffer
    import scala.collection.mutable.ArrayBuffer

    // 创建一个空的ArrayBuffer
    val b = ArrayBuffer[Int]()
    // 使用+=操作符，可以添加一个或多个元素
    b += 1
    b += (2, 3, 4, 5)
    // 使用++=操作符，可以添加其他集合汇总的所用元素
    b ++= Array(6, 7, 8, 9)

    // 尾部截断
//    b.trimEnd(5)

    //insert() 指定位置插入元素，效率很低
    b.insert(5, 6)
    b.insert(6, 7, 8, 9)

    //remove()指定位置元素
    b.remove(1)
    b.remove(1, 3)
//    println(b)

    //Array可与ArrayBuffer互相转换
    b.toArray
    a.toBuffer

//    //使用for循环和until遍历Array/ArrayBuffer
//    //until是RichInt提供的函数
//    for(i <- 0 until b.length)
//      println(b(i))
//
//    //跳跃遍历
//    for(i <- 0 until (b.length, 2))
//      println(b(i))
//
//    //从尾部遍历
//    for(i <- b.indices.reverse)
//      println(b(i))
//
//    //增加for循环
//    for(e <- b)
//      println(e)


    //求和
    val a1 = Array(1, 2, 3, 4, 5)
    val sum = a1.sum

    val max = a1.max

    //sort
//    scala.util.Sorting.quickSort(a1)
//
//    //获取数组中所有元素内容
//    println(a1.mkString)
//    println(a1.mkString(","))
//    println(a1.mkString("<", ",", ">"))
//
//    println(a1.toString)
//    //ArrayBuffer
//    println(b.toString)


    //使用yield和函数式编程转换数组
    //对Array进行转换，获取的还是Array
    val arr = Array(1, 2, 3, 4, 5)
    val arr1 = for(ele <- a) yield ele * ele

    //对ArrayBuffer进行转换，获取的还是ArrayBuffer
    val b1 = ArrayBuffer[Int]()
    b += (1, 2, 3, 4, 5)
    val b2 = for(ele <- b) yield ele * ele
    //if守卫
    val a3 = for(ele <- b if ele % 2 == 0) yield ele * ele

    //函数式编程转换数组
    arr.filter(_ % 2 == 0).map(2 * _)
    arr.filter{_ % 2 == 0} map {2 * _}

//    arr.filter(_ % 2 == 0).map(p => p * p)

  }
}
