package com.xxniu.generic;

import java.util.ArrayList;
import java.util.HashSet;

public class GenericMetodEemo {
	//java������ʹ�÷����ഴ����������
//	ArrayList<String>[] list = new ArrayList<String>()[10];
//	ArrayList<String>[] list = (ArrayList<String>[]) new ArrayList[10];
	public static void main(String[] args) {
		Integer[] integers = {1,2,3,4};
		String[] strings = {"one","two","three","four"};
		GenericMetodEemo.<Integer>print(integers);
		GenericMetodEemo.<String>print(strings);
	}
	public static <E> void print(E[] list){
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}
		System.out.println();
	}

	public static <E extends Comparable<E>> E max(E o1,E o2){
		if(o1.compareTo(o2)>0){
			return o1;
		}else {
			return o2;
		}
	}
}

class Test<T>{
	private ArrayList<T> list = new ArrayList<T>();
	public T getT(){
		T t1 = list.get(0);
		return t1;
	}
}