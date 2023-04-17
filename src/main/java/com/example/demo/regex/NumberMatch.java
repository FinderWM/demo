package com.example.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date: 2022/6/22
 */
public class NumberMatch {

    public static void main(String[] args) {
        Pattern compile = Pattern.compile("^\\d+$");
        Matcher matcher = compile.matcher("p2");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
        System.out.println("-----------------------------p2");

        matcher = compile.matcher("2p");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
        System.out.println("-----------------------------2p");

        matcher = compile.matcher("p2p");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
        System.out.println("-----------------------------p2p");

        matcher = compile.matcher("2");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
        System.out.println("-----------------------------2");

        matcher = compile.matcher("212");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
        System.out.println("-----------------------------212");

        matcher = compile.matcher("");
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
        }
        System.out.println("----------------------------- ");
    }
}
