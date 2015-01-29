//File: priorityQueue.java
//Author: lxw
//Time: 2014-10-15
//Usage: Implement the Priority Queue.  (min)Heap

import java.util.Arrays;

class BinHeap{
    private int capacity;
    private int size;
    private int[] heap;

    public BinHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity+1];
        heap[0] = Integer.MIN_VALUE;    //sentinel
    }

    public void buildHeap(){
        for(int i = this.size/2; i > 0; --i){
            percolateDown(i);
        }
    }
    //recursive percoalte down.
    private void percolateDown(int index){
        int child = index * 2;
        if(child > this.size){ //index is leaf node.
            return;
        }
        if(child < this.size){ //two children.
            child = heap[child] <= heap[child+1] ? child : child+1;
        }
        if(heap[child] < heap[index]){
            int temp = heap[child];
            heap[child] = heap[index];
            heap[index] = temp;
            percolateDown(child);
        }
    }

    public void insert(int x){
        if(isFull()){
            System.out.format("Cannot insert element %d, Priority Queue is full.\n", x);
            return;
        }
        int pos = ++this.size;
        while(x < heap[pos/2]){
            heap[pos] = heap[pos/2];
            //percolate up
            pos /= 2;
        }
        heap[pos] = x;
    }

    public int deleteMin(){
        if(isEmpty()){
            return heap[0];
        }
        int min = heap[1];
        int target = heap[this.size];
        --this.size;
        int i = 1;
        int child;
        while(true){ //child node exists
            child = 2 * i;
            if(child > this.size){
                break;
            }
            if(child != this.size){   //2 children
                child = heap[child] > heap[child+1] ? child+1 : child;
            }
            if(target <= heap[child]){  //NOTE:FOR HERE, <= is better than <.
                break;
            }
            else{   //percolate down
                heap[i] = heap[child];
            }
            i = child;
        }
        heap[i] = target;
        return min;
    }

    public int findMin(){
        if(this.size > 0){
            return this.heap[1];
        }
        else{
            System.out.println("No element in the heap");
            return 0;
        }
    }

    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFull(){
        if(this.size == this.capacity){
            return true;
        }
        else{
            return false;
        }
    }

    public void show(){
        for(int i = 1; i <= this.size; ++i){
            System.out.format("%d\t", heap[i]);
        }
        System.out.println();
    }

    public void disturbHeap(){
        int temp = heap[1];
        heap[1] = heap[2];
        heap[2] = temp;
        temp = heap[4];
        heap[4] = heap[8];
        heap[8] = temp;
        temp = heap[3];
        heap[3] = heap[10];
        heap[10] = temp;
        temp = heap[2];
        heap[2] = heap[6];
        heap[6] = temp;
    }
}
public class priorityQueue {
    public static void main(String[] args){
        BinHeap bh = new BinHeap(10);
        bh.insert(5);
        bh.insert(1);
        bh.insert(6);
        bh.insert(2);
        bh.insert(0);
        bh.insert(8);
        bh.insert(4);
        bh.insert(7);
        bh.insert(3);
        bh.insert(9);
        bh.show();
        System.out.println("-----------------------------------");
        System.out.println("Start to test buildHeap method.");
        bh.disturbHeap();
        bh.show();
        bh.buildHeap();
        bh.show();
        System.out.println("-----------------------------------");
        System.out.println("Start delete Minimum element.");
        System.out.println(bh.deleteMin());
        bh.show();
        System.out.println(bh.deleteMin());
        bh.show();
        System.out.println(bh.deleteMin());
        bh.show();
        System.out.println(bh.deleteMin());
        bh.show();
    }
}
