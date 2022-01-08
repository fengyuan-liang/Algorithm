package com.fx.arrayList;


import java.util.Arrays;
import java.util.Objects;

/**
 * 添加了缩容方法
 * 缩容条件：剩余空间占总容量的一半时进行缩容
 */
@SuppressWarnings("unchecked")
public class ArrayList2<E> {
    private int size;//元素的数量
    private E[] elements;//所有的元素
    private static final int DEFAULT_CAPACITY = 10;//默认存放十个元素
    private static final int ELEMENT_NOT_FOUND = -1;//没有找到元素的返回值

    public ArrayList2() {
        this(DEFAULT_CAPACITY);
    }

    //通过构造器传入元素的容量
    public ArrayList2(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    /**
     * 在指定索引上插入指定值，指定索引后面的值依次后移
     *
     * @param index   指定索引
     * @param element 指定值
     */
    public void add(int index, E element) {
        //注意这里的范围判断要变大一位，表示可以在末尾插入元素
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * @param element 添加到集合中的元素
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 删除指定索引上的值
     *
     * @param index 指定索引
     * @return 返回被删除元素的值
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        //存放对象的数组需要移除最后一个元素防止浪费空间
        elements[--size]=null;
        //进行缩容判断
        trim();
        return oldElement;
    }


    /**
     * 清除集合中所有的元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i]=null;
        }
        size=0;
    }

    public boolean contains(E element){
        return indexOf(element) >= 0;
    }

    /**
     * 返回对应元素在集合中的索引
     *
     * @param element 指定元素
     * @return 返回指定元素在集合中的索引
     */
    public int indexOf(E element) {
        if(element==null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null) return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                //让使用者指定E的比较方法,如未指定，即比较内存地址
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 设置指定索引的值并返回设置之前的值
     *
     * @param index   指定索引
     * @param element 设置的值
     * @return 返回set值之前的值
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * @param index 指定索引
     * @return 返回指定索引的元素
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * @return 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return 返回此时的元素个数
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sbf.append(", ");
            }
            sbf.append(elements[i]);
        }
        sbf.append("]");
        return sbf.toString();
    }

    /**
     * 保证集合容量足够
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity < oldCapacity) return;
        //扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * 对数组元素空间进行缩容
     * 这里有可能因为系数不合理导致复杂度震荡的现象，例如刚刚remove缩容后又add扩容，反复震荡
     */
    private void trim(){
        int oldCapacity=elements.length;
        int newCapacity=oldCapacity >> 1;
        if(size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;
        //进行缩容
        E[] newElements= (E[]) new Object[newCapacity];
        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);
        elements=newElements;
    }

    /**
     * 添加元素检查索引合法性
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBound(index);
        }
    }

    /**
     * 索引检查合法性
     */
    private void rangeCheck(int index) {
        if (index < 0 || index > size - 1) {
            outOfBound(index);
        }
    }

    /**
     * 索引不合法抛出异常
     */
    private void outOfBound(int index) {
        throw new IndexOutOfBoundsException("索引值不合法,index=" + index + ",实际大小size=" + size);
    }
}
