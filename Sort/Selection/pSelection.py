#!/usr/bin/python2.7
#File: pSelection.py
#Author: lxw
#Time: 2014-09-21
#Usage: Selection Sort in Python.

import sys

def selectionSort(array):
    bound = len(array)
    while bound != 1:
        maxIndex = 0
        for i in range(bound):
            if array[maxIndex] < array[i]:
                maxIndex = i
        if maxIndex != i:
            array[maxIndex], array[i] = array[i], array[maxIndex]
        bound -= 1


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

    selectionSort(numbers)
    print(numbers)


if __name__ == '__main__':
    main()
else:
    print("Being imported as a module.")
