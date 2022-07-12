package com.fx.test;

import com.fx.RBTree.RedBlackTree;
import com.fx.printer.BinaryTrees;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/6/29 22:07
 * @author: 梁峰源
 */
public class TestRBTree {

    @Test
    public void test01(){
        Integer[] data = {
                55, 42, 96, 62, 24, 29, 18, 2, 89, 40, 23, 3
        };
        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for(int i : data){
            rb.add(i);
        }
        BinaryTrees.println(rb);
        // 删除结点
        for (Integer datum : data) {
            rb.remove(datum);
            System.out.println("删除元素：【" + datum + "】");
            BinaryTrees.println(rb);
            System.out.println("---------------------------------");
        }
    }
}
