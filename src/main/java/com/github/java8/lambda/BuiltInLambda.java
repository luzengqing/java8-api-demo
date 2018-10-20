package com.github.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java内置的几个核心函数式接口
 * @author zqlu
 * @date 2018/10/15
 */
public class BuiltInLambda {


    /**
     * @see Predicate
     * @see Consumer
     * @see Supplier
     * @see Function
     */

    @Test
    public void t1() {

        //lambda表达式标准写法
        Predicate<Car> predicate = (Car car) -> {return car.getYear() > 2014;};
        Car bmw = new Car("BMW", 2015, 36);
        predicate.test(bmw);

        //有了泛型后，参数的类型可以省略
        Predicate<Car> predicate1 = (car) -> {return car.getYear() > 2014;};

        //如果只有一个参数的话，参数的圆括号可以省略
        Predicate<Car> predicate2 = car -> {return car.getYear() > 2014;};

        //如果函数体内只有一个语句，花括号，return都可以省略
        Predicate<Car> predicate3 = car -> car.getYear() > 2014;

        //如果没有参数，必须有圆括号
        Thread thread = new Thread(() -> System.out.println("Hello World"));
    }



    @Test
    public void t2() {
        Consumer<Car> consumer = car -> System.out.println(car);
        Car mazda = new Car("Mazda", 2014, 33);
        consumer.accept(mazda);
    }




    @Test
    public void t3(){
        Supplier<Car> supplier = () -> new Car("INFINITI", 2017, 60);
        Car car = supplier.get();
    }


    /**
     * 函数式接口默认方法、静态方法
     */
    @Test
    public void t4() {

        //字符串转大写函数
        Function<String, String> function = str -> str.toUpperCase();
        System.out.println(function.apply("hello"));

        //在字符串转大写之前，先应用某个函数
        Function<String, String> compose = function.compose(o ->  o.concat(" world"));
        System.out.println(compose.apply("hello"));


        //在字符串转大写之后，再应用某个函数
        Function<String, String> andThen = function.andThen(upperCase -> upperCase.concat(" world"));
        System.out.println(andThen.apply("hello"));

        //静态方法
        Function<String, String> identity = Function.identity();
        String hello = identity.apply("hello");
        System.out.println(hello);
    }

    /**
     * 除了以上4个核心函数式接口，还有
     * @see java.util.function.BiConsumer
     * @see java.util.function.BiFunction
     * ...
     */

}
