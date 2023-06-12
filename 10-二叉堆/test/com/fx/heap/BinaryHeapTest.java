package com.fx.heap;

import com.fx.printer.BinaryTrees;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/5/19 23:35
 */
public class BinaryHeapTest {

    @Test
    public void TestTopK() {
        Integer[] arr = new Integer[]{75, 57, 65, 13, 34, 79, 9, 23, 31, 27, 7, 3, 37, 90, 48, 95, 11, 32, 41};
        BinaryHeap<Integer> heap = new BinaryHeap<>((o1, o2) -> o2 - o1);
        int k = 5;
        for (int i = 0; i < arr.length; i++) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(arr[i]);
            } else if (arr[i] > heap.get()) { // 如果是第k+1个数
                heap.replace(arr[i]);
            }

        }
        BinaryTrees.println(heap);
        // 输出最大的前五个
        Arrays.stream(arr)
                .sorted((o1, o2) -> o2 - o1)
                .limit(k)
                .forEach(e -> System.out.print("\t" + e));
    }

    @Test
    public void Test() {
        Integer[] arr = new Integer[]{75, 57, 65, 13, 34, 79, 9, 23, 31, 27, 7, 3, 37, 90, 48, 95, 11, 32, 41};
        BinaryHeap<Integer> heap = new BinaryHeap<>(arr, (o1, o2) -> o2 - o1);
        BinaryTrees.println(heap);
    }

    @Test
    public void TestHeapify() {
        Integer[] arr = new Integer[]{75, 57, 65, 13, 34, 79, 9, 23, 31, 27, 7, 3, 37, 90, 48, 95, 11, 32, 41};
        BinaryHeap<Integer> heap = new BinaryHeap<>(arr);
        BinaryTrees.println(heap);
    }

    @Test
    public void TestReplace() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] arr = new int[]{68, 72, 43, 50, 38, 30, 12, 15, 78};
        for (int i : arr) {
            heap.add(i);
        }
        BinaryTrees.println(heap);
        System.out.println(heap.replace(1));
        BinaryTrees.println(heap);
    }

    @Test
    public void TestSiftUp() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] arr = new int[]{68, 72, 43, 50, 38, 30, 12, 15, 78};
        for (int i : arr) {
            heap.add(i);
        }
        BinaryTrees.println(heap);
        Integer remove;
        while ((remove = heap.remove()) != null) {
            System.out.printf("删除结点%s后\n", remove);
            BinaryTrees.println(heap);
        }
    }

    @Test
    public void TestPriorityQueue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int[] arr = {68, 72, 43, 50, 38, 30, 12, 15, 78};
        for (int i : arr) {
            priorityQueue.add(i);
        }
        Integer remove;
        while ((remove = priorityQueue.remove()) != null) {
            System.out.printf("删除结点%s后\n", remove);
        }
    }

    @Test
    public void BubbleSort() {
        int[] arr = {68, 72, 43, 50, 38, 30, 12, 15, 78};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
//                    int tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
