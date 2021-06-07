package com.company;

import java.util.Arrays;

public class SearchMin implements Runnable{
    private int[] mas;
    private int startIter;
    private int finishIter;
    private static int minAll;

    public SearchMin(int[] mas, int startIter,int finishIter) {
        this.mas = mas;
        this.startIter = startIter;
        this.finishIter = finishIter;
    }

    public static synchronized void MinEl(int min){
        if (min<minAll){
            setMinAll(min);
        }
    }

    public static void setMinAll(int minAll) {
        SearchMin.minAll = minAll;
    }

    public static int getMinAll() {
        return minAll;
    }

    public int MinSearch(int[] mas, int startIter, int finishIter){
        int min = mas[startIter];
        for (int i = startIter; i < finishIter; i++) {
            if (mas[i] < min) {
                min = mas[i];
            }
        }
        return min;
    }

    @Override
    public void run() {
        int min = MinSearch(mas, startIter, finishIter);
        System.out.printf("%s [%d - %d] Min: %d \n", Thread.currentThread().getName(), startIter, finishIter-1, min);
        MinEl(min);
    }
}
