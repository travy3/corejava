package com.zyu.DataStruct.first;

import java.util.Iterator;

/**
 * Created by travy on 2016/7/14.
 */
public class ResizeingArrayStack<T> implements Iterable<T> {

    private T[] a = (T[]) new Object[1];
    private int N = 0;

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(T t){
        if (N == a.length) resize(2*a.length);
        a[N++] = t;
    }

    private void resize(int max) {
        T[] tTmp = (T[]) new Object[max];
        for (int i = 0; i<a.length; i++){
            tTmp[i] = a[i];
        }
        a = tTmp;
    }

    public T pop(){
        T t = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return t;
    }


    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {

        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public T next() {
            return a[--i];
        }

        public void remove() {
        }
    }
}
