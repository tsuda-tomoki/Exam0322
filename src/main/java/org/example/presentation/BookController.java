package org.example.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員を管理するコントローラークラス.
 */
@RestController
public class BookController {

  /**
   * ルートURLへのリクエストを処理します.
   */
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public void accessToRoute() {
  }
}
