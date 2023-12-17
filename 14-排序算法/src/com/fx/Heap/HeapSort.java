package com.fx.Heap;

import com.fx.BaseSort;
import com.fx.util.ArrayUtil;

import java.util.Arrays;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/18 0:03
 */
public class HeapSort extends BaseSort {

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.array = ArrayUtil.randomArray(10, 100);
        heapSort.sort();
        System.out.println(Arrays.toString(heapSort.array));
    }

    private int heapSize;

    protected void sort() {
        heapSize = array.length;
        // 原地建堆
        heapify();

        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);
            // 对0位置进行下滤（恢复堆的性质）
            siftDown(0);
        }
    }

    /**
     * 批量建堆
     */
    private void heapify() {
        // 自下而上的下滤
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }


    /**
     * 下滤
     *
     * @param index 结点的索引
     */
    private void siftDown(int index) {
        // 不能是叶子结点（必须要有子结点）
        int half = heapSize >> 1;
        int element = array[index];
        // 必须只有非叶子结点才能进入循环（第一个叶子结点的索引==非叶子结点的数量）
        // 更具完全二叉树的性质，第一个叶子结点的索引为：floor(size / 2)
        while (index < half) {
            // index 的节点有两种情况
            // 1. 只有左子节点 2. 同时有左右子节点
            // 默认跟左子节点进行比较
            int childIndex = (index << 1) + 1;
            int childElement = array[childIndex];
            // 右子节点
            int rightIndex = childIndex + 1;
            // 选出左右子节点中最大的
            if (rightIndex < heapSize && array[rightIndex] > array[childIndex]) {
                childIndex = rightIndex;
                childElement = array[rightIndex];
            }
            if (element >= childElement) {
                break;
            }
            // 将子结点存放到index位置
            array[index] = childElement;
            index = childIndex;
        }
        array[index] = element;
    }
}
