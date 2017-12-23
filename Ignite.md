# 'Hello World' с Apache Ignite (SQL)

* Предварительно пришлось настроить JAVA_HOME в .bash_profile
```sh
# Java 1.8
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
```

* Качаем сабж, запускаем пару нод ./ignite.sh 
* Запускаем ./sqlline.sh --color=true --verbose=true -u jdbc:ignite:thin://127.0.0.1/

```sh
issuing: !connect jdbc:ignite:thin://127.0.0.1/ '' '' org.apache.ignite.IgniteJdbcThinDriver
Connecting to jdbc:ignite:thin://127.0.0.1/
Connected to: Apache Ignite (version 2.3.0#20171028-sha1:8add7fd5)
Driver: Apache Ignite Thin JDBC Driver (version 2.3.0#20171028-sha1:8add7fd5)
Autocommit status: true
Transaction isolation: TRANSACTION_REPEATABLE_READ
sqlline version 1.3.0
0: jdbc:ignite:thin://127.0.0.1/> CREATE TABLE City (id LONG PRIMARY KEY, name VARCHAR) WITH "template=replicated";
No rows affected (0.712 seconds)
0: jdbc:ignite:thin://127.0.0.1/> CREATE TABLE Person (id LONG, name VARCHAR, city_id LONG, PRIMARY KEY (id, city_id))WITH "backups=1, affinityKey=city_id";
No rows affected (0.184 seconds)
0: jdbc:ignite:thin://127.0.0.1/> CREATE INDEX idx_city_name ON City (name);
No rows affected (0.059 seconds)
0: jdbc:ignite:thin://127.0.0.1/> CREATE INDEX idx_person_name_ ON Person(name); 
No rows affected (0.035 seconds)
0: jdbc:ignite:thin://127.0.0.1/> !tables
+--------------------------------+--------------------------------+------------+
|           TABLE_CAT            |          TABLE_SCHEM           |            |
+--------------------------------+--------------------------------+------------+
|                                | PUBLIC                         | CITY       |
|                                | PUBLIC                         | PERSON     |
+--------------------------------+--------------------------------+------------+
0: jdbc:ignite:thin://127.0.0.1/> !tables
+--------------------------------+--------------------------------+------------+
|           TABLE_CAT            |          TABLE_SCHEM           |            |
+--------------------------------+--------------------------------+------------+
|                                | PUBLIC                         | CITY       |
|                                | PUBLIC                         | PERSON     |
+--------------------------------+--------------------------------+------------+
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO City (id, name) VALUES (1, 'Forest Hill');
1 row affected (0.401 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO City (id, name) VALUES (2, 'Denver');
1 row affected (0.014 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO City (id, name) VALUES (3, 'St. Petersburg');
1 row affected (0.021 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO Person (id, name, city_id) VALUES (1, 'John Doe', 3);
1 row affected (0.049 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO Person (id, name, city_id) VALUES (2, 'Rob Chen', 2);
1 row affected (0.015 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO Person (id, name, city_id) VALUES (3, 'Mary Davis', 1);
1 row affected (0.025 seconds)
0: jdbc:ignite:thin://127.0.0.1/> INSERT INTO Person (id, name, city_id) VALUES (4, 'Richard Miles', 2);
1 row affected (0.017 seconds)
0: jdbc:ignite:thin://127.0.0.1/> SELECT * FROM City;
+--------------------------------+--------------------------------+
|               ID               |              NAME              |
+--------------------------------+--------------------------------+
| 2                              | Denver                         |
| 1                              | Forest Hill                    |
| 3                              | St. Petersburg                 |
+--------------------------------+--------------------------------+
3 rows selected (0.092 seconds)
0: jdbc:ignite:thin://127.0.0.1/> SELECT * FROM Person;
+--------------------------------+--------------------------------+------------+
|               ID               |              NAME              |            |
+--------------------------------+--------------------------------+------------+
| 3                              | Mary Davis                     | 1          |
| 2                              | Rob Chen                       | 2          |
| 1                              | John Doe                       | 3          |
| 4                              | Richard Miles                  | 2          |
+--------------------------------+--------------------------------+------------+
4 rows selected (0.083 seconds)
0: jdbc:ignite:thin://127.0.0.1/> select p.name, c.name from person p join city c on p.city_id = c.id;
+--------------------------------+--------------------------------+
|              NAME              |              NAME              |
+--------------------------------+--------------------------------+
| Rob Chen                       | Denver                         |
| Richard Miles                  | Denver                         |
| Mary Davis                     | Forest Hill                    |
| John Doe                       | St. Petersburg                 |
+--------------------------------+--------------------------------+
4 rows selected (0.054 seconds)
0: jdbc:ignite:thin://127.0.0.1/> UPDATE Person SET name = 'Alex Smith' WHERE id = 2;
0: jdbc:ignite:thin://127.0.0.1/> select p.name, c.name from person p join city c on p.city_id = c.id where c.name = 'Denver';
+--------------------------------+--------------------------------+
|              NAME              |              NAME              |
+--------------------------------+--------------------------------+
| Alex Smith                     | Denver                         |
| Richard Miles                  | Denver                         |
+--------------------------------+--------------------------------+
2 rows selected (0.031 seconds)
```

* Убьем одну ноду, проверяем - данные не потеряны (хотя, когда я убил первую ноду, возник косяк пока я не зарестил еще одну, второй раз проканало.)
```sh
0: jdbc:ignite:thin://127.0.0.1/> select p.name, c.name from person p, city c where p.city_id = c.id;
+--------------------------------+--------------------------------+
|              NAME              |              NAME              |
+--------------------------------+--------------------------------+
| Alex Smith                     | Denver                         |
| Richard Miles                  | Denver                         |
| Mary Davis                     | Forest Hill                    |
| John Doe                       | St. Petersburg                 |
+--------------------------------+--------------------------------+
4 rows selected (0.131 seconds)
```

* После перезаруска грида естественно ничего нет (не использовали persistens)
