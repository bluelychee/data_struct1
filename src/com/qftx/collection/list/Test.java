package com.qftx.collection.list;

/**
 * Create by lizhihui on 2018/7/30
 */
public class Test {

    public static void main(String[] args) {
        MArrayList<String> list =new MArrayList<>();
        for(int i=0;i<100;i++){
            list.add(String.valueOf(i));
        }

        System.out.println(list.size());
        System.out.println(list.toString());

        list.remove(100);

        System.out.println(list.size());
        System.out.println(list.toString());

    }
}
