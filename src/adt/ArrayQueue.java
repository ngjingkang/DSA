/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import entity.Course;
import java.util.Iterator;

/**
 *
 * @author jw
 */
public class ArrayQueue<T> implements QueueInterface<T>{
    
    private T[] array;
    private final static int frontIndex =0;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayQueue(int initialCapacity){
        array = (T[]) new Object[initialCapacity];
        backIndex = -1;
    }
    
    public void enqueue(T newEntry){
        if(!isArrayFull()){
            backIndex++;
            array[backIndex] = newEntry;
        }
    }
    
    public T getFront(){
        T front = null;
        if(!isEmpty()){
            front = array[frontIndex];
        }
        return front;
    }
    
    public T dequeue(){
        T front = null;
        if(!isEmpty()){
            front = array[frontIndex];
            for(int i = frontIndex; i < backIndex; ++i){
                array [i] = array [i+1];
            }
            backIndex--;
        }
        return front;
    }
     
    public boolean isEmpty(){
        return frontIndex > backIndex;
    }
    
    public void clear(){
        if(!isEmpty()){
            for(int index = frontIndex; index <= backIndex; index++){
                array [index] = null;
            }
            backIndex = -1;
        }
    }
    
    private boolean isArrayFull(){
        return backIndex == array.length - 1;
    }
    
    public Iterator<T> getIterator(){
        return new ArrayQueueIterator();
    }

    @Override
    public int CountEntries() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private class ArrayQueueIterator implements Iterator<T>{
        private int nextIndex;
        
        private ArrayQueueIterator(){
            nextIndex = 0;
        }
        
        @Override
        public boolean hasNext(){
            return nextIndex <= backIndex;
        }
        
        @Override
        public T next(){
            if(hasNext()){
                T nextEntry = array[nextIndex];
                nextIndex++;
                return nextEntry;
            }
            else{
                return null;
            }
        }
    }   
}
