package com.fx.binarySearchTree;

import com.fx.printer.BinaryTrees;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 梁峰源
 * @date: 2022/3/14 22:07
 * Description:
 */
public class TestBST {

    /**
     * 自定义比较规则1
     */
    private static class PersonComparator implements Comparator<Person>{

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }
    /**
     * 自定义比较规则2
     */
    private static class PersonComparator2 implements Comparator<Person>{

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    @Test
    public void test01(){
        int[] arr = new int[]{7,4,9,2,5,8,11,3};
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        Arrays.stream(arr).forEach(bst1::add);
        BinaryTrees.println(bst1);
    }

    @Test
    public void test02(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(20));
        list.add(new Person(19));
        list.stream()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(e -> System.out.println(e.getAge()));
    }

    @Test
    public void test03(){
        int[] arr = new int[]{7,4,9,2,5,8,11,3};
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        //将数据添加到BST中
        Arrays.stream(arr).forEach(bst1::add);
        bst1.leverOrderTraversal(System.out::println);
    }
}
