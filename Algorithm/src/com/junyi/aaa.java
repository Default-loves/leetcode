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

        List<Integer> list = Arrays.asList(1, 1, 2, 2, 10, 10, 16);
        int res = 0;
        for (Integer i : list) {
            res ^= i;
        }
        System.out.println(res);

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

