package com.fx.heap;

import com.fx.printer.BinaryTrees;
import org.junit.Test;

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
    public void TestSiftUp() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] arr = new int[]{68, 72, 43, 50, 38};
        for (int i : arr) {
            heap.add(i);
        }
        BinaryTrees.println(heap);
    }
}
