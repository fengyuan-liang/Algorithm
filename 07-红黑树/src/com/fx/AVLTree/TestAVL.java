package com.fx.AVLTree;

import com.fx.AVLTree.tree.AVLTree;
import com.fx.printer.BinaryTrees;
import com.fx.tree.BinarySearchTree;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 梁峰源
 * @date: 2022/3/14 22:07
 * Description:
 */
public class TestAVL {
    public static void main(String[] args) {
        System.out.println(111);
    }

    @Test
    public void test01() {
        Integer[] data = {
                17, 29, 40, 48, 97, 59, 65
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for(int i : data){
            avl.add(i);
        }
        avl.remove(97);
        avl.remove(59);
        avl.remove(65);
        BinaryTrees.println(avl);
    }

    @Test
    public void test02(){
        List<Integer> data = new ArrayList<>();
        //存入一亿个随机数
        for (int i = 0; i < 1_0000_000; i++) {
            data.add((int) (Math.random() * 1_0000_000));
        }
        //分别用BST和AVL进行性能测试
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        //记录开始时间
        Instant start = Instant.now();
        for (Integer datum : data) {
            bst.add(datum);
        }
        System.out.println("BST添加一千万个数字共耗时："+ Duration.between(start,Instant.now()).toMillis());
        start = Instant.now();
        for (Integer datum : data) {
            bst.remove(datum);
        }
        System.out.println("BST删除一千万数字共耗时："+ Duration.between(start,Instant.now()).toMillis());
        AVLTree<Integer> avlTree = new AVLTree<>();
        start = Instant.now();
        for(Integer datum : data){
            avlTree.add(datum);
        }
        System.out.println("AVL添加一千万数字共耗时："+ Duration.between(start,Instant.now()).toMillis());
        start = Instant.now();
        for(Integer datum : data){
            avlTree.remove(datum);
        }
        System.out.println("AVL删除一千万数字共耗时："+ Duration.between(start,Instant.now()).toMillis());
    }
}












