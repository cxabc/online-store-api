package com.maidao.edu;

import com.maidao.edu.store.api.weapon.model.Weapon;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class StreamTest {
    public static void main(String[] args) {

            Weapon weapon = new Weapon(10, "张三", 100, "img", 1);
            Optional<Weapon> os1 = Optional.of(weapon);
            os1.filter(p -> p.getWeaponName().equals("张三")).ifPresent(x -> System.out.println("OK"));

    }

    /**
     * 将武器的价格全部增加1000
     */
    @Test
    public void test() {

        List<Weapon> weaponList = new ArrayList<>();
        weaponList.add(new Weapon(10, "火箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(11, "火箭", 100, "img", 1));
        weaponList.add(new Weapon(12, "导弹", 100, "img", 1));
        weaponList.add(new Weapon(13, "火弹", 100, "img", 1));
        weaponList.add(new Weapon(14, "箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(15, "弹", 100, "img", 1));
        weaponList.add(new Weapon(16, "火导弹", 100, "img", 1));
        weaponList.add(new Weapon(17, "弹", 100, "img", 1));
        System.out.println("一次改动前：" + weaponList.get(0).getWeaponName() + "-->" + weaponList.get(0).getPrice());
        List<Weapon> weaponList1 = weaponList.stream().map(weapon -> {
            weapon.setPrice(weapon.getPrice() + 1000);
            return weapon;
        }).collect(Collectors.toList());
        System.out.println(weaponList1);

        weaponList1.forEach(weapon -> {
            System.out.println(weapon.getWeaponName() + "-->" + weapon.getPrice());
        });

        System.out.println("一次改动后：" + weaponList1.get(0).getWeaponName() + "-->" + weaponList1.get(0).getPrice());

    }

    @Test
    public void test2() {
        // 输出字符串集合中每个字符串的长度
        List<String> stringList = Arrays.asList("mu", "CSDN", "hello",
                "world", "quickly");
        stringList.stream().mapToInt(String::length).forEach(System.out::println);
        // 将int集合的每个元素增加1000
        List<Integer> integerList = Arrays.asList(4, 5, 2, 1, 6, 3);
        integerList.stream().mapToInt(x -> x + 1000).forEach(System.out::println);

        // 求最大最小值、求和、求平均值：
        List<Double> doubleList = Arrays.asList(1.0, 2.0, 3.0, 4.0, 2.0);
        double average = doubleList.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
        double sum = doubleList.stream().mapToDouble(Number::doubleValue).sum();
        double max = doubleList.stream().mapToDouble(Number::doubleValue).max().getAsDouble();
        System.out.println("平均值：" + average + "，总和：" + sum + "，最大值：" + max);
    }

    /**
     * reduce 缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作
     */
    @Test
    public void test3() {

        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求最大值：" + max.get() + "," + max2);
    }

    /**
     * 统计(count/averaging)
     */
    @Test
    public void test4() {
        List<Weapon> weaponList = new ArrayList<>();
        weaponList.add(new Weapon(10, "火箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(11, "火箭", 100, "img", 1));
        weaponList.add(new Weapon(12, "导弹", 100, "img", 1));
        weaponList.add(new Weapon(13, "火弹", 100, "img", 1));
        weaponList.add(new Weapon(14, "箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(15, "弹", 100, "img", 1));
        weaponList.add(new Weapon(16, "火导弹", 100, "img", 1));
        weaponList.add(new Weapon(17, "弹", 100, "img", 1));

        // 求总数
        Long count = weaponList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = weaponList.stream().collect(Collectors.averagingDouble(Weapon::getPrice));
        // 求最高工资
        Optional<Integer> max = weaponList.stream().map(Weapon::getPrice).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = weaponList.stream().collect(Collectors.summingInt(Weapon::getPrice));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = weaponList.stream().collect(Collectors.summarizingDouble(Weapon::getPrice));

        System.out.println("总数：" + count);
        System.out.println("平均价格：" + average);
        System.out.println("价格总和：" + sum);
        System.out.println("武器价格所有统计：" + collect);


    }

    /**
     * 分组(partitioningBy/groupingBy)
     */
    @Test
    public void test5() {
        List<Weapon> weaponList = new ArrayList<>();
        weaponList.add(new Weapon(10, "火箭导弹", 333, "img", 1));
        weaponList.add(new Weapon(11, "火箭", 100, "!img", 2));
        weaponList.add(new Weapon(12, "导弹", 333, "img", 1));
        weaponList.add(new Weapon(13, "火弹", 100, "!img", 2));
        weaponList.add(new Weapon(14, "箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(15, "弹", 333, "img", 2));
        weaponList.add(new Weapon(16, "火导弹", 100, "!img", 1));
        weaponList.add(new Weapon(17, "弹", 100, "img", 1));

        // 将武器价格是否高于100分组
        Map<Boolean, List<Weapon>> part = weaponList.stream().collect(Collectors.partitioningBy(x -> x.getPrice() > 100));
        // 将武器按状态分组
        Map<Integer, List<Weapon>> group = weaponList.stream().collect(Collectors.groupingBy(Weapon::getStatus));
        // 将武器先按状态分组，再按图像分组
        Map<Integer, Map<String, List<Weapon>>> group2 = weaponList.stream().collect(Collectors.groupingBy(Weapon::getStatus, Collectors.groupingBy(Weapon::getImg)));

        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }

    /**
     * 排序(sorted)
     * sorted，中间操作。有两种排序：
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */
    @Test
    public void test6() {
        List<Weapon> weaponList = new ArrayList<>();
        weaponList.add(new Weapon(10, "火箭导弹", 333, "img", 1));
        weaponList.add(new Weapon(11, "火箭", 100, "!img", 2));
        weaponList.add(new Weapon(12, "导弹", 333, "img", 1));
        weaponList.add(new Weapon(13, "火弹", 100, "!img", 2));
        weaponList.add(new Weapon(14, "箭导弹", 100, "img", 1));
        weaponList.add(new Weapon(15, "弹", 333, "img", 2));
        weaponList.add(new Weapon(16, "火导弹", 100, "!img", 1));
        weaponList.add(new Weapon(17, "弹", 100, "img", 1));

        // 按价格升序排序（自然排序）
        List<String> newList = weaponList
                .stream()
                .sorted(Comparator.comparing(Weapon::getPrice))
                .map(Weapon::getWeaponName)
                .collect(Collectors.toList());
        // 按价格倒序排序
        List<String> newList2 = weaponList
                .stream()
                .sorted(Comparator.comparing(Weapon::getPrice).reversed())
                .map(Weapon::getWeaponName)
                .collect(Collectors.toList());
        // 先按工资再按状态升序排序
        List<String> newList3 = weaponList
                .stream()
                .sorted(Comparator.comparing(Weapon::getPrice).thenComparing(Weapon::getStatus))
                .map(Weapon::getWeaponName)
                .collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
    }


}
