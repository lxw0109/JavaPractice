#!/usr/bin/python2.7
#File: pShell.py
#Author: lxw
#Time: 2014-09-23
#Usage: Shell Sort in Python.

import sys

def shellSort(array):
    length = len(array)
    INC = length / 2
    while INC:
        for i in xrange(INC, length):
            target = array[i]
            for j in xrange(i, INC-1, -INC):
                if array[j - INC] > target:
                    array[j] = array[j - INC]
                else:
                    j += INC
                    break
            array[j - INC] = target
        INC /= 2


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

    shellSort(numbers)
    print(numbers)

    

if __name__ == '__main__':
    main()
else:
    print("Being imported as a module.")
