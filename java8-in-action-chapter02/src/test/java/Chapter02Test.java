import java8.in.action.chapter02.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Chapter02Test {

    List<Apple> inventory;

    @Before
    public void init() {
        inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
    }

    /**
     * 代码写死颜色
     */
    @Test
    public void testFilterGreenApples() {
        Assert.assertTrue(AppleUtils.filterGreenApples(inventory).stream().allMatch(app -> "green".equals(app.getColor())));
    }

    /**
     * 参数传递颜色
     */
    @Test
    public void testFilterApplesByColor() {
        Assert.assertTrue(AppleUtils.filterApplesByColor(inventory, "green").stream().allMatch(app -> "green".equals(app.getColor())));
        Assert.assertTrue(AppleUtils.filterApplesByColor(inventory, "red").stream().allMatch(app -> "red".equals(app.getColor())));
    }

    /**
     * 参数传递重量
     */
    @Test
    public void testFilterApplesByWeight() {
        Assert.assertTrue(AppleUtils.filterApplesByWeight(inventory, 150).stream().allMatch(app -> app.getWeight() > 150));
    }

    /**
     * 通过参数判断
     */
    @Test
    public void testFilterApples() {
        Assert.assertTrue(AppleUtils.filterApples(inventory, "green", 150, true).stream().allMatch(app -> "green".equals(app.getColor())));
        Assert.assertTrue(AppleUtils.filterApples(inventory, "green", 150, false).stream().allMatch(app -> app.getWeight() > 150));
    }

    /**
     * 行为参数化：使用实现类
     */
    @Test
    public void testFilterApples2() {
        Assert.assertTrue(AppleUtils.filterApples2(inventory, new AppleGreenColorPredicate()).stream().allMatch(app -> "green".equals(app.getColor())));
        Assert.assertTrue(AppleUtils.filterApples2(inventory, new AppleHeavyWeightPredicate()).stream().allMatch(app -> app.getWeight() > 150));
        Assert.assertTrue(AppleUtils.filterApples2(inventory, new AppleRedAndHeavyPredicate()).stream().allMatch(app -> "red".equals(app.getColor()) && app.getWeight() > 150));
    }

    /**
     * 行为参数化：打印
     */
    @Test
    public void testPrettyPrintApple() {
        AppleUtils.prettyPrintApple(inventory, new AppleFancyFormatter());
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    /**
     * 行为参数化：使用匿名类
     */
    @Test
    public void testFilterApples3() {
        Assert.assertTrue(AppleUtils.filterApples2(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        }).stream().allMatch(app -> "red".equals(app.getColor())));
    }

    /**
     * 行为参数化：使用 Lambda 表达式
     */
    @Test
    public void testFilterApples4() {
        Assert.assertTrue(AppleUtils.filterApples2(inventory, apple -> "red".equals(apple.getColor())).stream().allMatch(app -> "red".equals(app.getColor())));
    }

    /**
     * List.sort()
     */
    @Test
    public void testListSort() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        inventory.forEach(System.out::println);

        inventory.sort((o1, o2) -> o1.getWeight() - o2.getWeight());
        inventory.forEach(System.out::println);

        inventory.sort(Comparator.comparingInt(Apple::getWeight));
        inventory.forEach(System.out::println);
    }
}
