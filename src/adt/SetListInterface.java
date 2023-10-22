package adt;

import java.util.Iterator;

/**
 *
 * @author Yap Ying Sin
 */
public interface SetListInterface<T> {
    
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public T remove(int entryPosition);
    public boolean remove(T anEntry);
    public boolean replace(int entryPosition, T newEntry);
    public void clear();
    public T getEntry(int entryPosition);
    public boolean contains(T anEntry);
    public int getNumberOfEntries();
    public boolean isEmpty();
    public boolean isFull();
    public boolean checkSubset(SetListInterface otherSetList);
    public void union(SetListInterface otherSetList);
    public SetListInterface intersection(SetListInterface otherSetList);
    public SetListInterface difference(SetListInterface otherSetList);
    public Iterator<T> getIterator();
    
}
