package com.sbt.javaschool.averveyko;

/*Ваша задача — реализовать структуру данных, которая умеет хранить мультимножество натуральных чисел,
т.е. в этой структуре могут одновременно храниться несколько равных элементов.

Эта структура должна поддерживать две операции:
добавить элемент x в множество
удалить минимальный элемент в множестве и вернуть его значение
(если минимальных элементов несколько, то удаляется только один из них)

Входные данные
Первая строка входных данных содержит число n (1 ≤ n ≤ 106) — количество операций.
Далее в n строках даны описания операций над множеством.
Описание представляет собой число — тип запроса (1 или 2) и число x (1 ≤ x ≤ 109) если это запрос первого типа.
Выходные данные
Для каждого запроса второго типа выведите результат на отдельной строке.*/

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        MultiSet multiSet = new MultiSet();

        int n = in.nextInt(); //Кол-во операций

        for (int i =0; i < n ; i++) {
            int oper = in.nextInt();
            if (oper == 2) { //Удалить минимальный
                out.append(multiSet.remove()).append('\n');
            } else if (oper == 1) { // Добавить элемент
                int value = in.nextInt();
                multiSet.add(value);
            }
        }
        System.out.println(out);
    }
}

class MultiSet {
    //Хак: используем обычный массив, удаленный элемент устанавливаем в 0 (без ресайза массива)
    int[] arr;
    int size;

    public MultiSet() {
        this.arr = new int[1000000];
        this.size = 0;
    }

    public void add(int value) {
        arr[size] = value;
        size++;
    }

    public int remove() {
        int min = 0;
        int minindex = 0;
        //ищем первый минимальный <> 0
        for (int i = 0; i < size; i++) {
            if (arr[i] == 0) continue;
            min = arr[i];
            minindex = i;
            break;
        }

        for (int i = 1; i < size; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] < min) {
                min = arr[i];
                minindex = i;
            }
        }
        arr[minindex] = 0;
        return min;
    }
}

//    class MultiSet {
//        //Тут после каждого удаления ресайзим массив
//        int[] arr;
//        int size;
//
//        public MultiSet() {
//            this.arr = new int[100000];
//            this.size = 0;
//        }
//
//        public void add(int value) {
//            arr[size] = value;
//            size++;
//        }
//
//        public int remove() {
//            int min = arr[0];
//            int minindex = 0;
//
//            for (int i = 1; i < size; i++) {
//                if (arr[i] < min) {
//                    min = arr[i];
//                    minindex = i;
//                }
//            }
//            arr = removeAndTrim(arr, minindex);
//            size--;
//            return min;
//        }
//
//        final private int[] removeAndTrim(int[] arr, int index) {
//            if (index >= 0 && index < arr.length) {
//                int[] copy = new int[100000];
//                System.arraycopy(arr, 0, copy, 0, index);
//                System.arraycopy(arr, index + 1, copy, index, arr.length - index - 1);
//                return copy;
//            }
//            return arr;
//        }
//    }
//}


//Такой вариант не проходит все тесты по лимиту времени
//class MultiSet {
//    List<Integer> arr;
//
//    public MultiSet() {
//        this.arr = new ArrayList<>();
//    }
//
//    public void add(Integer value) {
//        arr.add(value);
//    }
//
//    public Integer remove () {
//        if (arr.size() == 0) return null;
//        Integer min = Collections.min(arr);
//        arr.remove(min);
//        return min;
//    }
//}
