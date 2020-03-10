package me.salieri.lab1;

class Lab1 {
  public static void sieveOfEratosthenes(int n) {
    boolean[] primes = new boolean[n + 1];
    for (int i = 1; i <= n; i++) {
      primes[i] = true;
    }

    for (int i = 2; (i * i <= n); i++) {
      if (primes[i]) {
        for (int j = i * i; j <= n; j += i) {
          primes[j] = false;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (primes[i]) {
        System.out.print(i + " ");
      }
    }

    if (primes[n]) {
      System.out.println(n);
    } else {
      System.out.println();
    }
  }

  public static void main(int n) {
    System.out.println("Lab 1:");
    sieveOfEratosthenes(n);
  }
}

public class Main {
  public static void main(String[] args) {
    Lab1.main(10000);
    System.out.println("------------");
  }
}
