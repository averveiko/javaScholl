### Ввод-вывод доступ к файловой системе (продвинутые возможности)
```Java
ProcessBuilder processBuilder = new ProcessBuilder();
processBuilder.command("ls", "-l")
	.directory(new File("/home/stepic/"))
	.redirectInput(Redirect.from(new File("/dev/null"))
	.redirectOutput(Redirect.PIPE)
	.redirectError(Redirect.INHERIT);

Process process = processBuilder.start();
try(BufferedReader reader = new BufferedReader(
		new InputStreamReader(process.getInputStream()))) {
	reader.lines().forEach(System.out::println);
}

int exitValue = process.waitFor();
if (exitValue != 0) {
	sout("Subprocess terminated abnormally");
}
```
### Неблокирующий ввод-вывод
```Java
Path path = Paths.get("test.txt");

        try(ReadableByteChannel in = FileChannel.open(path);
            WritableByteChannel out = Channels.newChannel(System.out)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (in.read(buffer) >= 0 || buffer.position() != 0 ) {
                buffer.flip();
                out.write(buffer);
                buffer.compact();
            }
        }
```
### Optional
```Java
Optional<String> optionalText = Optional.of("foo");
//Если != null - распечатать
optionalText.ifPresent(System.out::println);

Optional<String> bar = Optional.empty();
//value != null ? value : "bar";
String value = bar.orElse("bar");
//Конструкторы
Optional.empty();
Optional.of("bar"); //Нельзя передвать null
Optional.ofNullable("baz"); //Если передать null создаться пустой опшинал
Optional.<CharSequence>ofNullable("baz"); //Явно указываем что мы хотим CharSequence

```

### Collections
```Java
collecton.forEach(Consumer); // Удобный перебор коллекции
map.forEach((k, v) -> System.out.printf("%s => %s\n", k, v)) // Принимает BiConsumer<T, U>
// Получение коллекций только для чтения
Set<String> set = Collection.unmodifableSet(originalSet);
```

### Функциональные интерфейсы
Стандартные функциональные интерфейсы живут в пакете java.util.function (>40)

* Consumer<T>.accept(T t) - принимает значение, но ничего не возвращает

  +(Int, Long, Double, Bi ~Consumer) 


* Supplier<T t>   T get();  - Поставщики, ничего не принимают, возвращают значение

  +(Boolean, Int, Long, Double, ~Supplier) 

* Predicate<T> boolean test(T t); - Принимает значение: возвращает boolean

  +(Int, Long, Double, Bi, ~Predicate)  

* Function<T, R> R apply(T t); - Принимает аргумент и возвращает значение

  +(BiFunction, DoubleF, LongToIntF, ToIntFunction, etc ...)

* UnaryOperator<T> - частный случай функции, когда на входе и на выходе тот же тип
  
  +(BinaryOperator, Int, Long, Double)

### Stream API

Stream, IntStream, LongStream, DoubleStream

```Java
int sum = IntStream.iterate(1, n -> n + 1)  //Первый эл-т последовательности и функция вычисления следующего
    .filter(n -> n % 5 == 0 && n % 2 != 0)  //Фильтруем
    .limit(10)                              //Берем первые 10
    .map(n -> n * n)                        //Возводим в квадрат
    .sum();                                 //Суммируем

```

1. Получение стрима
    ```Java
    //из коллеции: Stream<String> = stringSet.stream();
    //из BufferedReader: Stream<String> = bufferedReader.lines();

    Path path = ...;
    Stream<Path> stream1 = Files.list(path); //один уровень
    Stream<Path> stream2 = Files.walk(path); //рекурсивно

    //стрим из строчки
    IntStream chars = "hello".chars();

    //Генерируем с помощью поставщика (supllier)
    DoubleStream randomNumbers = DoubleStream.generate(Math::random)

    //Итерирование
    IntStream integers = IntStream.iterate(0, n -> n + 1); 

    IntStream smallIntegers = IntStream.range(0, 100); //диапазон 0..99

    IntStream smallIntegers2 = IntStream.rangeClosed(0, 100); //диапазон 0..100

    IntStream combinedStream = IntStream.concat(stream1, stream2); //Конкатенация

    IntStream empty = IntStream.empty(); //Пустой стрим

    double[] array = ...;
    DoubleStream streamFromArray = Arrays.stream(array); //Стрим из массива

    IntStream streamOfElements = IntStream.of(2,4,6,8,10); //Явно перечисляем

    //Есть еще масса других способов.
    ```

2. 0 или > промежуточных операций, преобразований
    ```Java
    stream.filter( n -> n > 100)
        .mapToObj(Integer::toString)
        .flatMapToInt(s -> s.chars()) //  принимает функцию возвращающую стрим, конкатенирует стримы в один стрим
        .peek(System.out:println) // Удобно для отладки, позводяет подсмотреть эл-ты
        .distinct()
        .sorted()
        .skip(3)
        .limit(2);

    ```
3. Единственная терминальная операция (запускающая вычисление).
    ```Java
    IntStream stream1 = ...;
    stream1.forEach(System.out:println); // Выполнить над каждым эл-том

    IntStream stream1 = ...;
    OptionalInt result = stream2.findFirst(); // Первый элемент. Еще есть findAny

    Stream<String> stream3 = ...;
    boolean allStringsAreAtLeast10Chars = stream3.allMath(s -> s.length > 10); // Все элементы удовлетворяют условию
    // anyMath, noneMath

    Stream<String> stream = ...;
    Optional<String> minString = stream.min( //max
        Comparator.comparing(
            String::length, Integer::compare));

    int count = stream.count();
    int sum = stream.sun();

    List<String> list = stream.collect(Collectors.toList()); // Собрать в новое хранилище

    //Свертка. Результат применения оператора к каждой паре стрима. Если стрим был пуст - вернется BigInteger.ZERO 
    BigInteger sum = bigIntsStream.reduce(BigInteger.ZERO, BigInteger::add);
    ```

Иногда надо после закрыть стрим (если работали с файлом или другими ресурсами). Можно try with resources.

Некоторые алгоритмы на стримах:

```Java
public static BigInteger factorial(int n) {
    return IntSream.rangeClosed(1, n)
        .mapToObj(i -> BigInteger.valueOf(i))
        .reduce(BigInteger.ONE, BigInteger::multiply);
}

public static boolean isPalindrome(String s) {
    StringBuilder leftToRight = new StringBuilder();

    s.chars().filter(Character::isLetterOrDigit)
        .map(Character::toLowerCase)
        .forEach(leftToRight::appendCodePoint);

    String Builder rigthToLeft = new StringBuilder(leftToRight).reverse();

    return leftToRight.toString().equals(rightToLeft.toString());
}
```

### Разное
string.split("[^\\p{L}\\p{Digit}]+") // Разбить по словам (только буквы и цифры)