package sparkstudy.core.tuto

/**
  * Created by tweinyan on 2018/10/25.
  */

/**
  * 伴生对象
  * 如果有一个class，还有一个与class同名的object，那么就称这个object是class的伴生对象，
  * class是object的伴生类
  * 伴生类和伴生对象必须放在一个.scala文件之中
  * 伴生类和伴生对象，最大特点就在于，互相可以访问private field
  */
//class Person(val name: String, val age: Int) {
//  def sayHello = println("Hi, " + name + ", I guess you are " + age + " years old!"
//  + " and usually you must have " + Person.eyeNum + " eyes.")
//}
//
//object Person {
//  private val eyeNum = 2
//  println("this Person object!")
//
//  def getEyeNum = eyeNum
//}


/**
  * 让object继承抽象类
  * object的功能其实和class类似，除了不能定义接受参数的constructor之外
  * object也可以继承抽象类，并覆盖抽象类中的方法
  */
//abstract class Hello(var message: String) {
//  def sayHello(name: String): Unit
//}
//
//object HelloImpl extends Hello("hello") {
//  override def sayHello(name: String): Unit = {
//    println(message + ", " + name)
//  }
//}


/**
  * apply方法
  * object中非常重要的一个特殊方法，就是apply方法
  * 通常在伴生对象中实现apply方法，并在其中实现构造伴生类的对象的功能
  * 而创建伴生类的对象时，通常不会使用new Class的方式，而是使用Class()的方式，
  * 隐式地调用伴生对象的apply方法，这样会让对象创建更加简洁
  * 
  * 比如，Array类的伴生对象的apply方法就实现了接受可变数量的参数，并创建一个Array对象的功能
  * val a = Array(1, 2, 3, 4, 5)
  * 
  * 比如，定义自己的伴生类和伴生对象
  */
//class Person(val name: String)
//object Person {
//  def apply(name: String) = new Person(name)
//}


/**
  * main方法
  * 就如同java中，如果要运行一个程序，必需编写一个包含main方法类一样；在scala中，
  * 如果要运行一个应用程序，那么必须有一个main方法，作为入口
  * scala中的main方法定义为def main(args: Array[string])，而且必须定义在object中
  *
  * 除了自己实现main方法之外，还可以继承App Trait，然后将需要在main方法中运行的代码
  * 直接作为object的constructor代码；而且用args可以接受传入的参数
  */
//object HelloWorld extends App {
//  if (args.length > 0) println("hello, " + args(0))
//  else println("Hello World!")
//}


/**
  * 用object来实现枚举功能
  * Scala没有直接提供类似于Java的Enum这样的枚举特性，如果要实现枚举，则需要用object继承
  * Enumeration类，并且调用Value方法来初始化枚举值
  */
object Seanson extends Enumeration {
  val SPRING, SUMMER, AUTUMN, WINTER = Value
}

/**
  * 还可以通过Value传入枚举值的id和name，通过id和toString可以获取；还可以通过id和name来查找枚举值
  * Seanson(0)
  * Seanson.withName("spring")
  * for (ele <- Season.values) println(ele)
  * Seanson.SPRING.id
  * Seanson.SPRING.toString
  */
object Season extends Enumeration {
  val SPRING = Value(0, "spring")
  val SUMMER = Value(1, "summer")
  val AUTUMN = Value(2, "autumn")
  val WINTER = Value(3, "winter")
}

