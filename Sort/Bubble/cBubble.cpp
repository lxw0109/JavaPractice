//File: cBubble.cpp
//Author: lxw
//Time: 2014-09-19
//Usage: Bubble Sort in C++.

#include <iostream>
using namespace std;
#include <cstdlib>

void bubble(int * array, int length){
	int i;
	bool swap = false;
	int bound = length - 1;
	while(1){
		swap = false;
		int boundTemp = 0;
		for(i = 0; i < bound; ++i){
			if(array[i] > array[i+1]){
				int temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				swap = true;
				boundTemp = i;
			}
		}
		if(!swap){	// No swap.
			break;
		}
		else{	// Swap.
			bound = boundTemp;
		}
	}
}

int main(int argc, const char * argv[])
{
	cout << "---------------------------------------------" << endl;
	cout << "|  Usage: Program ArrayLength               |" << endl;
	cout << "|  If no ArrayLength offered, 5 is default. |" << endl;
	cout << "---------------------------------------------" << endl << endl;

	int arrSize = 5;
	if(argc == 2){
		//arrSize = (int)argv[1];	//NO way
		arrSize = atoi(argv[1]);
	}
	else if(argc != 1){
		cout << "Too much parameters." << endl;
		exit(1);
	}

	int * array = new int[arrSize];
	cout << "Input " << arrSize << " numbers: ";
	int i;
	for(i = 0; i < arrSize; ++i){
		cin >> array[i];
	}

	bubble(array, arrSize);

	for(i = 0; i < arrSize; ++i){
		cout << array[i] << " ";
	}
	cout << endl;

	delete[] array;

	return 0;
}
