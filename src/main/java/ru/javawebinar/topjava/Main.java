package ru.javawebinar.topjava;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");

ConcurrentHashMap<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();

concurrentHashMap.put(1,"one");

       concurrentHashMap.computeIfPresent(1, (lodkey, oldvalue) -> "TESTING");

        System.out.println(concurrentHashMap.entrySet());

    }
}
