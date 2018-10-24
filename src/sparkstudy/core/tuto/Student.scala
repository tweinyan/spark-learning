package sparkstudy.core.tuto


/**
  * Created by tweinyan on 2018/10/24.
  */

// 定义不带private的var field，此时scala生成的面向JVM的类时，会定义为private的name字段，并提供public的getter和setter方法
// 如果使用private修饰field，则生成的getter和setter也是private的
// 如果定义val field，则只会生成getter方法
// 如果不希望生成setter和getter方法，则将field声明为private[this]

// 如果只是希望拥有简单的getter和setter方法，name就按照scala提供的语法规则，根据需求为field选择合适的修饰符就好：var, val, private, private[this]
// 如果希望能够自己对getter与setter进行控制，则可以自定义getter与setter方法
// 自定义setter方法的时候一定要注意scala的语法限制，签名、=、参数间不能有空格

// 如果不希望field有setter方法，则可以定义为val，但是此时就再也不能更改field的值了
// 但是如果希望能够仅仅暴露出一个getter方法，并且还能通过某些方法更改field的值，name需要综合使用private以及自定义getter方法
// 此时，由于field是private的，所以setter和getter都是private，对外界没有暴露；自己可以实现修改field值的方法；自己可以覆盖getter方法

//class Student {
//  //  var name = "leo"
//
//  //  private var myName = "leo"
//  //  def name = "your name is " + myName
//  //  def name_= (newValue: String): Unit = {
//  //    println("you cannot edit your name!")
//  //  }
//
//  private var myName = "leo"
//  def updateName(newName: String): Unit = {
//    if(newName == "leo1") myName = newName
//    else println("not accept this new name!!!")
//  }
//  def name = "your name is " + myName
//
//
//}


// 如果将field使用private来修饰，那么代表这个field是类私有的，在类的方法中，可以直接访问类的其他对象的private field
// 这种情况下，如果不希望field被其他对象访问到，那么可以使用private[this]，意味着对象私有的field，只有本对象内可以访问到
//class Student {
//  private var myAge = 0
////  private[this] var myAge = 0
//
//  def age_= (newValue: Int): Unit = {
//    if (newValue > 0) myAge = newValue
//    else println("illegal age!")
//  }
//  def age = myAge
//  def older(s: Student) = {
//    myAge > s.myAge
//  }
//}


// Scala的getter和setter方法的命名与java是不同的，是field和field_=的方式
// 如果要让scala自动生成java风格的getter和setter方法，只要给field添加@BeanProperty注解即可
// 此时会生成4个方法，name: String, name_=(newValue: String): Unit, getName(): String, setName(newValue: String): Unit

//import scala.beans.BeanProperty
//class Student {
//  @BeanProperty
//  var name: String = _
//}
//class Student(@BeanProperty var name: String)


/**
  * 辅助constructor
  * Scala中，可以给类定义多个辅助constructor，类似于java中的构造函数重载
  * 辅助constructor之间可以互相调用，而且必须第一行调用主constructor
  */
//class Student {
//  private var name = ""
//  private var age = 0
//  def this(name: String) {
//    this()
//    this.name = name
//  }
//  def this(name: String, age: Int) {
//    this(name)
//    this.age = age
//  }
//}

/**
  * 主构造函数
  * Scala中，主constructor是与类名放在一起的，与java不同
  * 而且类中，没有定义在任何方法或者是代码块之中的代码，就是主constructor的代码，
  */
//class Student(val name: String, val age: Int) {
//  println("your name is " + name + ", your age is " + age)
//}

/**
  * 主constructor中还可以通过使用默认参数，来给参数默认的值
  */
//class Student(val name: String = "leo", val age: Int = 30) {
//  println("your name is " + name + ", your age is " + age)
//}


/**
  * 如果主constructor传入的参数什么修饰都没有，比如name: String，那么如果类内部的方法
  * 使用到了，则会声明为private[this] name; 否则没有该field，就只能被constructor代码使用而已
  */
//class Student(name: String, age: Int) {
//  println("your name is " + name + ", your age is " + age)
//}


/**
  * 内部类
  * Scala中，同样可以在类中定义内部类；但是与java不同的是，每个外部类的对象的内部类，都是不同的类
  */

//import scala.collection.mutable.ArrayBuffer
//
//class Class {
//  class Student(val name: String ) {}
//  val students = new ArrayBuffer[Student]()
//  def getStudent(name: String) = {
//    new Student(name)
//  }
//}
