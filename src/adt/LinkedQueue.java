/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author ngjin
 * @param <T>
 */
public class LinkedQueue<T> implements QueueInterface<T> {
    private int count;
    private Node firstNode; // references node at front of queue (for dequeue)
    private Node lastNode;
    
    public LinkedQueue() {
    count=0;
    firstNode = null;
    lastNode = null;
  } 

    @Override
  public void enqueue(T newEntry) {
    Node newNode = new Node(newEntry, null);

    if (isEmpty()) {
      firstNode = newNode;
    } else {
      lastNode.next = newNode;
    }

    lastNode = newNode;
    count++;
  } 

    @Override
  public T getFront() {
    T front = null;

    if (!isEmpty()) {
      front = (T) firstNode.data.toString();
    }

    return front;
  } 

    @Override
  public T dequeue() {
    T front = null;
    
    if (!isEmpty()) {
      front = (T) firstNode.data.toString();
      firstNode = firstNode.next;

      if (firstNode == null) {
        lastNode = null;
      }
    } 
    count--;
    return front;
  } // end dequeue
  
    @Override
  public boolean isEmpty() {
    return (firstNode == null) && (lastNode == null);
  }

    @Override
  public void clear() {
    firstNode = null;
    lastNode = null;
  } 
  
    @Override
  public Iterator<T> getIterator() {
    return new LinkedQueueIterator();
  }

  private class LinkedQueueIterator implements Iterator<T> {

    private Node currentNode;

    public LinkedQueueIterator() {
      currentNode = firstNode;
    }

    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    @Override
    public T next() {
      if (hasNext()) {
        T returnData = currentNode.data;
        currentNode = currentNode.next;
        return returnData;
      } else {
        return null;
      }
    }
    public T current(){
        return (T) firstNode.data;
    }
   
  }
  
    
  private class Node {

    private T data; 
    private Node next; 

    private Node(T data) {
      this.data = data;
      this.next = null;
    } 

    private Node(T data, Node next) {
      this.data = data;
      this.next = next;
    } 
  }

     

    public int CountEntries() {
        return count;
    }

    

    public boolean IsFullStud() {
        return false;
    }
  

}
