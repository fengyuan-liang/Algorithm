package com.fx.Map;

import com.fx.IMap.Map;
import com.fx.hash.Person;
import com.fx.model.Asserts;
import com.fx.model.Key;
import com.fx.model.SubKey1;
import com.fx.model.SubKey2;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/15 17:09
 * @author: 梁峰源
 */
public class TestHashMap {
    @Test
    public void test01() {
        Person p1 = new Person(18, 650, "张三");
        Person p2 = new Person(18, 65, "张三");
        Person p3 = new Person(23, 65, "李四");
        Map<Person, String> map = new HashMap<>();
        map.put(p1, "张三");
        map.put(p2, "李四");
        map.put(p3, "王五");
        System.out.println(map.size());
        System.out.println(map.get(p1));
        System.out.println(map.get(p2));
        System.out.println(map.get(p3));
        System.out.println(map.get(null));
        map.remove(p1);
        System.out.println(map.size());
    }

    @Test
    public void test02() {
        Person p1 = new Person(18, 650, "张三");
        Person p2 = new Person(18, 65, "张三");
        Person p3 = new Person(23, 65, "李四");
        Map<Person, String> map = new HashMap<>();
        map.put(p1, "张三");
        map.put(p2, "李四");
        map.put(p3, "王五");
        System.out.println(map.containsValue("张三"));
        System.out.println(map.containsValue("李四"));
        System.out.println(map.containsValue("1"));
        map.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });
        map.traversal(new Map.Visitor<Person, String>() {
            @Override
            public boolean visit(Person key, String value) {
                System.out.println("k=" + key);
                System.out.println("v=" + value);
                return false;
            }
        });
    }

    @Test
    public void test03() {
        HashMap<Key, Integer> map = new HashMap<>();
        for (int i = 1; i < 19; i++) {
            map.put(new Key(i),i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(map.get(new Key(1)) + "\t");
        }
    }

    @Test
    public void test04() {
        SubKey1 k1 = new SubKey1(1);
        SubKey2 k2 = new SubKey2(1);
        Map<Object,Integer> map = new HashMap<>();
        map.put(k1,1);
        map.put(k2,2);
        System.out.println(map.size());
    }

    @Test
    public void test05() {
        test2(new HashMap<>());
		test3(new HashMap<>());
		test4(new HashMap<>());
		test5(new HashMap<>());
    }

    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containsKey(10));
        Asserts.test(map.containsKey(null));
        Asserts.test(map.containsValue(null));
        Asserts.test(map.containsValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }
}
