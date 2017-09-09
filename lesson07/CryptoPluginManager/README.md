### Написать EncryptedClassloader 
Данный класслоадер умеет загружать классы из файлов дешифруя их. Ваша задача переопределить метод findClass(). В нем лоадер считывает зашифрованный массив байт, дешифрует его и превращает в класс (с помощью метода defineClass). 

**Использование**

```Java
    public static void main(String[] args) {
        CryptoPluginManager cryptoPluginManager = new CryptoPluginManager("plugins", "secretkey");

        cryptoPluginManager.load("PrinterPlugin").doUsefull();
        cryptoPluginManager.load("AnotherPlugin").doUsefull();
    }
```


<pre>
<ul>
<li>Предварительно утилитой Encryptor шифруем байт-код классов</li>
Шифруем файл C:\Users\admin\IdeaProjects\JavaSchool\lesson07\CryptoPluginManager\plugins\PrinterPlugin.class
Файл PrinterPlugin.class успешно обработан

<li>При попытке загрузить класс без расшифровки получем ошибку</li>
Exception in thread "main" java.lang.ClassFormatError: Incompatible magic value 3113998796 in class file AnotherPlugin

<li>После расшифровки байт-код успешно загружается и исполняется</li>
Загружаем плагин из файла plugins\PrinterPlugin.class
Module class PrinterPlugin running ...
Загружаем плагин из файла plugins\AnotherPlugin.class
Module class AnotherPlugin running ...
</ul>
</pre>

В качестве алгоритма шифрования используется простой xor
```Java
for(int i = 0; i < content.length; i++) {
    result[i] = (byte) (content[i] ^ key[i % key.length]);
}
```