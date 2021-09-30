package com.junyi;

import javafx.beans.binding.MapExpression;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.io.*;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * User: JY
 * Date: 2020/3/3 0003
 * Description:
 */
public class aaa {

    public static void main(String[] args) throws ParseException, IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> subList = list.subList(1, 10);
        System.out.println(subList.toString());

    }

    private static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    static int lowbit(int x) {
        return x & -x;
    }

}

