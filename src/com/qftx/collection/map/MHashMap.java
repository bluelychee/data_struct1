package com.qftx.collection.map;

import java.util.Arrays;
import java.util.Objects;

/**
 * Create by lizhihui on 2018/7/31
 */
public class MHashMap<K, V> implements MMap<K, V> {
    private int size;

    private MEntry<K,V>[] table;

    static final int DEFAULT_CAPCITY = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    transient int hashSeed = 0;

    public MHashMap() {
        table = new MEntry[DEFAULT_CAPCITY];
    }

    public MHashMap(int length) {
        table = new MEntry[length];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash,table.length);

        if(table[index] ==null){
            return null;
        }else{
            for (MEntry<K,V> e = table[index]; e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    return e.value;
                }
            }
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = indexFor(hash,table.length);

        if(table[index] ==null){
            table[index] = new MEntry(hash, key, value);
            table[index].index = index;
        }else{
            for (MEntry<K,V> e = table[index]; e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    V oldValue = e.value;
                    e.value = value;
                    //e.recordAccess(this);
                    return oldValue;
                }
                if(e.next ==null) {
                    e.next = new MEntry(hash, key, value);
                    e.next.index = index;
                    break;
                }
            }
        }
        this.size++;
        return value;
    }

    @Override
    public void clear() {
    }

    /*final int hash(Object k){
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }*/

    final int hash(Object k){
        return k.hashCode();
    }

    final int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (MEntry<K, V> kvmEntry : table) {
            sb.append(kvmEntry.toString()+"\n");
        }
        return sb.toString();
    }

    class MEntry<K,V>{
        int hash;
        int index;
        K key;
        V value;
        MEntry next;

        public MEntry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public MEntry(int hash, K key, V value, MEntry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MEntry<?, ?> MEntry = (MEntry<?, ?>) o;
            return hash == MEntry.hash &&
                    Objects.equals(key, MEntry.key) &&
                    Objects.equals(value, MEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (MEntry<K,V> e = this; e != null; e = e.next) {
                //sb.append("{key:"+e.key+",value:"+e.value+",hash:"+e.hash+",index:"+e.index+"},");

                sb.append("{key:"+e.key+",value:"+e.value+"},");
                i++;
            }
            return i+"\t"+sb.toString();
        }
    }

}

