package sparkstudy.core.tuto

/**
  * Created by tweinyan on 2018/10/24.
  * 1. 定义一个简单的类
  * 2. field的getter与setter详解
  * 3. constructor详解
  * 4. 内部类介绍
  */

object ClassExample {
  def main(args: Array[String]): Unit = {
    val helloWorld = new HelloWorld
    helloWorld.sayHello()
    println(helloWorld.getName)
  }
}

