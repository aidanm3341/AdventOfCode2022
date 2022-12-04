package org.aidan.utils;

import java.util.Objects;

public class Tuple2<ONE, TWO> {
    private ONE one;
    private TWO two;

    public Tuple2(ONE one, TWO two) {
        this.one = one;
        this.two = two;
    }

    public Tuple2() {}

    public ONE getOne() {
        return one;
    }
    public TWO getTwo() {
        return two;
    }
    public void setOne(ONE one) {
        this.one = one;
    }
    public void setTwo(TWO two) {
        this.two = two;
    }

    public void clear() {
        one = null;
        two = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(one, tuple2.one) && Objects.equals(two, tuple2.two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
