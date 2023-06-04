package com.quick.start.code.snippets;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CodeTest {

    @Test
    public void test() {
        if (NumberUtil.isNumber("0a001")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        String s = StringUtils.arrayToDelimitedString(new ArrayList().toArray(), ",");
        System.out.println(s);

        System.out.println(JSONUtil.toJsonStr(Arrays.asList("xxxxx-bbb".split("-"))));

    }

    @Test
    public void testDate() {
        String sse = "20221230";
        String year = StrUtil.sub(sse, 0, 4);
        String month = StrUtil.sub(sse, 4, 6);
        System.out.println("year=" + year);
        System.out.println("sub=" + month);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        for (int i = 0; i < 11; i++) {
            monthInt -= 1;
            if (monthInt == 0) {
                monthInt = 12;
                yearInt -= 1;
            }
        }
        System.out.println("monthInt=" + monthInt);
        System.out.println("yearInt=" + yearInt);
    }

    @Test
    public void testDate2() {
        String now = DateUtil.format(new Date(), "yyyyMMdd");
        // String now = "20220708";
        System.out.println("now=" + now);
        String year = StrUtil.sub(now, 0, 4);
        String month = StrUtil.sub(now, 4, 6);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        for (int i = 0; i < 11; i++) {
            monthInt -= 1;
            if (monthInt == 0) {
                monthInt = 12;
                yearInt -= 1;
            }
        }
        String start;
        if (monthInt < 10) {
            start = yearInt + "0" + monthInt + "01";
        } else {
            start = yearInt + "" + monthInt + "01";
        }
        System.out.println("start=" + start);
        List<String> tradingDayList = new ArrayList<>();
        tradingDayList.add("20221214");
        tradingDayList.add("20221213");
        tradingDayList.add("20221208");
        tradingDayList.add("20221123");
        tradingDayList.add("20221120");
        tradingDayList.add("20221014");
        tradingDayList.add("20221018");
        tradingDayList.add("20220909");
        tradingDayList.add("20220901");
        tradingDayList.add("20220814");
        tradingDayList.add("20220818");
        tradingDayList.add("20220712");
        tradingDayList.add("20220611");
        tradingDayList.add("20220511");
        tradingDayList.add("20220506");
        tradingDayList.add("20220406");
        tradingDayList.add("20220306");
        tradingDayList.add("20220206");
        tradingDayList.add("20220106");
        tradingDayList.add("20211206");
        tradingDayList.add("20211229");
        tradingDayList.add("20211129");
        tradingDayList.add("20211029");
        tradingDayList.add("20210929");
        tradingDayList.add("20210920");
        tradingDayList.add("20210820");
        tradingDayList.add("20210811");
        tradingDayList.add("20210705");
        tradingDayList.add("20210708");
        // 取最后一个交易日, 以及前11个月的月末日期, 根据年月分组, 取最后一个
        // 将这个 List 对象转换成一个 Map<String,String> 对象 tradingDayMap，根据年月分组。
        // Map 的 key 为年月（即截取了 yyyyMMdd 中的前 6 位），value 为该年月中的最后一个交易日和前11个月的月末日期中的最大值。
        Map<String, String> tradingDayMap = tradingDayList.stream()
                .collect(Collectors.groupingBy(e -> StrUtil.sub(e, 0, 6),
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Integer::valueOf)),
                                op -> op.orElseThrow(() -> new RuntimeException("获取交易日异常")))));
        System.out.println(JSONUtil.toJsonStr(tradingDayMap));
        TreeMap<String, String> treeMap = new TreeMap<>(tradingDayMap);
        System.out.println(JSONUtil.toJsonStr(treeMap));

        // List<Person> people = ...; // 假设这是一个存储了 Person 对象的列表
        // Map<String, Integer> map = tradingDayList.stream()
        // .collect(Collectors.toMap(
        // person -> person.getLastName() + "," + person.getFirstName(), // 指定如何提取键
        // Person::getAge // 指定如何提取值
        // ));
    }

    @Test
    public void testDate3() {
        // 集合流的过滤和映射
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        List<Integer> doubledEvenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .toList();
        // 上述代码中，首先创建了一个包含整数的 List 对象 `numbers`。然后使用 `stream()` 方法将该 List 转换为一个流对象。接着使用 `filter` 方法过滤出其中的偶数，然后使用 `map` 方法将偶数都翻倍，最后用 `collect` 方法将结果收集到一个新的 List 中。

        // 对流进行分组和归约
        List<Product> productList = new ArrayList<>();
        Product a = new Product("a", 100.0);
        Product b = new Product("b", 200.0);
        productList.add(a);
        productList.add(b);
        Map<String, List<Product>> productsByCategory = productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        double averagePrice = productList.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        double totalPrice = productList.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        Optional<Product> maxPriceProduct = productList.stream()
                .max(Comparator.comparing(Product::getPrice));
        // 上述代码中，假设从数据库中获取了一个产品列表，并定义了一个 `Product` 类型。可以使用 `groupingBy` 方法对产品按照类别进行分组，生成一个以类别名称为 key，对应产品列表为 value 的 Map 对象。使用 `averagingDouble` 方法获取产品价格的平均值，使用 `sum` 方法获取所有产品的总价。使用 `max` 方法获取产品中价格最高的那个产品。

        // 流的筛选操作
        List<Product> expensiveProducts = productList.stream()
                .filter(p -> p.getPrice() > 1000)
                .toList();
        boolean hasCheapProducts = productList.stream()
                .anyMatch(p -> p.getPrice() < 50);
        Optional<Product> firstCheapProduct = productList.stream()
                .filter(p -> p.getPrice() < 50)
                .findFirst();
        // 上述代码中，可以使用 `filter` 方法筛选出价格超过 1000 的产品，并使用 `collect` 方法将结果收集到一个新的 List 中。可以使用 `anyMatch` 方法判断产品价格是否有低于 50 的，使用 `findFirst` 方法获取第一个价格低于 50 的产品。

        // 流对象的一些常见操作方法
        // stream.forEach(System.out::println);   // 每个元素逐个打印
        // stream.distinct();                     // 去除流中的重复元素
        // stream.limit(5);                       // 高阶函数：只保留前 5 个元素
        // stream.skip(5);                        // 高阶函数：跳过前 5 个元素
        // stream.sorted();                       // 将流中的元素排序
        // stream.toArray();                      // 将流中的元素转换为数组
        // stream.reduce((a, b) -> a + b).get();  // 将流中的元素累加
        // 上述代码中，`forEach` 方法用于对每个元素逐个执行某个操作；`distinct` 方法用于去除流中的重复元素；`limit` 和 `skip` 方法分别用于只保留前 n 个元素和跳过前 n 个元素；`sorted` 方法用于将流中的元素排序；`toArray` 方法用于将流中的元素转换为数组；`reduce` 方法用于在流中进行累加或者归约操作。
    }

    private static class Product {
        private String category;
        private Double price;

        public Product(String category, Double price){
            this.category = category;
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public Product setCategory(String category) {
            this.category = category;
            return this;
        }

        public Double getPrice() {
            return price;
        }

        public Product setPrice(Double price) {
            this.price = price;
            return this;
        }
    }
}
