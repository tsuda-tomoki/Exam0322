package org.example.presentation;

import lombok.RequiredArgsConstructor;
import org.example.presentation.response.AllBooksResponse;
import org.example.presentation.response.BookResponse;
import org.example.usecase.GetAllBooksUseCase;
import org.example.usecase.GetIdBookUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員を管理するコントローラークラス.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

  private final GetAllBooksUseCase getAllBooksUseCase;
  private final GetIdBookUseCase getIdBookUseCase;

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

  /**
   * 指定したIDの本情報を検索して返します.
   *
   * @param id 検索する本情報のID
   * @return 指定されたIDの本情報を含むBookResponse
   */
  @GetMapping("/v1/books/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse findById(@PathVariable String id) {
    return BookResponse.format(getIdBookUseCase.findById(id));
  }
}
