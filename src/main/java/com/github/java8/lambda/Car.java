package com.github.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zqlu
 * @date 2018/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {

    private String brand;

    private Integer year;

    private Integer price;


    public Car(String brand) {
        this.brand = brand;
    }



}
