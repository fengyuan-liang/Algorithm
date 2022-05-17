package com.fx.binarySearchTree2;

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
    private static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }
    /**
     * 自定义比较规则2
     */
    private static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    @Test
    public void test01(){
        int[] arr = new int[]{7,4,9,2,5,8,11,3};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Arrays.stream(arr).forEach(bst::add);
        BinaryTrees.println(bst);
        bst.inorderTraversalByStack(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        bst.inorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        //将数据添加到BST中
        Arrays.stream(arr).forEach(bst::add);
        BinaryTrees.println(bst);
        //前序
        System.out.print("前序：");
        bst.preorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        //中序
        System.out.print("中序：");
        bst.inorderTraversalByStack(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        //后序
        System.out.print("后序：");
        bst.postorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        //层次遍历
        System.out.print("层序：");
        bst.leverOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 2;
            }
        });
    }


    @Test
    public void test04(){
        int[] arr = new int[]{1,3,2,5,9,6,7};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        //将数据添加到BST中
        Arrays.stream(arr).forEach(bst::add);
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    @Test
    public void test05(){
    }

}












