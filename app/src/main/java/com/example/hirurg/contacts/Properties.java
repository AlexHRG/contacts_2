package com.example.hirurg.contacts;

/**
 * If this code works fine, it was written by HIRURG
 * If no - I don`t know who wrote it
 */

public class Properties {
    static Integer ORDER_BY;

    static final Integer FIRST_NAME = 101;
    static final Integer LAST_NAME = 102;

    static {
        ORDER_BY = FIRST_NAME;
    }

    public static void setOrderBy(Integer orderBy) {
        ORDER_BY = orderBy;
    }
}
