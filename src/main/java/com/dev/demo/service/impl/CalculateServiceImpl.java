package com.dev.demo.service.impl;

import com.dev.demo.service.CalculateService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CalculateServiceImpl implements CalculateService {
  @Override
  @Async("taskExecutor")
  public CompletableFuture<Integer> fibonacci(int n) {
    return CompletableFuture.completedFuture(CompletableFuture.supplyAsync(() -> calculateFibonacci(n)).thenApplyAsync(v -> v).join());
  }

  private int calculateFibonacci(int n){
    if (n == 1 || n == 2) {
      return 1;
    }
    return calculateFibonacci(n-1) + calculateFibonacci(n-2);
  }

  @Override
  @Async("taskExecutor")
  public CompletableFuture<String> shortestPalindrome(String s) {
    return CompletableFuture.completedFuture(CompletableFuture.supplyAsync(() -> findShortest(s)).thenApplyAsync(res -> res).join());
  }

  private static String scanFromCenter(String s, int left, int right){
    int i =1;
    for (; left-i >= 0; i++) {
      if (s.charAt(left-i) != s.charAt(right+i)){
        break;
      }
    }

    if (left-i >= 0){
      return null;
    }
    StringBuilder sb = new StringBuilder(s.substring(right+i));
    sb.reverse();
    return sb.append(s).toString();
  }

  public static String findShortest(String s){
    if (s == null || s.length() <= 1) {
      return s;
    }
    String result = null;

    int len = s.length();
    int mid = len/2;

    for (int i = mid; i >= 1; i--) {
      if (s.charAt(i) == s.charAt(i-1)) {
        if ((result = scanFromCenter(s, i-1, i)) != null) {
          return result;
        }
      }else {
        if ((result = scanFromCenter(s, i-1, i-1)) != null) {
          return result;
        }
      }
    }
    return result;
  }
}
