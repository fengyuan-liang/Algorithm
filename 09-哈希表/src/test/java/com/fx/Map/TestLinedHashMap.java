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
    public void test01() {
        Person p1 = new Person(18, 650, "张三");
        Person p2 = new Person(18, 65, "张三");
        Person p3 = new Person(23, 65, "李四");
        Map<Person, String> map = new LinkedHashMap<>();
        map.put(p1, "张三");
        map.put(p2, "李四");
        map.put(p3, "王五");
        map.forEach((k, v) -> System.out.println(v));
    }
}
