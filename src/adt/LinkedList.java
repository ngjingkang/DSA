
package adt;

import entity.Programme;
import entity.Tutor;
import java.util.Iterator;


public class LinkedList<T> implements ListInterface<T> {

    

    

    
//Node for the linked list
    private class Node{
    private T value;
    private Node next = null;
    private Node previous = null;
    }
    //number of nodes
    private int numberOfNodes = 0;
    //first node of the list
    private Node first;
    //last node of the list
    private Node last;
    
    @Override
    public boolean add(T newEntry) {
        //creates new node
        Node node = new Node();
        node.value = newEntry;
         //if list is empty then first and last node is the new node
            if(isEmpty()){
            first = last = node;
            
        //else the next node of the previous last node will become the new node and the new node will become the last node
        }else{
            last.next = node;
            node.previous = last;
            last = node;
        }
        numberOfNodes++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if(newPosition > 0 && newPosition <= getNumberOfEntries()+1){
            if(newPosition == 1){
                Node node = new Node();
                node.value = newEntry;
                //if list is empty then first and last node is the new node
                if(isEmpty()){
                    first = last = node;
                    
                //else the next node of the new node will be the previous first node and the new node will be the first node
                }else{
                    node.next = first;
                    first.previous = node;
                    first = node;
                }
            }else if(newPosition == getNumberOfEntries()+1){
                add(newEntry);
                numberOfNodes--;
            }else{
                int count = 1;
                Node currentNode = first;
                while(count != newPosition){
                    currentNode = currentNode.next;
                    count++;
                }
                Node node = new Node();
                node.value = newEntry;
                Node previousNode = currentNode.previous;
                previousNode.next = node;
                node.previous = previousNode;
                node.next = currentNode;
                currentNode.previous = node;
                
            }
        }
        numberOfNodes++;
        return true;
    }

    @Override
    public T remove(int entryPosition) {
        if(entryPosition > 0 && entryPosition <= getNumberOfEntries()){  
            if(getNumberOfEntries() == 1){
                Node holder = first;
                first = last =null;
                numberOfNodes--;
                return holder.value;
            }else if(getNumberOfEntries() == 2){
                Node holder;
                if(entryPosition ==1){
                    
                    first.next = null;
                    last.previous = null;
                    holder = first;
                    first = last;
                   
                }else{
                    first.next = null;
                    holder = last;
                    last.previous = null;
                    last = first;
                }
                numberOfNodes--;
                return holder.value;
            }else{
               if(entryPosition == 1){
               Node newFirst = first.next;
               newFirst.previous = null;
               first.next = null;
               Node holder = first;
               first = newFirst;
               numberOfNodes--;
               return holder.value;
            }else if(entryPosition == getNumberOfEntries()){
               Node newLast = last.previous;
               last.previous = null;
               Node holder = last;
               last = newLast;
               last.next = null;
               numberOfNodes--;
               return holder.value;            
            }else{
            Node currentNode = first;
            int count = 1;    
            while(count != entryPosition){
                currentNode = currentNode.next;
                count++;
            }
            Node previousNode = currentNode.previous;
            Node nextNode = currentNode.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            currentNode.next = currentNode.previous = null;
            numberOfNodes--;
            return currentNode.value;
            }
            }
            
            
        }else{
            return null;
        }
    }
    @Override
    public void clear() {
        if(getNumberOfEntries() >= 2){
            Node currentNode = first;
            
            while(currentNode != null){
                Node nextNode = currentNode.next;
                currentNode.next = null;
                currentNode.previous = null;
                currentNode = nextNode;
            }
        }
        first = last = null;
        numberOfNodes = 0;
    }

    @Override
    public boolean replace(int entryPosition, T newEntry) {
        if(entryPosition > 0 && entryPosition <= getNumberOfEntries()){
            Node currentNode = first;
            int count = 1;    
            while(count != entryPosition){
                currentNode = currentNode.next;
                count++;
            }
            currentNode.value = newEntry;         
            return true;
        }
        return false;
        
    }

    @Override
    public T getEntry(int entryPosition) {
        // 1 is the first index not 0
        if(getNumberOfEntries() >= entryPosition && entryPosition > 0){
            int count = 1;
            Node currentNode = first;
            while(count != entryPosition){
                currentNode = currentNode.next;
                count++;
            }
            return currentNode.value;
        }   
        return null;
    
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = first;
            //tranverse until the last node
            while (currentNode != null) {
                if(anEntry.equals(currentNode.value)){
                return true;
                }
            currentNode = currentNode.next;
            }
        return false;
    }
    

    @Override
    public int getNumberOfEntries() {
        return numberOfNodes;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean isFull() {
        //linked list does not have a fixed capacity
        return false;
        
    }
    @Override
    public Iterator<T> iterator() {
       return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T> {

    private LinkedList.Node currentNode;

    public LinkedListIterator() {
      currentNode = first;
    }

    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    @Override
    public T next() {
      if (hasNext()) {
        T returnData = (T) currentNode.value;
        currentNode = currentNode.next;
        return returnData;
      } else {
        return null;
      }
    }
    public T current(){
        return (T) first.value;
    }
    
    
}}
