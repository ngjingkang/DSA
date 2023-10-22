package adt;

import entity.Tutor;
import java.util.Iterator;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class ArrayList<T> implements ListInterface<T> {
    
    private T[] array;
    private int numOfEntries;
    private static final int DEFAULT_CAPACITY = 10;
    
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayList(int initialCapacity) {
        numOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        // Check if newEntry is a duplicate of existing entry
        for (int i = 0; i < numOfEntries; i++) {
            if (array[i].equals(newEntry)) {
                return false;
            }
        }
        
        // Check if list is full
        if (isFull()) {
            doubleArray();
        }
        
        array[numOfEntries] = newEntry;
        numOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccess = true;
        
        // Check if list is full
        if (isFull()) {
            doubleArray();
        }
        
        if (newPosition >= 1 && newPosition <= numOfEntries + 1) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numOfEntries++;
        }
        else {
            isSuccess = false;
        }
        
        return isSuccess;
    }

    @Override
    public T remove(int entryPosition) {
        T targetEntry = null;
        
        if (entryPosition >= 1 && entryPosition <= numOfEntries) {
            targetEntry = array[entryPosition - 1];
            
            if (entryPosition < numOfEntries) {
                removeGap(entryPosition);
            }
            
            numOfEntries--;
        }
        
        return targetEntry;
    }

    @Override
    public void clear() {
        numOfEntries = 0;
    }

    @Override
    public boolean replace(int entryPosition, T newEntry) {
        boolean isSuccess = true;
        
        if (entryPosition >= 1 && entryPosition <= numOfEntries) {
            array[entryPosition - 1] = newEntry;
        }
        else {
            isSuccess = false;
        }
        
        return isSuccess;
    }

    @Override
    public T getEntry(int entryPosition) {
        T targetEntry = null;
        
        if (entryPosition >= 1 && entryPosition <= numOfEntries) {
            targetEntry = array[entryPosition - 1];
        }
        
        return targetEntry;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean isFound = false;
        
        for (int i = 0; !isFound && i < numOfEntries; i++) {
            if (anEntry.equals(array[i])) {
                isFound = true;
            }
        }
        
        return isFound;
    }

    @Override
    public int getNumberOfEntries() {
        return numOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numOfEntries == array.length;
    }
    
    @Override
    public String toString() {
        String output = "";
        
        for (int i = 0; i < numOfEntries; ++i) {
            output += array[i] + "\n";
        }
        
        return output;
    }
    
    /* Utility Methods Below */
    
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastindex = numOfEntries -  1;
        
        for (int i = lastindex; i >= newIndex; i--) {
            array[i + 1] = array[i];
        }
    }
    
    private void removeGap(int entryPosition) {
        int removedIndex = entryPosition - 1;
        int lastIndex = numOfEntries - 1;
        
        for (int i = removedIndex; i < lastIndex; i++) {
            array[i] = array[i + 1];
        }
    }
    
    private void doubleArray() {
        T[] oldArray = array;
        int oldArraySize = oldArray.length;
        array = (T[]) new Object[2 * oldArraySize];
        
        for (int i = 0; i < oldArraySize; i++) {
            array[i] = oldArray[i];
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < getNumberOfEntries();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            T element = (T) array[currentIndex];
            currentIndex++;
            return element;
        }
    }

    
    
}
