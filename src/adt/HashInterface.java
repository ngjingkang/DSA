/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 *
 * @author Tan Su Jing
 */
public interface HashInterface<K, V> {

    public V add(K key, V value);

    public V remove(K key);

    public V getValue(K key);

    public boolean contains(K key);

    public boolean isEmpty();

    public boolean isFull();

    public int getSize();

    public void clear();

    public ArrayList<K> getKeys();

    public ArrayList<V> getVal();

    public V get(K key);

    public void forEach(BiConsumer<K, V> action);

    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

}
