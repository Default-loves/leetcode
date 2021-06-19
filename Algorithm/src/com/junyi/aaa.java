package com.junyi;

import javafx.beans.binding.MapExpression;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.ProtectionDomain;
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

    public static void main(String[] args) {
        int a = 10;
        int result = (a * 2 + 12) % 10;
        System.out.println(result);
    }

    private static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }


}

