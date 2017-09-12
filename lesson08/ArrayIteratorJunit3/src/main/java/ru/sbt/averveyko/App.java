package ru.sbt.averveyko;

/**
 * Hello world!
 *
 */
public class App 
{

    public static int ARR_SIZE = 10;

    public static void main( String[] args )
    {
        ArrayWithIterator<String> myArray = new ArrayWithIterator<String>(ARR_SIZE);

        for (int i = 0; i < ARR_SIZE; i++) {
            myArray.put(i, "String " + i);
        }

        System.out.println("Выводим содержимое массива с помощью итератора");
        for (String s : myArray) {
            System.out.println(s);
        }
    }
}
