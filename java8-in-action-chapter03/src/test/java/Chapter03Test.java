import java8.in.action.chapter03.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Chapter03Test {

    @Test
    public void test001() {
        List<Apple> list = new ArrayList<>();
        Apple green = new Apple(34, "green");
        list.add(green);
        Consumer<Apple> consumer = a -> green.setWeight(50);
        list.forEach(System.out::println);
        list.forEach(consumer);
        list.forEach(System.out::println);
    }
}