// Task 1 - Fibonacci
// import java.util.Scanner;
//
//public class Fibonacci{
//    private static long calc_fib(int n) {
//        if (n <= 1)
//            return n;
//
//        long previous = 0;
//        long current = 1;
//
//        for (int i = 2; i <= n; i++) {
//            long new_current = previous + current;
//            previous = current;
//            current = new_current;
//        }
//
//        return current;
//    }
//
//    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//
//        System.out.println(calc_fib(n));
//    }
//}


// Task 2 - FibonacciLastDigit
import java.util.*;

public class Fibonacci {
    private static int getFibonacciLastDigitOptimized(int n) {
        // Период Пизано для модуля 10 равен 60
        n = n % 60;

        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int result = getFibonacciLastDigitOptimized(n);
        System.out.println(result);
    }
}


