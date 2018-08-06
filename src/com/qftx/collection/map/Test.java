package com.qftx.collection.map;

/**
 * Create by lizhihui on 2018/8/2
 */
public class Test {


    public static void main(String[] args) {

        MMap<String,String> map =new MHashMap();
        for(int i=0;i<1000;i++){
            map.put(String.valueOf(i),String.valueOf(i)+"acd");
        }

        System.out.println(map.size());
        System.out.println(map.get("55"));

        System.out.println(map.toString());

    }
}
