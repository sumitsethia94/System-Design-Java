package cache;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by sumit.sethia on 05/01/19.
 */
class DoublyLinkedList<T> implements Iterable<T>{
    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public Integer getSize() {
        return size;
    }

    private Node<T> tail;
    private Integer size = 0;
    private Integer maxSize = Integer.MAX_VALUE;

    DoublyLinkedList(int size) {
        this.maxSize = size;
    }

    DoublyLinkedList() {}

    void add (T data) {
        if (size.equals(maxSize)) {
            throw new OutOfMemoryError("Size exceeded maxSize ");
        }
        Node<T> n = new Node<>(data);
        if (head == null) {
            head = n;
        } else {
            n.setRight(head);
            head.setLeft(n);
            head = n;
        }

        if (tail == null) {
            tail = n;
        }
        size++;
    }

    void add (Node<T> n) {
        if (size.equals(maxSize)) {
            throw new OutOfMemoryError("Size exceeded maxSize ");
        }
        if (head == null) {
            head = n;
        } else {
            n.setRight(head);
            head.setLeft(n);
            head = n;
        }

        if (tail == null) {
            tail = n;
        }
        size++;
    }

    void delete (Node<T> deleteNode) {
        if (deleteNode == head) {
            head = head.getRight();
            size--;
            return;
        }

        if (deleteNode == tail) {
            tail = tail.getLeft();
            tail.setRight(null);
            size--;
            return;
        }

        Node<T> prev = deleteNode.getLeft();
        Node<T> next = deleteNode.getRight();

        prev.setRight(next);
        next.setLeft(prev);
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> temp = head;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Reached end of Cache");
                }
                T data = temp.getData();
                temp = temp.getRight();
                return data;
            }
        };
    }

}
