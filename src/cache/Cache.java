package cache;

import javafx.util.Pair;

import java.util.Iterator;

/**
 * Created by sumit.sethia on 05/01/19.
 */
public interface Cache<K, V> extends Iterable<Pair<K, V>> {
    V get(K key);
    void put(K key, V value);
    void delete(K key);
    void update(K key, V value);
    void printCache();
}
