package com.sbt.javaschool.averveyko;

import java.util.Collection;
import java.util.Iterator;

/**
 * Учебная реализация обобщенного двусвязного списка
 * @param <E> класс элементов, хранящихся в коллекции
 */
public class MyLinkedList<E> implements Iterable<E> {

    private int size = 0;

    /*
    * Псевдоэлемент списка, его значение всегда равно null, a свойства next и prev всегда
    * указывают на первый и последний элемент списка.
    */
    private Node<E> header = new Node<>(null, null, null);

    MyLinkedList() {
        //Так как на данный момент список еще пуст, свойства next и prev указывают сами на себя (т.е. на элемент header)
        header.next = header.prev = header;
    }

    /**
     * Возвращает кол-во элементов в коллекции
     * @return кол-во элементов
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка за O(1);
     * @param element добавляемый элемент
     */
    public void add(E element) {
        Node<E> newNode = new Node<>(header.prev, element, null);

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

        if (index == size) {    //Если добавляем в конец
            add(element);
            return;
        }

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
        if (index < (size >> 1))     //Cдвиг вправо - выполняется деление на два с отбрасыванием остатка
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
     * Возвращает элемент коллекции за O(n/2)
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

        if (removeNode.next != null) {                  //Это не последний элемент
            removeNode.prev.next = removeNode.next;
            removeNode.next.prev = removeNode.prev;
        } else {                                        //Это последний элемент
            removeNode.prev.next = null;
            header.prev = removeNode.prev;
        }

        //Обрываем все связи
        removeNode.next = null;
        removeNode.prev = null;
        size--;

        return removeNode.item;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> e = header;

            @Override
            public boolean hasNext() {
                return e.next != null;
            }

            @Override
            public E next() {
                e = e.next;
                return e.item;
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


