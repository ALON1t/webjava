package lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingleThread {
    public static void main(String[] args) {
        //耗时多的任务
        calculate(new ArrayList<>());
        calculate(new ArrayList<>());
        //阻塞任务

    }
    public static int calculate(List<Integer> list) {
        //计算量非常大
        return 0;
    }
    public static void print(Scanner scanner) {
        while (scanner.hasNext()) {
            System.out.println();
        }
    }
}
