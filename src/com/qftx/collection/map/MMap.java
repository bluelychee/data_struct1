package com.qftx.collection.map;

/**
 * Create by lizhihui on 2018/7/31
 */
public interface MMap<K,V> {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(K key);

    V put(K key, V value);

    void clear();
}
