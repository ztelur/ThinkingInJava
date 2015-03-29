package com.company.generics;

import java.lang.reflect.Array;

/**
 * Created by randy on 15-3-25.
 */
public class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind) { this.kind=kind; }
    public T[] create(int size) {
        return (T[])Array.newInstance(kind,size);
    }
    public static void main(String[] args) {
        ArrayMaker<String> maker=new ArrayMaker<String>(String.class);
        String[] stringArrays=maker.create(5);
        System.out.println(stringArrays.toString());
    }
}
