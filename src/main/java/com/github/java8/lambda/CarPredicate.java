package com.github.java8.lambda;

/**
 * @author zqlu
 * @date 2018/10/15
 */
@FunctionalInterface
public interface CarPredicate {

    /**
     * 对给定的car做断言
     * @param car
     * @return
     */
    boolean test(Car car);



    /**
     * 默认方法
     * @param car
     * @return
     */
    default boolean defaultTest(Car car) {
        return car.getYear() > 2014;
    }
}
