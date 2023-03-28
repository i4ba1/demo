package com.dev.demo.controller;

import com.dev.demo.dto.FibonacciDTO;
import com.dev.demo.dto.PalindromeDTO;
import com.dev.demo.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
public class AlgoChallengeController {
  private final CalculateService calculateService;

  public AlgoChallengeController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }


  @PostMapping("/calculateFibonacci")
  public ResponseEntity<Integer> fibonacci(@RequestBody FibonacciDTO fibonacciDTO){
    System.out.println("Fibonacci====>");
    int num = 0;
    try {
      num = calculateService.fibonacci(fibonacciDTO.getNum()).get();
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Fibonacci exception-======> "+e.getCause());
      throw new RuntimeException(e);
    }
    return new ResponseEntity<>(num, HttpStatus.OK);
  }

  @PostMapping("/findShortestPalindrome")
  public ResponseEntity<String> findShortestPalindrome(@RequestBody PalindromeDTO palindromeDTO){
    System.out.println("findShortestPalindrome===> ");
    String result = "";
    try {
      result = calculateService.shortestPalindrome(palindromeDTO.getInput()).get();
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Fibonacci exception-======> "+e.getCause());
      throw new RuntimeException(e);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
