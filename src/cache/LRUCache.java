package cache;

import javafx.util.Pair;

/**
 * Created by sumit.sethia on 05/01/19.
 */
public abstract class LRUCache<K, V> implements Cache<K, V>{

    @Override
    public synchronized void put(K key, V value) {
        if (isKeyPresent(key)) {
            deleteDataFromCache(key);
        }

        if (isCacheFull()) {
            deleteLeastRecentlyUsedData();
        }

        putDataAsMostRecentlyUsedData(key, value);
    }

    @Override
    public synchronized void delete(K key) {
        if (isKeyPresent(key)) {
            deleteDataFromCache(key);
        }
    }

    @Override
    public synchronized void update(K key, V value) {
        put(key, value);
    }

    @Override
    public V get(K key) {
        V data = null;
        if(isKeyPresent(key)) {
            data = getValue(key);
            delete(key);
            putDataAsMostRecentlyUsedData(key, data);
        }
        return data;
    }

    @Override
    public void printCache() {
        for (Pair<K, V> keyValuePair : this) {
            System.out.println("Key = " + keyValuePair.getKey() + " Value = " + keyValuePair.getValue());
        }
    }

    public abstract V getValue(K key);
    public abstract boolean isKeyPresent(K key);
    public abstract boolean isCacheFull();
    public abstract void deleteLeastRecentlyUsedData();
    public abstract void putDataAsMostRecentlyUsedData(K key, V value);
    public abstract void deleteDataFromCache(K key);

}
