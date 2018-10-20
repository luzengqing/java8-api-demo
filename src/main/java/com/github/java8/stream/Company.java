package com.github.java8.stream;

import java.util.List;

/**
 * @author zqlu
 * @date 2018/10/20
 */
public class Company {

    private String name;

    private List<Depart> departs;


    public Company(String name, List<Depart> departs) {
        this.name = name;
        this.departs = departs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", departs=" + departs +
                '}';
    }
}
