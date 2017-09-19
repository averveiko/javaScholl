package ru.sbt.averveyko.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Streams<T> {
    private List<T> collection;

    private Streams(List<T> collection) {
        //Создаем внутреннюю копию коллекции
        this.collection = new ArrayList<T>(collection);
    }

    public static Streams of(List list) {
        return new Streams(list);
    }

    public Streams filter(Predicate<? super T> predicate) {
        for (int i = 0; i < this.collection.size(); i++)
            if (! predicate.test(this.collection.get(i)))
                this.collection.remove(i);
        return this;
    }

    //<R> Stream<R> map(Function<? super T, ? extends R> mapper)
    public Streams transform(Function<? super T, ? extends R> mapper) {
        //...
        return this;
    }
//
//    public Map toMap(........) {
//        //...
//    }
}
