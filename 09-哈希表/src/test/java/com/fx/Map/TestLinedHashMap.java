package com.fx.Map;

import com.fx.IMap.Map;
import com.fx.hash.Person;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/21 10:45
 * @author: 梁峰源
 */
public class TestLinedHashMap {


    @Test
    public void test02() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("张三", 3);
        map.put("李四", 4);
        map.put("王五", 5);
        map.put("赵六", 6);
        map.put("钱七", 7);
        // 删除结点
        map.remove("李四");
        map.forEach((k, v) -> System.out.println(v));
    }

    @Test
    public void test01() {
        Person p1 = new Person(18, 650, "张三");
        Person p2 = new Person(18, 65, "张三");
        Person p3 = new Person(23, 65, "李四");
        Map<Person, String> map = new HashMap<>();
        map.put(p1, "张三");
        map.put(p2, "李四");
        map.put(p3, "王五");
        java.util.LinkedHashMap
        // s删除
        map.remove(p1);
        map.forEach((k, v) -> System.out.println(v));
    }
}
