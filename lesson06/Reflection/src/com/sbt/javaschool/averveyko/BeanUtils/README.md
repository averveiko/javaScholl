### Реаолизовать следующий класс по документации

```Java
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values. */

    public static void assign(Object to, Object from) {
        ...
    }
```
**Результат работы программы**

<pre>
<b>
Свойства класса получателя до assign: 
name: Noname
age: 0
</b>
Найден getter public java.lang.String com.sbt.javaschool.averveyko.BeanUtils.Employee.getProfession()
Сеттер-получатель: setProfession
В получателе нет метода setProfession с параметром class java.lang.String

Найден getter public java.lang.String com.sbt.javaschool.averveyko.BeanUtils.Person.getName()
Сеттер-получатель: setName
Найден сеттер: public void com.sbt.javaschool.averveyko.BeanUtils.Person.setName(java.lang.String)
Значение установлено.

Найден getter public int com.sbt.javaschool.averveyko.BeanUtils.Person.getAge()
Сеттер-получатель: setAge
Найден сеттер: public void com.sbt.javaschool.averveyko.BeanUtils.Person.setAge(int)
Значение установлено.
<b>
Свойства класса получателя после assign: 
name: Alex
age: 32
</b>
</pre>