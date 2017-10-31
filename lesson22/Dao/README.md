### Написать Dao классы по работе с расписанием (редактирование посещаемости, базовая статистика)

* Students - информация о студентах (колонки: id, имя, фамилия)
* Lessons - расписания занятий (колонки: id, название лекции, дата)
* Student_visits - посещаемость студентов (student_id, lesson_id)

#### Диаграмма БД

![ER Diagram](img/er.png)

#### Проверка работы StudentDao
<pre>
Drop tables if exist

Create tables

Adding students
Student{id=1, firstName='Bill', lastName='Gates'}
Student{id=2, firstName='Steve', lastName='Jobs'}
Student{id=3, firstName='Linus', lastName='Torvalds'}

Getting student from DB by PK
Student{id=1, firstName='Bill', lastName='Gates'}

Getting all students from DB
Student{id=1, firstName='Bill', lastName='Gates'}
Student{id=2, firstName='Steve', lastName='Jobs'}
Student{id=3, firstName='Linus', lastName='Torvalds'}

Update student Linus
Student{id=3, firstName='LINUS', lastName='TORVALDS'}

Delete student Bill
Student{id=2, firstName='Steve', lastName='Jobs'}
Student{id=3, firstName='LINUS', lastName='TORVALDS'}
</pre>