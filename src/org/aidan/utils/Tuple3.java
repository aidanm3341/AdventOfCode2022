package org.aidan.utils;

import java.util.Objects;

public class Tuple3<ONE, TWO, THREE> {
    private ONE one;
    private TWO two;
    private THREE three;

    public Tuple3(ONE one, TWO two, THREE three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public Tuple3() {}

    public ONE getOne() {
        return one;
    }
    public TWO getTwo() {
        return two;
    }
    public THREE getThree() {
        return three;
    }
    public void setOne(ONE one) {
        this.one = one;
    }
    public void setTwo(TWO two) {
        this.two = two;
    }
    public void setThree(THREE three) {
        this.three = three;
    }

    public void clear() {
        one = null;
        two = null;
        three = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(one, tuple3.one) && Objects.equals(two, tuple3.two) && Objects.equals(three, tuple3.three);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two, three);
    }

    @Override
    public String toString() {
        return "Tuple3{" +
                "one=" + one +
                ", two=" + two +
                ", three=" + three +
                '}';
    }
}
