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
                17, 29, 40, 48, 97, 59, 65
        };
        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for(int i : data){
            rb.add(i);
        }
        BinaryTrees.println(rb);
    }
}
