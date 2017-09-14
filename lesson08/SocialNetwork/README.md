### Спроектировать дизайн соц. сети.
> В данном задании интересует разбитие приложения на модули, взаимодействие интерфейсов, а не реализация конкретных классов.

> Написать юнит тесты к классам из данного задания (с помощью junit + mockito)

Модули приложения:

* Users - содержит класс User (+ тесты) и интерфейс IUserStorage без реализации;
* Post - содержит класс Post (+ тесты) и интерфейс IPostStorage без реализации;
* Friends - содержит интерфейс IFriendStorage без реализации;
* UserInterface - содержит класс StorageHelper с основной логикой и тестами:
    * getProfile - получить профиль пользователя;
    * getWall - получить "стену" пользователя (посты пользователя);
    * getFriends - получить список друзей пользователя;
    * getFeed - получить новости пользователя (посты друзей за последнее время).

![Схема модулей](img/scheme.svg)

[Основные тесты с использованием JUnit и Mockito смотреть тут ](https://github.com/averveiko/javaSchool/blob/master/lesson08/SocialNetwork/UserInterface/src/test/java/ru/sbt/averveyko/socialnetwork/userinterface/StorageHelperTest.java)

Для нормальной работы пришлось:
* Добавить в `<properties>` чтобы работал diamond<> оператор 
    ```xml
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    ```
* Сменить версию junit на 4.12
* Добавить зависимость на mockito-core:
    ```xml
    <dependency>
       <groupId>org.mockito</groupId>
       <artifactId>mockito-core</artifactId>
       <version>2.10.0</version>
       <scope>test</scope>
    </dependency>
  ```
* Во всех создаваемых пакетах дописывать `<version>1.0</version>`, чтобы можно было ссылаться на артифакт в зависимостях.