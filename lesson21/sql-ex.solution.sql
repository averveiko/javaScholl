/* Решение заданий с сайта http://www.sql-ex.ru/ */

/*
Краткая информация о базе данных "Компьютерная фирма":

Схема БД состоит из четырех таблиц:
Product(maker, model, type)
PC(code, model, speed, ram, hd, cd, price)
Laptop(code, model, speed, ram, hd, price, screen)
Printer(code, model, color, type, price)
Таблица Product представляет производителя (maker), номер модели (model) и тип ('PC' - ПК, 'Laptop' - ПК-блокнот или 'Printer' - принтер). Предполагается, что номера моделей в таблице Product уникальны для всех производителей и типов продуктов. В таблице PC для каждого ПК, однозначно определяемого уникальным кодом – code, указаны модель – model (внешний ключ к таблице Product), скорость - speed (процессора в мегагерцах), объем памяти - ram (в мегабайтах), размер диска - hd (в гигабайтах), скорость считывающего устройства - cd (например, '4x') и цена - price. Таблица Laptop аналогична таблице РС за исключением того, что вместо скорости CD содержит размер экрана -screen (в дюймах). В таблице Printer для каждой модели принтера указывается, является ли он цветным - color ('y', если цветной), тип принтера - type (лазерный – 'Laser', струйный – 'Jet' или матричный – 'Matrix') и цена - price.
*/

/* 1) Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd */
Select model, speed, hd
From PC
Where price < 500

/* 2) Найдите производителей принтеров. Вывести: maker */
Select distinct maker
From Product
Where type = 'Printer'

/* 3) Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол. */
Select model, ram, screen
from laptop
where price > 1000

/* 4) Найдите все записи таблицы Printer для цветных принтеров. */
Select *
from Printer
where color='y'

/* 5) Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.*/
Select model, speed, hd
from Pc
where cd in ('12x', '24x') and price < 600

/* 6)  Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт, найти скорости таких ПК-блокнотов. Вывод: производитель, скорость. */
Select distinct p.maker, l.speed
From Product p join Laptop l
On p.model = l.model
Where l.hd >= 10

/* 7)  Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква). */
Select PC.model, PC.price
from PC join Product p
on PC.model = p.model
where p.maker = 'B'
UNION
Select l.model, l.price
from Laptop l join Product p
on l.model = p.model
where p.maker = 'B'
UNION
Select pr.model, pr.price
from Printer pr join Product p
on pr.model = p.model
where p.maker = 'B'

/* 8) Найдите производителя, выпускающего ПК, но не ПК-блокноты. */
Select distinct p.maker
from Product p
Where type = 'PC'
EXCEPT
Select distinct p.maker
from Product p
Where type = 'Laptop'

/* 9) Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker */
Select distinct p.maker
from Product p join PC
on p.model = PC.model
where PC.speed >= 450

/* 10) Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price */
Select model, price
From Printer
Where price = (
    Select max(price)
    from Printer
)

/* 11) Найдите среднюю скорость ПК. */
Select AVG(speed)
From PC

/* 12) Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол. */
Select AVG(speed)
From Laptop
Where price > 1000

/* 13) Найдите среднюю скорость ПК, выпущенных производителем A. */
Select AVG(speed)
From PC
Where model in (
    Select model
    From Product
    Where maker = 'A'
)

/* 14) Найти производителей, которые выпускают более одной модели, при этом все выпускаемые производителем модели являются продуктами одного типа.
Вывести: maker, type */
select maker , type
from Product 
where maker in (
  select maker 
  from (
    select maker,type 
    from Product 
    group by maker,type
  ) x 
  group by maker 
  having count(*)=1
) 
group by maker,type 
having count(*)>1

/* 15) Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD */
Select hd
from PC
group by hd
having count(hd) > 1

