package com.example.hirurg.contacts;

/**
 * If this code works fine, it was written by HIRURG
 * If no - I don`t know who wrote it
 */

import java.util.HashMap;
import java.util.Map;

public class Properties {
    static Map<String, Integer> properties = new HashMap();

    static final String ORDER_BY = "orderBy";

    static final Integer FIRST_NAME = 101;
    static final Integer LAST_NAME = 102;

    static {
        properties.put(ORDER_BY, FIRST_NAME);
    }
}
