### Логирование (стандартная библиотека)
`Java Logging API`

```Java
import java.util.logging.*;

public class LogDemo {
	private static final Logger LOGGER = 
		Logger.getLogger(LogDemo.class.getName()); // В качестве имени логгера у нас будет пакет + имя класса. Это позволит гибко управлять логгерами.
}


// SEVERE - серъезные ошибки
// WARNING - предупреждения
// INFO -  инфа о нормальном функционировании (уровень по умолчанию)
// CONFIG - логирование конфигрурационных параметров
// FINE, FINER, FINEST - для детального логирования о работе программы (вход\выход а методы и прочее)
LOGGER.log(Level.INFO, "I'm logging");

LOGGER.warning("We have a problem!");
```
Конфигурирование логгера
* в коде setLevel(Level.WARNING)
* в конф файле

```Java
//Лучше не юзать конкатенацию, а делать так:
LOGGER.log(Level.FINEST, "value of x is {0}", x);
LOGGER.log(Level.FINEST, "coordinate are ({0}, {1})", new Object[] {x, y});
LOGGER.log(Level.SEVERE, "unexpected exception", e);
```

Обработчик сообщения, определяет куда будет записано сообщение
* java.util.logging.ConsoleHandler
* java.util.logging.FileHandler
* java.util.logging.SocketHandler
* своя реализация

Задается в конф файле или в коде .addHandler

В каком формате буем писать?
* java.util.logging.SimpleFormatter
* java.util.logging.XMLFormatter
* свой велосипед

logging.properties
```properties
# To use this config start JVM with parameter:
# -Djava.util.logging.config.file=logging.properties

.level=ALL
.handlers=java.util.logging.ConsoleHandler
java.util.logging.ConsoleHandler.level=ALL
```

```Java
private static void configureLogging() {
        Logger logA = Logger.getLogger("org.stepic.java.logging.ClassA");
        logA.setLevel(Level.ALL);

        Logger logB = Logger.getLogger("org.stepic.java.logging.ClassB");
        logB.setLevel(Level.WARNING);

        Logger logOther = Logger.getLogger("org.stepic.java");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        logOther.addHandler(handler);
        logOther.setUseParentHandlers(false);
}
```

### SLF4J (Simple Logging Facade for Java)
>библиотека для протоколирования, ставящая своей целью предоставить максимально простой, но при этом мощный фасад для различных систем протоколирования на Java.

```Java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wombat {

        final Logger logger = LoggerFactory.getLogger(Wombat.class);
        Integer t;
        Integer oldT;

        public void setTemperature(Integer temperature) {

                oldT = t;        
                t = temperature;

                logger.debug("Temperature set to {}. Old temperature was {}.", t, oldT);

                if(temperature.intValue() > 50) {
                        logger.info("Temperature has risen above 50 degrees.");
                }
        }
}
```

[Статья на хабре про разные логгеры](https://habrahabr.ru/post/247647/)