package com.qftx.collection.list;

/**
 * Create by lizhihui on 2018/7/30
 */
public interface MList<T> {

    int size();

    void add(T t);

    T get(int index);

    T remove(int index);
}
