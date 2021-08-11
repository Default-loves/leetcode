package com.junyi;

import javafx.beans.binding.MapExpression;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * User: JY
 * Date: 2020/3/3 0003
 * Description:
 */
public class aaa {

    public static void main(String[] args) throws ParseException {
        String s = "2021-08-07 13:46:30";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(s);


        LocalDateTime localDateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

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

