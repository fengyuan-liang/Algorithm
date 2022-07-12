package com.fx.test;

import com.fx.set.Set;
import com.fx.set.TreeSet;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/12 13:39
 * @author: 梁峰源
 */
public class TestTreeSet {
    public static void main(String[] args) {
        // 生成一百万个随机数

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(13);
        treeSet.add(13);
        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
