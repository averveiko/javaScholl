### Реализовать кэширующий прокси с возможностью тонкой настройки кэша аннотациями


```Java
@Cache(cacheType=FILE/MEMORY,               // тип кэширования (память/диск)
    fileNamePrefix="prefix",                // префикс имени файла кэша (по умолчанию имя метода)
    identityBy={String.class, int.class},   // классы аргументов, которые необходимо учитывать при определении уникальности результата (по умолчанию все)
    listLength=2,                           // если метод возращает List, ограничить количество кэшируемых элементов
    zip=true)                               // необходимость сжатия файла кэша
```


__Результат работы программы__
Интерфейс
```Java
public interface IService {
    @Cache(cacheType = CacheType.MEMORY, identityBy = {String.class, int.class})
    double doHardWork(String work, int value);

    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "myPrefix", zip = true, listLength = 2)
    List<Double> run(String item);
}
```

Вывод
<pre>
-- Метод doHardWork --

<b>Вызов функции doHardWork, аргументы [work2, 422]</b>
Аннотации метода: @Cache(cacheType=MEMORY, fileNamePrefix=, zip=false, identityBy=[class java.lang.String, int]), listLength=-1)
    Идентифицируем кэш по параметру: class java.lang.String
    Идентифицируем кэш по параметру: class java.lang.Integer
<b>Помещено в кэш |doHardWork| [work2, 422] : 0.003773357080327225</b>
Результат: 0.003773357080327225

<b>Вызов функции doHardWork, аргументы [work2, 422]</b>
Аннотации метода: @Cache(cacheType=MEMORY, fileNamePrefix=, zip=false, identityBy=[class java.lang.String, int]), listLength=-1)
    Идентифицируем кэш по параметру: class java.lang.String
    Идентифицируем кэш по параметру: class java.lang.Integer
<b>Для метода doHardWork ключ [work2, 422] найден в кэше, достаем результат из кэша</b>
Результат: 0.003773357080327225

<b>Вызов функции doHardWork, аргументы [work1, 4]</b>
Аннотации метода: @Cache(cacheType=MEMORY, fileNamePrefix=, zip=false, identityBy=[class java.lang.String, int]), listLength=-1)
    Идентифицируем кэш по параметру: class java.lang.String
    Идентифицируем кэш по параметру: class java.lang.Integer
<b>Помещено в кэш |doHardWork| [work1, 4] : 0.39808917197452226</b>
Результат: 0.39808917197452226

<b>Вызов функции doHardWork, аргументы [work1, 4]</b>
Аннотации метода: @Cache(cacheType=MEMORY, fileNamePrefix=, zip=false, identityBy=[class java.lang.String, int]), listLength=-1)
    Идентифицируем кэш по параметру: class java.lang.String
    Идентифицируем кэш по параметру: class java.lang.Integer
<b>Для метода doHardWork ключ [work1, 4] найден в кэше, достаем результат из кэша</b>
Результат: 0.39808917197452226

-- Метод run --

<b>Вызов функции run, аргументы [Test]</b>
Аннотации метода: @Cache(cacheType=FILE, fileNamePrefix=myPrefix, zip=true, identityBy=[]), listLength=2)
Ошибка загрузки кэша cache\myPrefix.cache.compressed (Не удается найти указанный файл)
<b>Помещено в кэш |run| [Test] : [6.0, 10.0]</b>
Кэш записан в файл cache\myPrefix.cache
Результат: [6.0, 10.0]

<b>Вызов функции run, аргументы [Test]</b>
Аннотации метода: @Cache(cacheType=FILE, fileNamePrefix=myPrefix, zip=true, identityBy=[]), listLength=2)
<b>Для метода run ключ [Test] найден в кэше, достаем результат из кэша</b>
Результат: [6.0, 10.0]

<b>Вызов функции run, аргументы [Test Test]</b>
Аннотации метода: @Cache(cacheType=FILE, fileNamePrefix=myPrefix, zip=true, identityBy=[]), listLength=2)
<b>Помещено в кэш |run| [Test Test] : [13.5, 22.5]</b>
Кэш записан в файл cache\myPrefix.cache
Результат: [13.5, 22.5]

<b>Вызов функции run, аргументы [Test Test]</b>
Аннотации метода: @Cache(cacheType=FILE, fileNamePrefix=myPrefix, zip=true, identityBy=[]), listLength=2)
<b>Для метода run ключ [Test Test] найден в кэше, достаем результат из кэша</b>
Результат: [13.5, 22.5]
</pre>