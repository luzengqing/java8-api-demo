package com.github.java8.lambda;

/**
 * @author zqlu
 * @date 2018/10/15
 */
public class CarYearPredicate implements CarPredicate {


    @Override
    public boolean test(Car car) {
        return car.getYear() > 2014;
    }

}
