import java8.in.action.chapter01.Apple;
import java8.in.action.chapter01.AppleUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppleTest {

    private List<Apple> appleList;

    @Before
    public void init() {
        appleList = AppleUtils.initAppleList();
    }

    @Test
    public void testFilterGreenApples() {
        AppleUtils.filterGreenApples(appleList).forEach(System.out::println);
    }

    @Test
    public void testFilterHeavyApples() {
        AppleUtils.filterHeavyApples(appleList).forEach(System.out::println);
    }

    @Test
    public void testFilterApples() {
        System.out.println("filterGreenApples--");
        AppleUtils.filterApple(appleList, AppleUtils::isGreenApple).forEach(System.out::println);
        System.out.println("filterHeavyApples--");
        AppleUtils.filterApple(appleList, AppleUtils::isHeavyApple).forEach(System.out::println);
    }

    @Test
    public void testLambda() {
        AppleUtils.filterApple(appleList, apple -> "green".equals(apple.getColor())).forEach(System.out::println);
    }
}
