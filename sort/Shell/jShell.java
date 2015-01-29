//File: jShell.java
//Author: lxw
//Time: 2014-09-23
//Usage: Shell sort in Java.

import java.util.*;

public class jShell{
	//-9 96 59 0 2
	private static void shellSort(List<Integer> a) {
		int h = 1;
		int size = a.size();
		while (h < size/3) {
			h = h * 3 + 1;    // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
		}
	 
		for (; h >= 1; h /= 3) {
			for (int k = 0; k < h; k++) {
				for (int i = h + k; i < size; i+=h) {
					for (int j = i; j >= h && a.get(j) < a.get(j-h); j-=h) {
						Collections.swap(a, j, j-h);
					}
				}
			}
		}
	}
	private static void shellSortShell(List<Integer> array){

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
		shellSort(list);
		System.out.println(list);

		//System.out.println("-----------------Array------------------");
		//System.out.println(Arrays.toString(list1));
		//shellSort(list1);
		//System.out.println(Arrays.toString(list1));
	}
}
