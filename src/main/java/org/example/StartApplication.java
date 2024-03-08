package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーションを起動するクラス.
 */
@SpringBootApplication
public class StartApplication {

  /**
   * 起動するための main メソッド.
   *
   * @param args 使用しない.
   */
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
