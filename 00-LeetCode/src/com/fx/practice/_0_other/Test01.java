package com.fx.practice._0_other;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/6 13:21
 */
public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            String str = sc.nextLine();
            if(str.matches("^[a-zA-Z](?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z\\d]+")){
                System.out.println("Accept");
            }else {
                System.out.println("Wrong");
            }
        }
    }
}
