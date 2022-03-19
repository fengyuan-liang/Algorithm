package com.fx.linkedList;

/**
 * 线性表抽象类
 */
public abstract class AbstractList<E> implements List<E>{

    protected int size;//元素的数量

    /**
     * @return 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element 添加到集合中的元素
     */
    public boolean contains(E element){
        return indexOf(element) !=ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素检查索引合法性
     */
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBound(index);
        }
    }

    /**
     * 索引检查合法性
     */
    protected void rangeCheck(int index) {
        if (index < 0 || index > size - 1) {
            outOfBound(index);
        }
    }

    /**
     * 索引不合法抛出异常
     */
    protected void outOfBound(int index) {
        throw new IndexOutOfBoundsException("索引值不合法,index=" + index + ",实际大小size=" + size);
    }
}
