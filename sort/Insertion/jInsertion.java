//File: jInsertion.java
//Author: lxw
//Time: 2014-09-22
//Usage: Insertion Sort in Java.

import java.util.*;

public class jInsertion{
	public static void insertionSort(List<Integer> array){
		int len = array.size();
		int i = 1;
		while(i < len){
			int j;
			int target = array.get(i);
			for(j = i-1; j >= 0; --j){
				if(array.get(j) > target){
					array.set(j+1, array.get(j));
				}
				else{
					++j;
					break;
				}
			}
			if(j > 0){	//Get out by break.
				array.set(j, target);
			}
			else{	//Get out of the cycle.
				array.set(0, target);
			}
			++i;
		}
	}

	public static void insertionSort(int[] array){
		int len = array.length;
		int target;
		for(int i = len-2; i >= 0; --i){
			target = array[i];
			int j;
			for(j = i+1; j < len; ++j){
				if(array[j] < target){
					array[j-1] = array[j];
				}
				else{
					--j;
					break;
				}
			}
			if(j == len){
				array[len-1] = target;
			}
			else{
				array[j] = target;
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
		List<Integer> list = new ArrayList<Integer>(arrSize);
		int[] list1 = new int[arrSize];	// dynamic array in Java.
		System.out.println(String.format("Input %d numbers:", arrSize));
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < arrSize; ++i){
			int number = in.nextInt();
			list.add(number);
			list1[i] = number;
		}

		System.out.println(Arrays.toString(list1));
		System.out.println("---------------ArrayList----------------");
		insertionSort(list);
		System.out.println(list);

		System.out.println("-----------------Array------------------");
		insertionSort(list1);
		System.out.println(Arrays.toString(list1));
	}
}
