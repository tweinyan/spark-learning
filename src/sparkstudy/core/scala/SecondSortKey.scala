package sparkstudy.core.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tweinyan on 2018/9/23.
  */
class SecondSortKey(val first: Int, val second: Int)
  extends Ordered[SecondSortKey] with Serializable {
  override def compare(that: SecondSortKey): Int = {
    if (this.first - that.first != 0) {
      this.first - that.first
    } else {
      this.second - that.second
    }
  }
}
