### Задание из слайдов

* Вывести на консоль все методы класса, включая родительские и приватные
* Вывести все геттеры класса
* Проверить что все строковые константы имеют значение = их имени 

**Результат работы программы**

<pre>
<b>Methods declared in class com.sbt.javaschool.averveyko.exercise01.Person</b>
public java.lang.String com.sbt.javaschool.averveyko.exercise01.Person.getName()
public void com.sbt.javaschool.averveyko.exercise01.Person.setName(java.lang.String)
public int com.sbt.javaschool.averveyko.exercise01.Person.getAge()
public void com.sbt.javaschool.averveyko.exercise01.Person.setAge(int)
private java.lang.String com.sbt.javaschool.averveyko.exercise01.Person.getPass()

<b>Methods declared in class java.lang.Object</b>
protected void java.lang.Object.finalize() throws java.lang.Throwable
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException
private static native void java.lang.Object.registerNatives()
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()

<b>Getters declared in class com.sbt.javaschool.averveyko.exercise01.Person</b>
public java.lang.String com.sbt.javaschool.averveyko.exercise01.Person.getName()
public int com.sbt.javaschool.averveyko.exercise01.Person.getAge()
private java.lang.String com.sbt.javaschool.averveyko.exercise01.Person.getPass()

<b>String const name == value</b>
MONDAY true
TUESDAY true
WEDNESDAY true
THURSDAY true
FRIDAY true
SATURDAY true
SUNDAY true
</pre>