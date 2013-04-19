package name.pehl.piriti.commons.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A Node to create a tree structure. A node can either be a root (no parent), a node or a leaf
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class Node<K, V> implements Iterable<Node<K, V>> {
    private final List<Node<K, V>> childrens;

    private K key;
    private V value;
    private Node<K, V> parent;

    public Node(K key) {
        this(key, null);
    }

    public Node(K key, V value) {
        this(key, value, null);
    }

    public Node(K key, V value, Node<K, V> parent) {
        this(key, value, parent, new ArrayList<Node<K, V>>());
    }

    public Node(K key, V value, Node<K, V> parent, List<Node<K, V>> childrens) {
        this.key = key;
        this.value = value;
        this.parent = parent;
        this.childrens = childrens;
    }


    public Node<K, V> findDirectChildNode(K data) {
        for (Node<K, V> child : childrens) {
            if (data.equals(child.key)) {
                return child;
            }
        }
        return null;
    }

    public Node<K, V> findChildNode(K data) {
        if (this.key.equals(data)) {
            return this;
        }

        return findChildNode(data, childrens);
    }

    public Node<K, V> addChildNode(K data) {
        Node<K, V> child = findChildNode(data);
        if (child == null) {
            child = addChildNode(new Node<K, V>(data));
        }
        return child;
    }

    public Node<K, V> addChildNode(Node<K, V> child) {
        childrens.add(child);
        child.setParent(this);
        return child;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    @Override
    public Iterator<Node<K, V>> iterator() {
        return childrens.iterator();
    }

    private Node<K, V> findChildNode(K data, List<Node<K, V>> childrens) {
        for (Node<K, V> child : childrens) {
            if (data.equals(child.key)) {
                return child;
            }

            findChildNode(data, child.childrens);
        }

        return null;
    }
}
