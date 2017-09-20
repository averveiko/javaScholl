package ru.sbt.averveyko.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private List<T> collection;

    private Streams(List<T> collection) {
        this.collection = new ArrayList<T>(collection);
    }

    public static <C> Streams<C> of(List<C> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        for (int i = 0; i < this.collection.size(); i++)
            if (! predicate.test(this.collection.get(i)))
                this.collection.remove(i);
        return this;
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        List<R> newList = new ArrayList<>();
        for (T t : collection)
            newList.add(mapper.apply(t));
        return new Streams<R>(newList);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> mapperKey, Function<? super T, ? extends V> mapperValue) {
        Map<K, V> map = new HashMap<>();
        for (T t : collection)
            map.put(mapperKey.apply(t), mapperValue.apply(t));
        return map;
    }
}
