package com.fx.linkedList;


/**
 * 线性表
 */
public interface List<E> {

    static final int ELEMENT_NOT_FOUND = -1;//没有找到元素的返回值

    /**
     * 在指定索引上插入指定值，指定索引后面的值依次后移
     *
     * @param index   指定索引
     * @param element 指定值
     */
    void add(int index, E element);

    /**
     * @param element 添加到集合中的元素
     */
    void add(E element);

    /**
     * 删除指定索引上的值
     *
     * @param index 指定索引
     * @return 返回被删除元素的值
     */
    E remove(int index);


    /**
     * 清除集合中所有的元素
     */
    void clear();

    boolean contains(E element);

    /**
     * 返回对应元素在集合中的索引
     *
     * @param element 指定元素
     * @return 返回指定元素在集合中的索引
     */
    int indexOf(E element);

    /**
     * 设置指定索引的值并返回设置之前的值
     *
     * @param index   指定索引
     * @param element 设置的值
     * @return 返回set值之前的值
     */
    E set(int index, E element);

    /**
     * @param index 指定索引
     * @return 返回指定索引的元素
     */
    E get(int index);

    /**
     * @return 判断是否为空
     */
    boolean isEmpty();

    /**
     * @return 返回此时的元素个数
     */
    int size();

//
//    /**
//     * 保证集合容量足够
//     */
//    void ensureCapacity(int capacity);
//
//    /**
//     * 添加元素检查索引合法性
//     */
//    void rangeCheckForAdd(int index);
//
//
//    /**
//     * 索引检查合法性
//     */
//    void rangeCheck(int index);
//
//
//    /**
//     * 索引不合法抛出异常
//     */
//    void outOfBound(int index);
}
