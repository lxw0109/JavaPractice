//File: jSelection.java
//Author: lxw
//Time: 2014-09-21
//Usage: Selection Sort in Java.

import java.util.*;

public class jSelection{
	public static void selectionSort(int[] array){
		int bound = array.length;
		while(bound != 1){
			int maxIndex = 0;
			int i = 0;
			while(i < bound){
				if(array[maxIndex] < array[i]){
					maxIndex = i;
				}
				++i;
			}
			if(maxIndex != bound-1){
				int temp = array[bound-1];
				array[bound-1] = array[maxIndex];
				array[maxIndex] = temp;
			}
			--bound;
		}
	}

	public static void selectionSort(List<Integer> array){
		int bound = array.size();
		while(bound != 1){
			int i = 0;
			int maxIndex = 0;
			while(i < bound){
				if(array.get(maxIndex) < array.get(i)){
					maxIndex = i;
				}
				++i;
			}
			if(maxIndex != bound-1){
				int max = array.get(maxIndex);
				array.set(maxIndex, array.get(bound-1));
				array.set(bound-1, max);
			}
			--bound;
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
		for(int i = 0; i < arrSize; ++i){ int number = in.nextInt();
			list.add(number);
			list1[i] = number;
		}
		System.out.println("---------------ArrayList----------------");
		selectionSort(list);
		System.out.println(list);

		System.out.println("-----------------Array------------------");
		System.out.println(Arrays.toString(list1));
		selectionSort(list1);
		System.out.println(Arrays.toString(list1));
	}
}
