package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.example.usecase.exception.NotBookFoundException;
import org.springframework.stereotype.Service;

/**
 * 指定IDの本情報を取得するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class GetIdBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 指定されたIDの本情報を取得します.
   *
   * @param id 指定されたID
   * @return 指定されたIDの本情報が存在しないときにスローされる例外
   */
  public Book findById(String id) {
    return bookRepository.findById(id).orElseThrow(
        () -> new NotBookFoundException(id)
    );
  }
}
