package org.example;

import org.example.service.StringListImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        StringListImpl test = new StringListImpl();
        System.out.println("Добавляем строку - " + test.add("Testing..."));
        System.out.println("Добавляем строку - " + test.add("Second string..."));
        //System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println("Удаляем строку - " + test.remove(0));
        //System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println("Добавляем строку - " + test.add("3 string..."));
        //System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println(test);
        System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println("Добавляем строку - " + test.add(1,"4 string"));
        System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println(test);
        System.out.println("Индекс строки с конца - " + test.lastIndexOf("3 string..."));
        System.out.println("Добавляем строку - " + test.set(1,"5 above"));
        System.out.println(test);
        System.out.println("кол-во записей в массиве = " + test.size());
        System.out.println("Удаляем строку по содержимому - " + test.remove("5 above"));
        System.out.println(test);
        System.out.println("Содержит строку ? " + test.contains("5 above"));
        System.out.println("Индекс строки - " + test.indexOf("3 string..."));
        System.out.println("Индекс строки с конца - " + test.lastIndexOf("3 string..."));
        System.out.println("Индекс строки с конца - " + test.get(0));
        System.out.println(test);
        System.out.println("Индекс строки с конца - " + test.equals("Second string..., 3 string..."));


    }
}