package sparkstudy.core.tuto

/**
  * Created by tweinyan on 2018/10/25.
  * object，相当于class的单个实例，通常在里面放一些静态的field或者method
  * 第一次调用object的方法时，就会执行object的constructor，也就是object内部不在
  * method中的代码；但是object不能定义接受参数的constructor
  * 注意：object的constructor只会在其第一次被调用时执行一次，以后再次调用就不会再次执行constructor了
  *
  * object通常用于作为单例模式的实现，或者放class的静态成员，比如工具方法
  */
object ObjectExample {
}
