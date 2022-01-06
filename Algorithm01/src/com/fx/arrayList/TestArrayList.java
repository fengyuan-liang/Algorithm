package com.fx.arrayList;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author: 梁峰源
 * @date: 2021/12/28 17:51
 * Description:测试动态数组
 */
public class TestArrayList {

    @Test
    public void test(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("张三",18,"男"));
        list.add(new Person("张三",18,"男"));
        list.add(new Person("张三",18,"男"));
        list.add(null);
        System.out.println(list.contains(null));
//        list.clear();
//        System.gc();//让JVM去回收内存
    }

    @Test
    public void test02(){
        List<String> list= new java.util.ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);

    }
}
