package com.dev.demo.service;

import java.util.concurrent.CompletableFuture;

public interface CalculateService {
  CompletableFuture<Integer> fibonacci(int n);

  CompletableFuture<String> shortestPalindrome(String s);
}
