package com.fx;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/18 0:08
 */
public abstract class BaseSort {
    protected int[] array;


    protected void swap(int a, int b) {
        if (a == b) {
            return;
        }
        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];
    }

    protected abstract void sort();
}
