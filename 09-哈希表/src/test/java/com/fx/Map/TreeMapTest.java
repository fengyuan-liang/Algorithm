package com.fx.Map;

import com.fx.set.Set;
import com.fx.set.TreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TreeMap Tester.
 *
 * @author fengxian
 * @version 1.0
 * @since <pre>07/12/2022</pre>
 */
public class TreeMapTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testTreeSet() {
        Set<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(5);
        set.add(3);
        set.add(2);
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    @Test
    public void test01() {
        int[][] arr = new int[2][1];
        System.out.println(arr[1][1]);
    }
}
