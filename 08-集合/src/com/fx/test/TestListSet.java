package com.fx.test;

import com.fx.set.ListSet;
import com.fx.set.Set;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/6/29 9:39
 * @author: 梁峰源
 */
public class TestListSet {
    public static void main(String[] args) {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(12);
        listSet.add(10);
        listSet.add(13);
        listSet.add(13);
        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
