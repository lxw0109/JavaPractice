#!/usr/bin/python2.7
#File: pInsertion.py
#Author: lxw
#Time: 2014-09-22
#Usage: Insertion Sort in Python.

import sys

#recursive
def insertion_sort(n):
    if len(n) == 1:
        return n
    b = insertion_sort(n[1:])
    m = len(b)
    for i in xrange(m):
        if n[0] <= b[i]:
            return b[:i]+[n[0]]+b[i:]
    return b + [n[0]]


#none recursive
def insertionSort(array):
    i = 1
    length = len(array)
    for i in xrange(1, length):
        target = array[i]
        for j in xrange(i-1, -1, -1):
            if array[j] > target:
                array[j+1] = array[j]
            else:
                j += 1
                break
        array[j] = target


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
    for i in xrange(arrSize):
        number = input()
        numbers.append(number)

    #recursive
    print(insertion_sort(numbers))
    #none recursive
    insertionSort(numbers)
    print(numbers)


if __name__ == '__main__':
    main()
else:
    print("Being imported as a module.")
