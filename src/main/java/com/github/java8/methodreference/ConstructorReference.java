package com.github.java8.methodreference;

import com.github.java8.lambda.Car;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 * @author zqlu
 * @date 2018/10/18
 */
public class ConstructorReference {

    /**
     * 构造器引用 类名::new
     *
     * 构造引用也是一种方法引用，具体调用的哪个构造方法，就看函数式接口的抽象方法的参数列表
     */

    @Test
    public void t1() {

        //lambda
        Supplier<Car> supplier1 = () -> new Car();

        //构造引用
        Supplier<Car> supplier2 = Car::new;



        //因为Supplier抽象方法的结构与该构造方法结构不一致，所以不能使用构造引用
        Supplier<Car> supplier = () -> new Car("Jeep", 2016, 32);




        //lambda
        Function<String, Car> function1 = brand -> new Car(brand);

        //构造引用
        Function<String, Car> function2 = Car::new;




    }
}
