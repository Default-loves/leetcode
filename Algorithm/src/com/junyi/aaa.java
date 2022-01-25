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
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * User: JY
 * Date: 2020/3/3 0003
 * Description:
 */
public class aaa {
//    private static ThreadLocal<Book> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<Book> threadLocal = new InheritableThreadLocal<>();


    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        threadLocal.set(new Book(1, "pencil fly"));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(MyJob());


        Thread.sleep(10 * 1000);
        System.out.println(threadLocal.get().toString());
        threadLocal.get().setName("key line");
        System.out.println(threadLocal.get().toString());

        executorService.shutdown();

    }

    private static Runnable MyJob() {
        return () -> {
            while (true) {
                try {
                    Book book = threadLocal.get();
                    book.setId(3);
                    System.out.println(book.toString());
                    Thread.sleep(3 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    @Test
    public void test() throws InterruptedException {
        System.out.println(500 * 1001);


    }

}

