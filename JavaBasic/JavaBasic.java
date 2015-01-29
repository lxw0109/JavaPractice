//File: JavaBasic.java
//Author: lxw
//Time: 2014-09-20
//Usage: Java basic usage.

import java.util.*;

public class JavaBasic{
	public static void main(String[] args){
		//ArrayList.
		//List<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();//OK
		for(int i = 0; i < 10; ++i){
			list.add(i);
		}
		System.out.println(list);
		//System.out.println(list[0]); //No way.
		System.out.println(list.get(0));
	}
}
