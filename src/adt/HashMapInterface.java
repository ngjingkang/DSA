
package adt;


public interface HashMapInterface<K,V> {
    public V put(K key, V value);

    public V remove(K key);

    public V getValue(K key);


}
