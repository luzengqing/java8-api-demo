package com.github.java8.methodreference;

import com.github.java8.lambda.Car;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用
 * @author zqlu
 * @date 2018/10/18
 */
public class MethodReference {

    /**
     *  方法引用使用条件：
     *  当lambda表达式的方法体中仅仅调用了一个已存在的方法时候,并且不做其他事情时
     *  方法引用是lambda表达式的简洁形式
     *
     *
     *  1. 类::静态方法
     *  2. 对象::实例方法
     *  前两种使用条件: 当lambda表达式体中调用的方法的参数列表和返回值类型与函数式接口的抽象方法结构保持一致
     *
     *  3.类::实例方法
     *  使用条件: 当lambda表达式参数列表中第1个参数是lambda表达式中方法的调用者
     */








    /**
     * 类::静态方法
     */
    @Test
    public void t1() {

        //lambda
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);

        //方法引用
        Comparator<Integer> comparator2 = Integer::compare;


        //lambda
        Function<Integer, String> function1 = o -> String.valueOf(o);

        //方法引用
        Function<Integer, String> function2 = String::valueOf;
    }


    /**
     * 对象::实例方法
     */
    @Test
    public void t2() {

        //lambda
        Consumer<Car> consumer1 = car -> System.out.println(car);

        //方法引用
        Consumer<Car> consumer2 = System.out::println;



        Car mazda = new Car("Mazda", 2014, 33);
        //lambda
        Supplier<String> supplier1 = () -> mazda.getBrand();

        //方法引用
        Supplier<String> supplier2 = mazda::getBrand;

    }


    /**
     * 类::实例方法
     */
    public void t3() {

        //lambda
        Comparator<Integer> comparator1 = (o1, o2) -> o1.compareTo(o2);

        //方法引用
        Comparator<Integer> comparator2 = Integer::compareTo;



        //lambda
        BiPredicate<String, String> predicate1 = (s1, s2) -> s1.equals(s2);

        //方法引用
        BiPredicate<String, String> predicate2 = String::equals;



        //lambda
        Function<Car, String> function1 = car -> car.getBrand();

        //方法引用
        Function<Car, String> function2 = Car::getBrand;

    }
}
