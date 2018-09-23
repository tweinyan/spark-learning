package sparkstudy.core.java;

import scala.math.Ordered;

import java.io.Serializable;

/**
 * 自定义的二次排序key
 * Created by tweinyan on 2018/9/22.
 */
public class SecondarySortKey implements Ordered<SecondarySortKey>, Serializable {
    public SecondarySortKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    // 首先在自定义key里面，定义需要进行排序的列
    private int first;
    private int second;

    @Override
    public int compare(SecondarySortKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.getFirst();
        } else {
            return this.second - that.getSecond();
        }
    }

    @Override
    public boolean $less(SecondarySortKey that) {
        if (this.first < that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second < this.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater(SecondarySortKey that) {
        if (this.first > that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second > that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $less$eq(SecondarySortKey that) {
        if (this.$less(that)) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater$eq(SecondarySortKey that) {
        if (this.$greater(that)) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(SecondarySortKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.getFirst();
        } else {
            return this.second - that.getSecond();
        }
    }

    // 为要进行排序的多个列，提供getter和setter方法，以及hashcode和equals方法

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondarySortKey that = (SecondarySortKey) o;

        if (first != that.first) return false;
        return second == that.second;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }

    public int getFirst() {

        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
