package com.sbt.javaschool.averveyko;

import java.util.Collection;
import java.util.Iterator;

/**
 * Учебная реализация обобщенного двусвязного списка
 * @param <E> класс элементов, хранящихся в коллекции
 */
public class MyLinkedList<E> implements Iterable<E> {

    private int size = 0;

    private Node<E> header;

    MyLinkedList() {
        /*
         * Псевдоэлемент списка, Его значение всегда равно null, a свойства next и prev всегда
         * указывают на первый и последний элемент списка. Так как на данный момент
         * список еще пуст, свойства next и prev указывают сами на себя (т.е. на элемент header)
         */
        header = new Node<>(null, null, null);
        header.next = header.prev = header;
    }

    /**
     * Добавляет элемент в конец списка за O(1);
     * @param element добавляемый элемент
     */
    public void add(E element) {
        Node<E> newNode = new Node<>(header.prev, element, header);

        //Переопределяем указатели
        newNode.prev.next = newNode;
        header.prev = newNode;
        size++;
    }

    /**
     * Добавляет элемент по индексу index за O(n/2)
     * @param index позиция добавляемго элемента
     * @param element добавляемый элемент
     */
    public void add(int index, E element) {
        Node<E> prevNode = node(index);
        Node<E> newNode = new Node<>(prevNode.prev, element, prevNode);

        prevNode.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    /**
     * Возвращает узел по индексу за O(n/2)
     * @param index индекс узла
     * @return узел
     */
    public Node<E> node(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<E> n = header;

        //В целях экономии проходим только половину списка
        if (index < (size / 2))     //(index < (size >> 1))
        {
            for (int i = 0; i <= index; i++)
                n = n.next;
        }
        else
        {
            for (int i = size; i > index; i--)
                n = n.prev;
        }
        return n;
    }

    /**
     * Возвращает элемент коллекции за O(n)
     * @param index индекс
     * @return элемент
     */
    E get(int index) {
        return node(index).item;
    }

    /**
     * Удаляет элемент из коллекции за O(n) по индексу
     * @param index индекс
     * @return удаленный элемент
     */
    E remove(int index) {
        Node<E> removeNode = node(index);
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;

        //Обрываем все связи
        removeNode.next = null;
        removeNode.prev = null;
        size--;

        return removeNode.item;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                return node(i++).item;
            }
        };
    }

    /**
     * Добавляем все элементы коллекции c
     * Простейшая реализация
     * @param c источник
     * @return true если добавление прошло успешно
     */
    boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0 ) return false;
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    /**
     * Копируем все элементы в коллекцию c
     * @param c получатель
     * @return true если копирование прошло успешно
     */
    boolean copy(Collection<? super E> c){
        if (size == 0) return false;
        for (E e : this) {
            c.add(e);
        }
        return true;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}


