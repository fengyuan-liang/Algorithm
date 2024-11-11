package com.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/23 23:38
 */
public class _1962移除石子使总数最小 {
    public static void main(String[] args) {
        int i = 1;
        System.out.println((++i)+(++i));
        new ArrayList<Integer>().set(0, 1);
        int[] ints = {-1, -1};
        ArrayList<Integer> collect = IntStream.of(ints)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
 class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int pile : piles) {
            pq.offer(pile);
        }
        for (int i = 0; i < k; i++) {
            int pile = pq.poll();
            pile -= pile / 2;
            pq.offer(pile);
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

     public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
         inorderTraversal(root);
         List<List<Integer>> result = new ArrayList<>(queries.size());
         queries.forEach(q -> {
             List<Integer> subResult = new ArrayList<>();
             subResult.add(-1);
             subResult.add(-1);
             for (Integer v : orderArr) {
                 if (v <= q) {
                     subResult.add(0, v);
                 }
                 if (v >= q) {
                     subResult.add(1, v);
                     break;
                 }
             }
             result.add(subResult);
         });
         return result;
     }

     private final List<Integer> orderArr = new ArrayList<>();

     private void inorderTraversal(TreeNode node) {
         if (node == null) {
             return;
         }
         inorderTraversal(node.left);
         orderArr.add(node.val);
         inorderTraversal(node.right);
         return;
     }
}

