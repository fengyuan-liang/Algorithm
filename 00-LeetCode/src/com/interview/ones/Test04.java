package com.interview.ones;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String str = "";
        while (!Objects.equals(str, "}")) {
            str = sc.next();
            sb.append(str);
        }
        System.out.println(new Random().nextInt(4));
    }
}
