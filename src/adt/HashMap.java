/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Tan Su Jing
 */
import java.io.Serializable;
import adt.HashMap.TableEntry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class HashMap<K, V> implements HashInterface<K, V>, Serializable {

    private TableEntry<K, V>[] hashTable;
    private int numOfEntries;
    private int locationUsed;
    private static final int DEFAULT_SIZE = 5;
    private static final double MAX_LOAD_FACTOR = 0.5;

    public HashMap() {
        this(DEFAULT_SIZE);
    }

    public HashMap(int tableSize) {
        int primeSize = getNextPrime(tableSize);
        hashTable = new TableEntry[primeSize];
        numOfEntries = 0;
        locationUsed = 0;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < hashTable.length; index++) {
            outputStr += String.format("%4d. ", index);
            if (hashTable[index] == null) {
                outputStr += "null\n";
            } else if (hashTable[index].isRemoved()) {
                outputStr += "notIn\n";
            } else {
                outputStr += hashTable[index].getKey() + "" + hashTable[index].getValue() + "\n";
            }
        }
        outputStr += "\n";
        return outputStr;
    }

    //testing
    @Override
    public ArrayList<K> getKeys() {
        ArrayList<K> keylist = new ArrayList<>();
        for (TableEntry<K, V> entry : hashTable) {
            if (entry != null && entry.isIn()) {
                keylist.add(entry.getKey());
            }
        }
        return keylist;
    }

    @Override
    public ArrayList<V> getVal() {
        ArrayList<V> vallist = new ArrayList<>();
        for (TableEntry<K, V> entry : hashTable) {
            if (entry != null && entry.isIn()) {
                vallist.add(entry.getValue());
            }
        }
        return vallist;
    }

    @Override
    public V get(K key) {
        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            return hashTable[index].getValue();
        }

        return null; // Key not found
    }

    @Override
    public V add(K key, V value) {
        V oldValue;

        if (isTableFull()) {
            rehash();
        }

        int index = getHashIndex(key);
        index = probe(index, key);

        if ((hashTable[index] == null) || hashTable[index].isRemoved()) {
            hashTable[index] = new TableEntry<K, V>(key, value);
            numOfEntries++;
            locationUsed++;
            oldValue = null;
        } else {
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);

        }

        return oldValue;
    }

    @Override
    public V remove(K key) {
        V removedValue = null;

        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            removedValue = hashTable[index].getValue();
            hashTable[index].setToRemoved();
            numOfEntries--;
        }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
        V result = null;
        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            result = hashTable[index].getValue();
        }
        return result;
    }

    private int probe(int index, K key) {
        boolean found = false;
        int removedStateIndex = -1;

        while (!found && (hashTable[index] != null)) {
            if (hashTable[index].isIn()) {
                if (key.equals(hashTable[index].getKey())) {
                    found = true;
                } else {
                    index = (index + 1) % hashTable.length;
                }
            } else {
                if (removedStateIndex == -1) {
                    removedStateIndex = index;
                }
                index = (index + 1) % hashTable.length;
            }
        }
        if (found || (removedStateIndex == -1)) {
            return index;
        } else {
            return removedStateIndex;
        }
    }

    private int locate(int index, K key) {
        boolean found = false;

        while (!found && (hashTable[index] != null)) {
            if (hashTable[index].isIn() && key.equals(hashTable[index].getKey())) {
                found = true;
            } else {
                index = (index + 1) % hashTable.length;
            }
        }
        int result = -1;
        if (found) {
            result = index;
        }
        return result;
    }

    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getSize() {
        //return numOfEntries;
        return hashTable.length;
    }

    @Override
    public final void clear() {
        for (int index = 0; index < hashTable.length; index++) {
            hashTable[index] = null;
        }
        numOfEntries = 0;
        locationUsed = 0;
    }

    private int getHashIndex(K key) {
        int hashIndex = key.hashCode() % hashTable.length;

        if (hashIndex < 0) {
            hashIndex += hashTable.length;
        }
        return hashIndex;
    }

    private void rehash() {
        TableEntry<K, V>[] oldTable = hashTable;
        int oldTableSize = hashTable.length;
        int newTableSize = getNextPrime(oldTableSize + oldTableSize);
        hashTable = new TableEntry[newTableSize];
        locationUsed = 0;

        for (int index = 0; index < oldTableSize; index++) {
            if ((oldTable[index] != null) && oldTable[index].isIn()) {
                add(oldTable[index].getKey(), oldTable[index].getValue());
            }
        }
    }

    private boolean isTableFull() {
        return locationUsed > MAX_LOAD_FACTOR * hashTable.length;
    }

    private int getNextPrime(int integer) {
        if (integer % 2 == 0) {
            integer++;
        }

        while (!isPrime(integer)) {
            integer = integer + 2;
        }
        return integer;
    }

    private boolean isPrime(int integer) {
        boolean result;
        boolean done = false;

        if ((integer == 1) || (integer % 2 == 0)) {
            result = false;
        } else if ((integer == 2) || (integer == 3)) {
            result = true;
        } else {
            result = true;
            for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {
                if (integer % divisor == 0) {
                    result = false;
                    done = true;
                }
            }
        }
        return result;
    }

    @Override
    public void forEach(BiConsumer<K, V> action) {
        for (TableEntry<K, V> entry : hashTable) {
            if (entry != null && entry.isIn()) {
                action.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1) {
            TableEntry<K, V> entry = hashTable[index];
            V oldValue = entry.getValue();
            V newValue = remappingFunction.apply(key, oldValue);

            if (newValue != null) {
                entry.setValue(newValue);
            } else {
                // If the remapping function returns null, remove the entry
                remove(key);
            }
            return newValue;
        }
        return null; // Key not found
    }

    public class TableEntry<K, V> implements Serializable {

        private K key;
        private V value;
        private boolean inTable;

        private TableEntry(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
            inTable = true;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V newValue) {
            value = newValue;
        }

        private boolean isIn() {
            return inTable;
        }

        private boolean isRemoved() {
            return !inTable;
        }

        private void setToRemoved() {
            key = null;
            value = null;
            inTable = false;
        }

        private void setToIn() {
            inTable = true;
        }
    }
}
