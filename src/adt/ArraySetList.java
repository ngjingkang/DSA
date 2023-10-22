package adt;

import java.util.Iterator;

/**
 *
 * @author Yap Ying Sin
 */
public class ArraySetList<T> implements SetListInterface<T> {
    
    private T[] array;
    private int numOfEntries;
    private static final int DEFAULT_CAPACITY = 10;
    
    public ArraySetList() {
        this(DEFAULT_CAPACITY);
    }
    
    public ArraySetList(int initialCapacity) {
        numOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }
    
    @Override
    public boolean add(T newEntry) {
        // Check if newEntry is a duplicate of existing entry
        if (isDuplicate(newEntry)) {
            return false;
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
        // Check if newEntry is a duplicate of existing entry
        if (isDuplicate(newEntry)) {
            return false;
        }
        
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
            return false;
        }
        
        return true;
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
    public boolean remove(T anEntry) {
        for (int i = 0; i < numOfEntries; i++) {
            if (array[i].equals(anEntry)) {
                removeGap(i);
                numOfEntries--;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean replace(int entryPosition, T newEntry) {
        if (isDuplicate(newEntry)) {
            return false;
        }
        
        if (entryPosition >= 1 && entryPosition <= numOfEntries) {
            array[entryPosition - 1] = newEntry;
        }
        else {
            return false;
        }
        
        return true;
    }
    
    @Override
    public void clear() {
        numOfEntries = 0;
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
        for (int i = 1; i <= numOfEntries; i++) {
            if (anEntry.equals(array[i])) {
                return true;
            }
        }
        
        return false;
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
    public boolean checkSubset(SetListInterface otherSetList) {
        if (otherSetList instanceof ArraySetList) {
            ArraySetList aSetList = (ArraySetList) otherSetList;
            if (aSetList.numOfEntries > this.numOfEntries) {
                return false;
            }
            
            for (int i = 0; i < aSetList.numOfEntries; i++) {
                boolean found = false;
                for (int j = 0; j < this.numOfEntries && !found; j++) {
                    if (aSetList.array[i].equals(array[j])) {
                        found = true;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void union(SetListInterface otherSetList) {
        
        if (otherSetList instanceof ArraySetList) {
            ArraySetList aSetList = (ArraySetList) otherSetList;
            for (int i = 0; i < aSetList.numOfEntries; i++) {
                add((T) aSetList.array[i]);
            }
        }
    }
    
    @Override
    public SetListInterface intersection(SetListInterface otherSetList) {
        SetListInterface<T> resultSetList = new ArraySetList<>();
        
        if (otherSetList instanceof ArraySetList) {
            ArraySetList aSetList = (ArraySetList) otherSetList;
            
            for (int i = 0; i < aSetList.numOfEntries; i++) {
                boolean found = false;
                for (int j = 0; j < this.numOfEntries && !found; j++) {
                    if (aSetList.array[i].equals(array[j])) {
                        found = true;
                    }
                }
                if (found) {
                    resultSetList.add((T) aSetList.array[i]);
                }
            }
        }
        
        return resultSetList;
    }
    
    @Override
    public SetListInterface difference(SetListInterface otherSetList) {
        SetListInterface<T> resultSetList = new ArraySetList<>();
        
        if (otherSetList instanceof ArraySetList) {
            ArraySetList aSetList = (ArraySetList) otherSetList;
            
            for (int i = 0; i < this.numOfEntries; i++) {
                boolean found = false;
                for (int j = 0; j < aSetList.numOfEntries && !found; j++) {
                    if (this.array[i].equals(aSetList.array[j])) {
                        found = true;
                    }
                }
                if (!found) {
                    resultSetList.add((T) this.array[i]);
                }
            }
        }
        
        return resultSetList;
    }
    
//    @Override
//    public SetListInterface difference(SetListInterface otherSetList) {
//        SetListInterface<T> resultSetList = this;
//        //SetListInterface<T> resultSetList = new ArraySetList<>();
//        
//        if (otherSetList instanceof ArraySetList) {
//            ArraySetList aSetList = (ArraySetList) otherSetList;
//            
//            for (int i = 0; i < aSetList.numOfEntries; i++) {
//                
//                if (this.contains((T) aSetList.array[i])) {
//                    resultSetList.remove(i);
//                }
//            }
//            
////            for (int i = 0; i < aSetList.numOfEntries; i++) {
////                int notFound = 0;
////                for (int j = 0; j < this.numOfEntries; j++) {
////                    if (!aSetList.array[i].equals(array[j])) {
////                        notFound++;
////                    }
////                }
////                
////                if (notFound == this.numOfEntries) {
////                    resultSetList.add((T) aSetList.array[i]);
////                }
////            }
//        }
//        
//        return resultSetList;
//    }
    
    @Override
    public Iterator<T> getIterator() {
        return new ArraySetListIterator();
    }
    
    private class ArraySetListIterator implements Iterator<T> {
        
        private int nextIndex;
        
        private ArraySetListIterator() {
            nextIndex = 0;
        }
        
        @Override
        public boolean hasNext() {
            return nextIndex < numOfEntries;
        }
        
        @Override
        public T next() {
            if (hasNext()) {
                T nextEntry = array[nextIndex];
                nextIndex++;
                return nextEntry;
            }
            else {
                return null;
            }
        }
        
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
    
    private boolean isDuplicate(T newEntry) {
        for (int i = 0; i < numOfEntries; i++) {
            if (array[i].equals(newEntry)) {
                return true;
            }
        }
        return false;
    }
    
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
}
