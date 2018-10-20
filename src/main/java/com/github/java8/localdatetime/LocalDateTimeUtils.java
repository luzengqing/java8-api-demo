package com.github.java8.localdatetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class LocalDateTimeUtils {

    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINESE);
    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.CHINESE);
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ", Locale.CHINESE);


    /**
     * 当前日期
     */
    public static String dateNow(){
        return LocalDate.now().format(DEFAULT_DATE_FORMATTER);
    }

    public static String dateNow(String pattern){
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }

    /**
     * 当前时间
     */
    public static String timeNow(){
        return LocalTime.now().format(DEFAULT_TIME_FORMATTER);
    }

    public static String timeNow(String pattern){
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }

    /**
     * 当前日期时间
     */
    public static String dateTimeNow(){
        return LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMATTER);
    }
    /**
     * 指定格式的当前日期时间
     * @param pattern
     * @return
     */
    public static String dateTimeNow(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }


    /**
     * 指定日期
     */
    public static String dateOf(LocalDate date){
        return date.format(DEFAULT_DATE_FORMATTER);
    }

    public static String dateOf(int year, int month, int dayOfMonth){
        return LocalDate.of(year, month, dayOfMonth).format(DEFAULT_DATE_FORMATTER);
    }

    public static String dateOf(int year, int month, int dayOfMonth, String pattern){
        return LocalDate.of(year, month, dayOfMonth).format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }

    /**
     * 指定时间
     */
    public static String timeOf(LocalTime time){
        return time.format(DEFAULT_TIME_FORMATTER);
    }

    public static String timeOf(int hour, int minute, int second){
        return LocalTime.of(hour, minute, second).format(DEFAULT_TIME_FORMATTER);
    }

    public static String timeOf(int hour, int minute, int second, String pattern){
        return LocalTime.of(hour, minute, second).format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }

    /**
     * 指定日期时间
     */
    public static String dateTimeOf(LocalDateTime dateTime){
        return dateTime.format(DEFAULT_DATE_TIME_FORMATTER);
    }

    public static String dateTimeOf(int year, int month, int dayOfMonth, int hour, int minute, int second){
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second).format(DEFAULT_DATE_TIME_FORMATTER);
    }

    public static String dateTimeOf(int year, int month, int dayOfMonth, int hour, int minute, int second, String pattern){
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second).format(DateTimeFormatter.ofPattern(pattern, Locale.CHINESE));
    }

    /**
     * 当月第一天
     */
    public static String firstDayOfThisMonth(){
        LocalDate date = LocalDate.now();
        return firstDayof(date.getYear(), date.getMonthValue());
    }

    /**
     * 当月最后一天
     */
    public static String lastDayOfThisMonth(){
        LocalDate date = LocalDate.now();
        return lastDayof(date.getYear(), date.getMonthValue());
    }

    /**
     * 指定月份第一天
     */
    public static String firstDayof(int year, int month){
        return dateOf(year, month, 1);
    }

    public static String firstDayof(int year, int month, String pattern){
        return dateOf(year, month, 1, pattern);
    }

    /**
     * 指定月份最后一天
     */
    public static String lastDayof(int year, int month){
        Month m = Month.of(month);
        return dateOf(year, month, m.length(year % 4 == 0 && year % 100 != 0 || year % 400 == 0));
    }
    public static String lastDayof(int year, int month, String pattern){
        Month m = Month.of(month);
        return dateOf(year, month, m.length(year % 4 == 0 && year % 100 != 0 || year % 400 == 0), pattern);
    }



    //LocalDateTime/LocalDate/LocalTime -> java.util.Date

    public static Date convertToDate(LocalDateTime dateTime) {
        return  Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDate(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return  Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDate(LocalDate date, String time){
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.parse(time));
        return convertToDate(dateTime);
    }

    public static Date convertToDate(String date, LocalTime time){
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(date), time);
        return convertToDate(dateTime);
    }

    public static Date convertToDate(String date, String time){
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
        return convertToDate(dateTime);
    }




    //java.util.Date ->  LocalDateTime/LocalDate/LocalTime

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate convertToLocalDate(Date date) {
        LocalDateTime dateTime = convertToLocalDateTime(date);
        return LocalDate.of(dateTime.getYear(),dateTime.getMonthValue(),dateTime.getDayOfMonth());
    }

    public static LocalTime convertToLocalTime(Date date) {
        LocalDateTime dateTime = convertToLocalDateTime(date);
        return LocalTime.of(dateTime.getHour(),dateTime.getMinute(),dateTime.getSecond());
    }

}
