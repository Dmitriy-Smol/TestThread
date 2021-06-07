package com.company;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        Instant start = Instant.now();
        System.out.println(Thread.currentThread().getName());
        int [] myArray = CreateMas(100);
        System.out.println(Arrays.toString(myArray));
        threadStart(10, myArray);
        System.out.println("Минимальный элемент: " + SearchMin.getMinAll());
    }

    public static void threadStart (int countThread, int[] mas) {
        int startIter = 0;
        int iter = mas.length/countThread;
        int finishIter = iter;
        Thread [] threads = new Thread[countThread];

        SearchMin.setMinAll(mas[0]);

        for(int i=0; i < countThread; i++){
            SearchMin searchMin = new SearchMin(mas, startIter, finishIter);
            threads[i] = new Thread(searchMin);
            threads[i].start();
            startIter = startIter + iter;
            finishIter = finishIter + iter;
            if (mas.length-finishIter < iter) {
                finishIter = mas.length;
            }
        }

        for(int i = 0; i < threads.length; i++)
            try {
                    threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    public static int[] CreateMas(int count) {
        int[] mas = new int[count];
        for (int i = 0; i < mas.length; i++){
            mas[i] = (int)(Math.random()*100);
        }
        return mas;
    }
}
