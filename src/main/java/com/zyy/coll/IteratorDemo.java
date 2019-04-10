package com.zyy.coll;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
/**
 * 迭代器接口
 * @author Shinelon
 *
 */
public class IteratorDemo {

    @Test
    public void testIterator() {
        IteratorDemoList<String> list = new IteratorDemoList<String>();
        list.add("xiyang0");
        list.add("xiyang1");
        list.add("xiyang2");
        list.add("xiyang3");

        System.out.println("size is " + list.size());

        for (String str : list) {
            System.out.println(str);
        }

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        System.out.println("size is " + list.size());
    }
}

class IteratorDemoList<E> implements Iterable<E> {

    private E[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public IteratorDemoList() {
        elementData = (E[]) new Object[10];
    }

    public void add(E e) {
        elementData[size++] = e;
    }
    public void remove(int lastRet) {
        for (int i=lastRet; i<size-1; i++) {
            elementData[i] = elementData[i+1];
        }
        elementData[size-1] = null;
        size--;
    }
    public E get(int index) {
        return elementData[index];
    }
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            E[] elementData = IteratorDemoList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                IteratorDemoList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

}