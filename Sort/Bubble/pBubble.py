#!/usr/bin/python2.7
#File: pBubble.py
#Author: lxw
#Time: 2014-09-19 #Usage: Bubble sort in Python.

import sys

def bubbleSort(array):
    bound = len(array) - 1
    while 1:
        i = 0
        tempBound = 0
        swap = False
        while i < bound:
            if array[i] > array[i+1]:
                array[i], array[i+1] = array[i+1], array[i] 
                tempBound = i
                swap = True
            i += 1

        if swap:
            bound = tempBound
        else:
            break


def main():
    print("---------------------------------------------")
    print("|  Usage: Program ArrayLength               |")
    print("|  If no ArrayLength offered, 5 is default. |")
    print("---------------------------------------------\n")

    arrSize = 5
    argc = len(sys.argv)
    if argc == 2:
        arrSize = int(sys.argv[1])
    elif argc != 1:
        sys.exit("Too much parameters.")
    numbers = []
    print("Input {} numbers:(each line with only 1 number) ".format(arrSize))
    for i in range(arrSize):
        number = input()
        numbers.append(number)

    bubbleSort(numbers)
    print(numbers)


if __name__ == '__main__':
    main()
else:
    print("Being imported as a module.")

