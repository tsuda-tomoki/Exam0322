package org.example.presentation;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.presentation.request.InsertBookRequest;
import org.example.presentation.request.UpdateBookRequest;
import org.example.presentation.response.AllBooksResponse;
import org.example.presentation.response.BookResponse;
import org.example.usecase.DeleteBookUseCase;
import org.example.usecase.GetAllBooksUseCase;
import org.example.usecase.GetIdBookUseCase;
import org.example.usecase.InsertBookUseCase;
import org.example.usecase.UpdateBookUseCase;
import org.example.usecase.param.InsertBookParam;
import org.example.usecase.param.UpdateBookParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 本情報を管理するコントローラークラス.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

  private final GetAllBooksUseCase getAllBooksUseCase;
  private final GetIdBookUseCase getIdBookUseCase;
  private final InsertBookUseCase insertBookUseCase;
  private final UpdateBookUseCase updateBookUseCase;
  private final DeleteBookUseCase deleteBookUseCase;

  /**
   * ルートURLへのリクエストを処理します.
   */
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> access() {
    return ResponseEntity.ok("Success! You've accessed the root URL of /v1/employees.");
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

  /**
   * 新規の本情報を登録します.
   *
   * @param insertBookRequest 新規の本情報を含むリクエスト
   * @return 新規の本情報のURIを含むレスポンス
   */
  @PostMapping("/v1/books")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Book> insert(
      @RequestBody @Validated InsertBookRequest insertBookRequest) {

    Book book = insertBookUseCase.insert(
        new InsertBookParam(
            insertBookRequest.title(),
            insertBookRequest.author(),
            insertBookRequest.publisher(),
            insertBookRequest.price()
        ));

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .pathSegment(book.id())
            .build().encode().toUri();

    return ResponseEntity.created(location).build();
  }

  /**
   * 本情報の更新をします.
   *
   * @param id                    更新する本情報のID
   * @param updateEmployeeRequest 更新する本情報のリクエスト
   */
  @PatchMapping("/v1/books/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable String id,
      @RequestBody @Validated UpdateBookRequest updateEmployeeRequest) {

    updateBookUseCase.update(new UpdateBookParam(
        id,
        updateEmployeeRequest.title(),
        updateEmployeeRequest.author(),
        updateEmployeeRequest.publisher(),
        updateEmployeeRequest.price()));
  }

  /**
   * ID指定で本情報を削除します.
   *
   * @param id 削除する本のID
   */
  @DeleteMapping("/v1/books/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    deleteBookUseCase.delete(id);
  }
}
