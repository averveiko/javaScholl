package ru.sbt.averveyko;

import java.util.Iterator;

public class ArrayWithIterator<T> implements Iterable<T> {
    private final T[] array;
    private final int size;

    public ArrayWithIterator(int size) {
        this.size = size;
        this.array = (T[])(new Object[size]);
    }

    public void put (int index, T element) {
        if (index < 0 && index >= this.size)
            throw new IllegalArgumentException("i must be >= 0 and < " + this.size);
        this.array[index] = element;
    }

    public T get (int index) {
        if (index < 0 && index >= this.size)
            throw new IllegalArgumentException("i must be >= 0 and < " + this.size);
        return this.array[index];
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int i = 0;

            public boolean hasNext() {
                if (i < size) return true;
                return false;
            }

            public T next() {
                return array[i++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
