package com.maidao.edu.store.api.vip.entity;

import java.util.Calendar;

/*
 * Calendar 是 Java 中用于处理日期和时间的抽象类。
 * 它提供了一种用于操作日期和时间字段的方式，并提供了日历特定的功能，
 * 如添加和减去日期，获取日期的某个字段（如年、月、日、时、分、秒等），以及处理时区、夏令时等问题
 **/
public enum TimeUnit {

    YEAR("Y", Calendar.YEAR),
    MONTH("M", Calendar.MONTH),
    DAY("D", Calendar.DAY_OF_MONTH),
    HOUR("H", Calendar.HOUR_OF_DAY),
    MINUTE("M", Calendar.MINUTE),
    SECOND("S", Calendar.SECOND);

    private final String value;
    private final int calendarUnit;

    private TimeUnit(String value, int calendarUnit) {
        this.value = value;
        this.calendarUnit = calendarUnit;
    }

    public static TimeUnit find(String value) {
        // values() 方法是所有枚举类型都具有的方法，用于返回枚举类型的所有枚举常量
        for (TimeUnit item : TimeUnit.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public int getCalendarUnit() {
        return calendarUnit;
    }
}
