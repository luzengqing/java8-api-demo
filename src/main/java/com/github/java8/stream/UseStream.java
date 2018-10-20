package com.github.java8.stream;

import com.github.java8.lambda.Car;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by louis on 2017/11/4.
 * Stream Apis
 */
public class UseStream {


    private List<Car> cars = Arrays.asList(
            new Car("BMW", 2015, 36),
            new Car("Audi", 2012, 34),
            new Car("Benz", 2014, 32),
            new Car("Cadillac", 2016, 35),
            new Car("Mazda", 2014, 33),
            new Car("Lexus", 2013, 30));


    /**
     * 数组也支持stream操作
     */
    @Test
    public void t1() {

        Stream<Integer> stream= Stream.of(1,2,3,4,5,6);
        stream.forEach(System.out::println);

    }



    //filter 过滤
    @Test
    public void t2(){

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = integers.stream().filter(integer -> integer % 2 == 1).collect(Collectors.toList());
    }

    //sort 排序
    @Test
    public void t7(){
        List<Integer> integers = Arrays.asList(4, 5, 6, 2, 1, 3);

        List<Integer> result = integers.stream().sorted(Comparator.comparing(integer -> integer)).collect(Collectors.toList());
    }

    //distinct 去重
    @Test
    public void t3(){

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 3, 5, 2);
        List<Integer> result = integers.stream().distinct().collect(Collectors.toList());
    }

    //skip limit 截取部分
    @Test
    public void t4(){

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //截取前4个元素
        List<Integer> result1 = integers.stream().limit(4).collect(Collectors.toList());

        //从第3个元素开始截取所有元素
        List<Integer> result2 = integers.stream().skip(2).collect(Collectors.toList());

        //从第2个元素截取，截取3个元素
        List<Integer> result3 = integers.stream().skip(1).limit(3).collect(Collectors.toList());
    }


    //max
    @Test
    public void t14(){
        List<Integer> integers = Arrays.asList(2,6,3,4,5,1);

        //按一定的规则查找最大的元素
        Optional<Integer> max = integers.stream().max(Integer::compare);

        System.out.println(max.get());
    }

    //min
    @Test
    public void t15(){
        List<Integer> integers = Arrays.asList(2,6,3,4,5,1);

        //按一定的规则查找最小的元素
        Optional<Integer> min = integers.stream().min(Comparator.comparing(i -> i));

        System.out.println(min.get());
    }


    //map 映射
    @Test
    public void t5(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //获取每个元素的平方
        List<Integer> list = integers.stream().map(integer -> integer * integer).collect(Collectors.toList());

        //求所有元素的总和
        int sum = integers.stream().mapToInt(i -> i).sum();

    }



    /**
     * reduce 归约   将流中每个元素执行相同的操作, 最后合并成一个值
     *  T reduce(T identity, BinaryOperator<T> accumulator); 设定起始值identity, 然后将每个元素执行accumulator的操作
     *  Optional<T> reduce(BinaryOperator<T> accumulator);  不设定起始值，对所有元素执行相同操作进行归约，返回Optional
     */
    @Test
    public void t16(){
        //计算一串数字的和
        List<Integer> integers = Arrays.asList(1,2,3,4,5,6);
        Integer sum1 = integers.stream().reduce(0, Integer::sum);
        System.out.println(sum1);

        //求所有汽车的总价格
        //map-reduce模式 使用的频率最高
        Optional<Integer> optional = cars.stream().map(Car::getPrice).reduce(Integer::sum);

        Integer sum2 = optional.get();


        int sum3 = cars.stream().mapToInt(Car::getPrice).sum();

    }

    /**
     * flatMap 扁平化
     */
    @Test
    public void t18(){
        Employee e1 = new Employee(1, "张三");
        Employee e2 = new Employee(2, "李四");
        Employee e3 = new Employee(3, "王五");
        Employee e4 = new Employee(4, "赵六");
        Depart d1 = new Depart(10, "开发部", Arrays.asList(e1, e2));
        Depart d2 = new Depart(12, "市场部", Arrays.asList(e3, e4));
        Company company = new Company("凯京", Arrays.asList(d1, d2));

        /**
         * 扁平化处理，打破部门约束，将多个部门的员工放在一个容器里
         */
        List<Employee> employees = company.getDeparts().stream().flatMap(depart -> depart.getEmployees().stream()).collect(Collectors.toList());
        System.out.println(employees);
    }


    /**
     * 数学统计
     * @see java.util.stream.IntStream
     * @see java.util.stream.DoubleStream
     * @see java.util.stream.LongStream
     */
    @Test
    public void t19(){

        IntSummaryStatistics statistics = cars.stream().mapToInt(Car::getPrice).summaryStatistics();

        long count = statistics.getCount();

        long sum = statistics.getSum();

        double average = statistics.getAverage();

        int max = statistics.getMax();

        int min = statistics.getMin();
    }




    /**
     * collect 收集
     * 除了常用的toList()  toSet()  toCollection()
     * 还有其他收集方式
     */
    @Test
    public void t17(){

        //求个数
        Long count1 = cars.stream().collect(Collectors.counting());
        Long count2 = cars.stream().count();
        Long count3 = (long) cars.size();

        //求所有汽车的价格总和
        Integer sum1 = cars.stream().collect(Collectors.summingInt(Car::getPrice));
        Integer sum2 = cars.stream().mapToInt(Car::getPrice).sum();

        //求平均值
        Double ave = cars.stream().collect(Collectors.averagingDouble(Car::getPrice));

        //获取价格最高的汽车
        Optional<Car> max1 = cars.stream().collect(Collectors.maxBy(Comparator.comparing(Car::getPrice)));
        Optional<Car> max2 = cars.stream().max(Comparator.comparing(Car::getPrice));

        //按年份进行分组
        Map<Integer, List<Car>> map1 = cars.stream().collect(Collectors.groupingBy(Car::getYear));
        System.out.println(map1);

        //针对汽车的价格以某个值进行分区
        Map<Boolean, List<Car>> map2 = cars.stream().collect(Collectors.partitioningBy(car -> car.getPrice() > 40));

    }

}
