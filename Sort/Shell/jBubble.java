//File: jBubble.java
//Author: lxw
//Time: 2014-09-20
//Usage: Bubble Sort in Java.

import java.util.*;

public class jBubble{
	public static void bubbleSort(int[] array){
		int bound = array.length - 1;	//OK
		int i;
		int tempBound = 0;
		boolean swap;
		while(true){
			i = 0;
			swap = false;
			while(i < bound){
				if(array[i] > array[i+1]){
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					swap = true;
					tempBound = i;
				}
				++i;
			}
			if(swap){
				bound = tempBound;
			}
			else{
				break;
			}
		}
	}
	public static void bubbleSort(List<Integer> list){
		boolean swap = false;
		int bound = list.size() - 1;
		int tempBound = 0;
		while(true){
			swap = false;
			for(int i = 0; i < bound; ++i){
				int item1 = list.get(i);
				int item2 = list.get(i+1);
				if(item1 > item2){
					list.set(i, item2);
					list.set(i+1, item1);
					swap = true;
					tempBound = i;
				}
			}
			if(swap){
				bound = tempBound;
			}
			else{
				break;
			}
		}
	}
	public static void main(String[] args){
		System.out.println("---------------------------------------------");
		System.out.println("|  Usage: Program ArrayLength               |");
		System.out.println("|  If no ArrayLength offered, 5 is default. |");
		System.out.println("---------------------------------------------\n");

		int arrSize = 5;
		int len = args.length;
		if(len == 1){ // Not if(len == 2): //NOTE: java BubbleSort 6 --> len = 1
			arrSize = Integer.parseInt(args[0]);
		}
		else if(len > 1){
			System.out.println("Too much parameters.");
		}
		int[] list1 = new int[arrSize];	// dynamic array in Java.
		List<Integer> list = new ArrayList<Integer>(arrSize);
		System.out.println(String.format("Input %d numbers:", arrSize));
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < arrSize; ++i){
			int number = in.nextInt();
			list.add(number);
			list1[i] = number;
		}
		System.out.println("---------------ArrayList----------------");
		bubbleSort(list);
		System.out.println(list);

		System.out.println("-----------------Array------------------");
		System.out.println(Arrays.toString(list1));
		bubbleSort(list1);
		System.out.println(Arrays.toString(list1));
	}
}
