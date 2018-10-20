package com.github.java8.localdatetime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by louis on 2017/7/5.
 */
public class LocalDateTimeTest {

    /**
     *  LocalDate LocalTime LocalDateTime
     *  @see LocalDateTime
     *
     *  不可变且线程安全
     *  使用ISO-8601国际标准， 默认格式：2007-12-03T10:15:30
     */

    @Test
    public void t1(){

        //获取当前系统时区对应的日期时间
        LocalDateTime now = LocalDateTime.now();

        //根据时区ID获取对应的日期时间
        LocalDateTime system = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime Paris = LocalDateTime.now(ZoneId.of("Europe/Paris"));


        //根据参数获取指定的日期时间  2017-06-06T12:19:32
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 6, 12, 19, 32);

        //根据时间戳获取对应的日期时间
        LocalDateTime dateTime3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1510120120398L), ZoneId.systemDefault());




        //加3个月  2017-09-06T12:19:32
        LocalDateTime dateTime1 = dateTime.plusMonths(3);

        //减去5天   2017-06-01T12:19:32
        LocalDateTime dateTime2 = dateTime.minusDays(5);

        //获取年
        int year = dateTime.getYear();
        //月的第几天
        int dayOfMonth = dateTime.getDayOfMonth();
        //星期的第几天
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        //年的第几天
        int dayOfYear = dateTime.getDayOfYear();

    }



    /**
     * Instant 时间戳  距离1970-01-01T00:00:00 的时间毫秒数
     */
    @Test
    public void t2(){

        ////获取当前时间戳，默认获取UTC的时区
        Instant instant = Instant.now();

        //获取北京时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));

        //获取时间戳对应的数字
        Long l = instant.toEpochMilli();

        //获取距离970-01-01T00:00:00Z 10秒的时间戳
        Instant instant1 = Instant.ofEpochSecond(10);
    }

    /**
     * Duration 两个日期时间的间隔
     */
    @Test
    public void t3(){

        LocalDateTime dateTime1 = LocalDateTime.of(2017, 6, 6, 12, 19, 32);

        LocalDateTime dateTime2 = LocalDateTime.of(2017, 7, 7, 12, 20, 32);

        Duration duration = Duration.between(dateTime1, dateTime2);

        //间隔的天
        long days = duration.toDays();
        //间隔的小时
        long hours = duration.toHours();
        //间隔的分钟
        long minutes = duration.toMinutes();
        //间隔的毫秒数
        long millis = duration.toMillis();

        System.out.println(days);

    }

    /**
     * Period  两个日期的间隔
     */
    @Test
    public void t4(){

        LocalDate date1 = LocalDate.of(2017, 6, 6);

        LocalDate date2 = LocalDate.of(2017, 9, 10);

        Period period = Period.between(date1, date2);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

    }



    /**
     * LocalDateTime <--->  string
     */
    @Test
    public void t5(){

        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String str1 = dateTime.format(formatter);

        LocalDateTime dateTime1 = LocalDateTime.parse("2017-11-11 10:00:00", formatter);
    }



    /**
     * LocalDateTime <--Instant-->  java.util.Date
     */
    @Test
    public void t6(){

        LocalDateTime dateTime = LocalDateTime.now();

        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        LocalDateTime dateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

    }
}
