package adt;

import entity.Tutor;
import java.util.Iterator;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public interface ListInterface<T> {
    
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public T remove(int entryPosition);
    public void clear();
    public boolean replace(int entryPosition, T newEntry);
    public T getEntry(int entryPosition);
    public boolean contains(T anEntry);
    public int getNumberOfEntries();
    public boolean isEmpty();
    public boolean isFull();

    public Iterator<T> iterator();
    
    
}
