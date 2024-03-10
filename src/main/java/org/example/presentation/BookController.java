package org.example.presentation;

import lombok.RequiredArgsConstructor;
import org.example.presentation.response.AllBooksResponse;
import org.example.usecase.GetAllBooksUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員を管理するコントローラークラス.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

  private final GetAllBooksUseCase getAllBooksUseCase;

  /**
   * ルートURLへのリクエストを処理します.
   */
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public void accessToRoute() {
  }

  /**
   * すべての本情報を返します.
   *
   * @return すべての本情報のリストを含むAllBooksResponse
   */
  @GetMapping("/v1/books")
  @ResponseStatus(HttpStatus.OK)
  public AllBooksResponse findAll() {
    return AllBooksResponse.format(getAllBooksUseCase.findAll());
  }
}
