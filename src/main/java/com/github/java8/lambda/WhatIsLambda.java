package com.github.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 什么是lambda
 * @author zqlu
 * @date 2018/10/15
 */
public class WhatIsLambda {

    private List<Car> cars = Arrays.asList(
            new Car("Benz", 2014, 32),
            new Car("BMW", 2015, 36),
            new Car("Audi", 2012, 34),
            new Car("Cadillac", 2016, 35),
            new Car("Mazda", 2014, 33),
            new Car("Lexus", 2013, 30));

    /**
     * 找出XXX年之后的汽车
     */
    public List<Car> filterByYear(List<Car> sources, int year) {
        List<Car> result = new ArrayList<>();
        for (Car car : sources) {
            if(car.getYear() > year) {
                result.add(car);
            }
        }
        return result;
    }

    /**
     * 找出价格低于XXX的汽车
     */
    public List<Car> filterByPrice(List<Car> sources, int price) {
        List<Car> result = new ArrayList<>();
        for (Car car : sources) {
            if(car.getPrice() < price) {
                result.add(car);
            }
        }
        return result;
    }














    /**
     * 以上这些通过修改方法参数列表的缺点就是，代码没有得到高度重复利用
     *
     * 解决思路：定义统一的接口，声明一个根据条件过滤汽车，返回boolean类型的抽象方法
     * 这样，让参数具备了行为能力
     */
    public List<Car> filter(List<Car> sources, CarPredicate predicate) {
        List<Car> result = new ArrayList<>();
        for (Car car : sources) {
            if(predicate.test(car)) {
                result.add(car);
            }
        }
        return result;
    }
















    /**
     * 解决方案1.
     * 在单独的文件里定义接口实现类
     */
    @Test
    public void t1(){
        //找出年份在2014年之后的汽车
        List<Car> filter = this.filter(cars, new CarYearPredicate());
        System.out.println(filter);

        //找出价格小于34w的汽车
        this.filter(cars, new CarPricePredicate());
    }

    /**
     * 定义实现类的缺点就是，会创建很多的类文件，显得项目过于臃肿，不够简洁
     */










    /**
     * 解决方案2.
     * 直接在方案参数上声明匿名内部类
     */
    @Test
    public void t2(){

        this.filter(cars, new CarPredicate() {
            @Override
            public boolean test(Car car) {
                return car.getYear() > 2014;
            }
        });

        this.filter(cars, new CarPredicate() {
            @Override
            public boolean test(Car car) {
                return car.getPrice() < 34;
            }
        });
    }

    /**
     * 匿名内部类相比显示创建实现类优点是使得逻辑功能部分肉眼可见
     * 匿名内部类虽然没有创建类文件，但是还会占用很大空间，显得很笨重，并且有大量高亮代码
     * 我们正在关心，在意的是什么？
     */











    /**
     * 解决方案3.
     * Java 8 Lambda
     * @see FunctionalInterface
     *
     * 使用lambda表达式
     * 需要将使用的接口声明为函数式接口 @FunctionalInterface, 且该接口有且只有一个抽象方法
     */
    @Test
    public void t3(){

        this.filter(cars, (Car car) -> {return car.getYear() > 2014;});

        this.filter(cars, (Car car) -> {return car.getPrice() < 34;});



        // 更简洁写法
        this.filter(cars, car -> car.getYear() > 2014);
        this.filter(cars, car -> car.getPrice() < 34);
    }



    /**
     * 总结: lambda表达式使得我们更加专注于逻辑功能本身
     * 1. lambda表达式也是对象，就是函数式接口的一个实现类。
     * 2. lambda表达式让方法参数具备了行为能力，是一段可以当做参数进行传递的代码块(这个代码块就是接口抽象方法的具体实现)
     * 3. lambda和普通方法一样，也有方法参数列表，方法体
     */

}
