package cache;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by sumit.sethia on 05/01/19.
 */
public class LRUCacheImplementation<K, V> extends LRUCache<K, V> {

    private Integer cacheSize;
    private Map<K, Node<Pair<K, V>>> hashTable;
    private DoublyLinkedList<Pair<K, V>> doublyLinkedList;

    public LRUCacheImplementation(Integer cacheSize) {
        this.cacheSize = cacheSize;
        hashTable = new HashMap<>(cacheSize);
        doublyLinkedList = new DoublyLinkedList<>(cacheSize);
    }

    @Override
    public V getValue(K key) {
        if (isKeyPresent(key)) {
            Node<Pair<K, V>> node = hashTable.get(key);
            return node.getData().getValue();
        }
        return null;
    }

    @Override
    public boolean isKeyPresent(K key) {
        return hashTable.containsKey(key);
    }

    @Override
    public boolean isCacheFull() {
        return doublyLinkedList.getSize().equals(cacheSize);
    }

    @Override
    public void deleteLeastRecentlyUsedData() {
        Node<Pair<K, V>> tailNode = doublyLinkedList.getTail();
        doublyLinkedList.delete(tailNode);
        hashTable.remove(tailNode.getData().getKey());
    }

    @Override
    public void putDataAsMostRecentlyUsedData(K key, V value) {
        Node<Pair<K, V>> node = new Node<>(new Pair<>(key, value));
        doublyLinkedList.add(node);
        hashTable.put(key, node);
    }

    @Override
    public void deleteDataFromCache(K key) {
        if (isKeyPresent(key)) {
            Node<Pair<K, V>> nodeToDelete = hashTable.get(key);
            doublyLinkedList.delete(nodeToDelete);
            hashTable.remove(key);
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return doublyLinkedList.iterator();
    }

}
