package de.thro.inf.prg3.a05.collections;

import java.util.Iterator;
import java.util.LinkedList;

public class TreeImpl<V extends Comparable<V>> implements Iterable<V> {

    class Entry {
        public Entry(V value) {
            this.value = value;
        }
        V value;
        Entry left, right;  // two successors!
    }

    Entry root;

    public Iterator<V> iterator() {
        return new TreeIterator();
    }

    public void add(V value) {
        if (root == null) {
            root = new Entry(value);
            return;
        }
        Entry it = root;
        while (it != null) {
            // unchecked cast, runtime hazard: ClassCastException
            int c = value.compareTo(it.value);
            if (c == 0) {
                it.value = value;
                return;
            } else if (c < 0) {
                if (it.left == null) {
                    it.left = new Entry(value);
                    return;
                } else {
                    it = it.left;
                }
            } else {
                if (it.right == null) {
                    it.right = new Entry(value);
                    return;
                } else {
                    it = it.right;
                }
            }
        }
    }

    public V get(V value) {
        Entry it = root;
        while (it != null) {
            // unchecked cast, runtime hazard: ClassCastException
            int c = value.compareTo(it.value);
            if (c == 0) return it.value;
            else if (c < 0) it = it.left;
            else it = it.right;
        }
        return null;
    }

    class TreeIterator<V> implements Iterator {

        private LinkedList<Entry> agenda = new LinkedList<>();

        {
            agenda.add(root);
        }

        @Override
        public boolean hasNext() {
            return agenda.size() > 0;
        }

        @Override
        public Object next() {
            Entry e = agenda.getFirst();
            agenda.removeFirst();
            if (e.left!= null)
                agenda.add(e.left);
            if(e.right != null)
                agenda.add(e.right);
            return e.value;
        }
    }
}
