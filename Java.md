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
```Java

```