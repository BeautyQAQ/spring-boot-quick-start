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
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CodeTest {

    @Test
    public void test(){
        if (NumberUtil.isNumber("0a001")){
            System.out.println("true");
        }else {
            System.out.println("false");
        }

        String s = StringUtils.arrayToDelimitedString(new ArrayList().toArray(), ",");
        System.out.println(s);

        System.out.println(JSONUtil.toJsonStr(Arrays.asList("xxxxx-bbb".split("-"))));

    }

    @Test
    public void testDate(){
        String sse = "20221230";
        String year = StrUtil.sub(sse, 0, 4);
        String month = StrUtil.sub(sse, 4, 6);
        System.out.println("year="+year);
        System.out.println("sub="+month);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        for (int i = 0; i < 11; i++) {
            monthInt-=1;
            if (monthInt==0){
                monthInt = 12;
                yearInt-=1;
            }
        }
        System.out.println("monthInt="+monthInt);
        System.out.println("yearInt="+yearInt);
    }

    @Test
    public void testDate2(){
        String now = DateUtil.format(new Date(), "yyyyMMdd");
        //String now = "20220708";
        System.out.println("now="+now);
        String year = StrUtil.sub(now, 0, 4);
        String month = StrUtil.sub(now, 4, 6);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        for (int i = 0; i < 11; i++) {
            monthInt-=1;
            if (monthInt==0){
                monthInt = 12;
                yearInt-=1;
            }
        }
        String start;
        if (monthInt<10){
            start = yearInt + "0" + monthInt + "01";
        }else{
            start = yearInt + "" + monthInt + "01";
        }
        System.out.println("start="+start);
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
        Map<String,String> tradingDayMap = tradingDayList.stream().collect(Collectors.groupingBy(e -> StrUtil.sub(e, 0, 6), Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Integer::valueOf)), op->op.orElseThrow(()->new RuntimeException("获取交易日异常")))));
        System.out.println(JSONUtil.toJsonStr(tradingDayMap));
        TreeMap<String, String> treeMap = new TreeMap<>(tradingDayMap);
        System.out.println(JSONUtil.toJsonStr(treeMap));

        // List<Person> people = ...; // 假设这是一个存储了 Person 对象的列表
        // Map<String, Integer> map = tradingDayList.stream()
        //         .collect(Collectors.toMap(
        //                 person -> person.getLastName() + "," + person.getFirstName(), // 指定如何提取键
        //                 Person::getAge // 指定如何提取值
        //         ));
    }
}
