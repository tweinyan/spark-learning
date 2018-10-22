package sparkstudy.core.tuto

/**
  * Created by tweinyan on 2018/10/18.
  */
object tuto {
  def main(args: Array[String]): Unit = {
//    def sayHello(name: String, age: Int) = {
//      if(age >= 18) {
//        printf("Hi, %s, you are a big boy!!", name)
//      } else printf("Hi, %s, you are a children")
//    }

    def sayHello(name: String, age: Int) = {
      if(age >= 18) {
        printf("Hi, %s, you are a big boy!!\n", name)
      } else printf("Hi, %s, you are a children\n")
      age
    }

//    val age = sayHello("tom", 19)
//    println(age)


    def sum(n: Int) = {
      var result = 0
      for(i <- 1 to n) {
        result += i
      }
      result
    }

    // 递归函数必须手动给出函数的返回类型
    def fab(n: Int): Int = {
      if(n <= 1) 1
      else fab(n - 1) + fab(n - 2)
    }

    // 变长参数
    def sumn(nums: Int*) = {
      var res = 0
      for (num <- nums) res += num
      res
    }

    // 如果想将一个已有的序列直接传给变长参数函数，是不对的。比如val s = sum(1 to 5)
    // 此时需要使用Scala特殊的语法将参数定义为序列，让Scala解释器能够识别。这种语法在spark源码中大量使用
    val s = sumn(1 to 5:_*)

    def sum2(nums: Int*): Int = {
      if(nums.length == 0) 0
      else nums.head + sum2(nums.tail:_*)
    }


//    // 异常处理
//    try {
//      throw new IllegalArgumentException("illegal argument!!!")
//    } catch {
//      case _: IllegalArgumentException => print("sorry, error!!!")
//    } finally {
//      print("\nrelease io resources!!!")
//    }


  }
}
