package com.xxniu.Algorithm;
/**
 * 分治法排序
 * @author Administrator
 *
 */
public class MergeSort {
	/**
	 * 拆分
	 * @param list
	 */
	public static void mergeSort(int[] list){
		if(list.length>1){
			int[] firstHalf = new int[list.length/2];
			System.arraycopy(list, 0, firstHalf, 0, list.length/2);
			mergeSort(firstHalf);
			
			int secondHalfLength = list.length - list.length/2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);
			
			int[] temp = merge(firstHalf, secondHalf);
			System.arraycopy(temp, 0, list, 0, temp.length);
		}
	}
	/**
	 * 归并
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static int[] merge(int[] list1,int[] list2){
		int[] temp = new int[list1.length+list2.length];
		int current1 = 0,current2 = 0,current3 = 0;
		
		while(current1<list1.length && current2<list2.length){
			if(list1[current1]<list2[current2]){
				temp[current3++]=list1[current1++];
			}else {
				temp[current3++]=list2[current2++];
			}
		}
		
		while(current1<list1.length){
			temp[current3++]=list1[current1++];
		}
		
		while(current2<list2.length){
			temp[current3++]=list2[current2++];
		}
		return temp;
	}
	/**
	 * 递归是解决问题耗时最短的，也是消耗内存最多的
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list1 = {2,3,2,5,6,1,-2,3,14,12};
		int[] list2 = {2,3,2,5,6,1,-2,3,14,12};
		long mergeTimeFirst = System.currentTimeMillis();
		mergeSort(list1);
		long mergeTimeSecond = System.currentTimeMillis();
		quickSort(list2);
		for (int i = 0; i < list1.length; i++) {
			System.out.print(list1[i]+" ");
		}
		long mergeTimeThird = System.currentTimeMillis();
		System.out.println(mergeTimeSecond);
		System.out.println(mergeTimeThird);
		/*for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}*/
	}

	public static void quickSort(int[] list){
		quickSort(list,0,list.length-1);
	}
	public static void quickSort(int[] list,int first,int last){
		if(last>first){
			int pivotIndex = partition(list,first,last);
			quickSort(list, first, pivotIndex-1);
			quickSort(list, pivotIndex+1, last);
		}
	}
	public static int partition(int[] list,int first,int last){
		int pivot = list[first];
		int low = first + 1;
		int high = last;
		
		while(high > low){
			while(low <= high && list[low] <= pivot){
				low++;
			}
			
			while(low <= high && list[high] > pivot){
				high--;
			}
			
			if(high > low){
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}
		
		while(high > first && list[high] >= pivot){
			high--;
		}
		
		if(pivot > list[high]){
			list[first] = list[high];
			list[high] = pivot;
			return high;
		}else {
			return first;
		}
	}
}
