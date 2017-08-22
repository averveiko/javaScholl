package com.sbt.javaschool.averveyko;
/* Задание: Описать абстрактный класс, отнаследоваться от него.
   Наследник должен реализовывай какой-то интерфейс.
*/

public class Main {

    public static void main(String[] args) {
	// write your code here
        User user01 = new User("Bender", 4);
        User user02 = new User("Hubert", 161);
        Moderator moder01 = new Moderator("Fry", 25);
        Moderator moder02 = new Moderator("Lila", 30);

        System.out.println(user01.monetize());
        System.out.println(user02.monetize());

        System.out.println(moder01.monetize());
        System.out.println(moder02.monetize());

        System.out.println(user01.toJSON());
        System.out.println(moder02.toJSON());

        user02.doBan(user01);
        user01.doBan(moder01);

        System.out.println(user01.toJSON());
        System.out.println(user02.toJSON());

    }
}
