### Задание: кстановить SONAR, снять статистику со своего проекта.


В IDEA добавил комманду
```Bash
mvn sonar:sonar \
  -Dsonar.host.url=http://127.0.0.1:9000 \
  -Dsonar.login=31ef4b51e1c6e289351fa73336b8f2fb621d9d51
```

Запустил анализ:
<pre>
[INFO] ANALYSIS SUCCESSFUL, you can browse http://127.0.0.1:9000/dashboard/index/ru.sbt.averveyko:JMSChat
[INFO] Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
[INFO] More about the report processing at http://127.0.0.1:9000/api/ce/task?id=AV_0TIgwls-FmtndU1hX
</pre>

