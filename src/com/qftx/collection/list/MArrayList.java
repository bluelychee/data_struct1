package com.qftx.collection.list;

import java.util.Arrays;

/**
 * Create by lizhihui on 2018/7/30
 */
public class MArrayList<T> implements MList<T>{
    private Object[] elementData;
    private int size;
    private static transient final int DEFAULT_LENGTH = 10;


    public MArrayList() {
        this.elementData = new Object[DEFAULT_LENGTH];
    }

    public MArrayList(int size) {
        this.elementData = new Object[size];
    }

    public int length() {
        return elementData.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T t) {
        ensureSize(++ size);
        elementData[size -1] = t;
    }

    @Override
    public T get(int index) {
        if(index < size){
            return elementData(index);
        }else{
            return null;
        }
    }

    @Override
    public T remove(int index) {
        T oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    private void ensureSize(int capcity){
        int oldCapcity = elementData.length;
        if(oldCapcity > capcity){
            return ;
        }
        int newCapcity = oldCapcity + (oldCapcity >> 1);
        int max = Math.max(newCapcity,capcity);
        elementData = Arrays.copyOf(elementData,max);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(i!=0) sb.append(",");
            sb.append(elementData[i].toString());
        }
        return sb.toString();
    }

    private T elementData(int index){
        if(index >= size){
            return null;
        }else{
            return (T) elementData[index];
        }
    }
}
