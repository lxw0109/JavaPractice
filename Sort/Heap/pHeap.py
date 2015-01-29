#!/usr/bin/python2.7
#coding:utf-8
#如果想有中文注释就必须得有上面的语句
#File: pHeap.py
#Author: lxw
#Time: 2014-09-24
#Usage: Heap Sort in Python.

def sift_down(lst, start, end):
    print("start:{0}, end:{1}".format(start, end))
    """最大堆调整"""
    root = start
    while True:
        child = 2 * root + 1
        if child > end:
            break
        if child + 1 <= end and lst[child] < lst[child + 1]:
            child += 1
        if lst[root] < lst[child]:
            lst[root], lst[child] = lst[child], lst[root]
            root = child
        else:
            break


def heap_sort(lst):
    print("Before Creation: lst:{}".format(lst))
    print("---------------------创建最大堆-----------------------")
    for start in xrange((len(lst)-2)/2, -1, -1):
        sift_down(lst, start, len(lst)-1)
    
    print("\nAfter Creation: lst:{}".format(lst))
    print("---------------------堆排序-----------------------")
    for end in xrange(len(lst)-1, 0, -1):
        lst[0], lst[end] = lst[end], lst[0]
        sift_down(lst, 0, end - 1)
    print("\nAfter Sort: lst:{}".format(lst))
    return lst

 
def main():
    l = [9, 2, 1, 7, 3, 6, 36, 8, 15, 3, 4]
    print(l)
    print("------------------------------------------------------------")
    heap_sort(l)
    print("------------------------------------------------------------")
    print(l)
 
if __name__ == "__main__":
    main()
