package com.github.java8.stream;

import com.github.java8.lambda.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream Introduction
 * @author zqlu
 * @date 2018/10/20
 */
public class WhatIsStream {

    private List<Car> cars = Arrays.asList(
            new Car("BMW", 2015, 36),
            new Car("Audi", 2012, 34),
            new Car("Benz", 2014, 32),
            new Car("Cadillac", 2016, 35),
            new Car("Mazda", 2014, 33),
            new Car("Lexus", 2013, 30));

    /**
     * 需求:
     * 返回一个只包含他们的品牌名称的集合, 并且是2013年以后的车，并按年份升序排列
     */


    /**
     * 传统解决方案
     */
    @Test
    public void t1() {

        //1.找出2013年后的汽车
        List<Car> findCarByYear = new ArrayList<>();
        for (Car car : cars) {
            if(car.getYear() > 2013){
                findCarByYear.add(car);
            }
        }

        //2.定义比较器对汽车年份排序
        Comparator<Car> comparator = new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return car1.getYear().compareTo(car2.getYear());
            }
        };
        findCarByYear.sort(comparator);

        //3.收集汽车的品牌名称
        List<String> result = new ArrayList<>();
        for (Car car : findCarByYear) {
            result.add(car.getBrand());
        }

        System.out.println(result);
    }



    /**
     * lambda && Stream
     * Stream 是包含多个数据的序列，可以理解成管道流，支持串行与并行操作
     *
     * stream/parallelStream  相当于开启管道, 进入管道后可以进行一些列的操作
     * filter,sorted,map 等这些操作是中间操作,返回的还是Stream， 后面可以链式操作
     * collect,toArray 相当于关闭管道, 出了管道不能再进行中间操作
     *
     */
    @Test
    public void t2() {
        List<String> result = cars.stream()
                .filter(car -> car.getYear() > 2013)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getBrand)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
