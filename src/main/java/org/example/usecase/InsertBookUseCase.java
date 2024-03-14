package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.example.usecase.param.InsertBookParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本情報を新規登録するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class InsertBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 新規の本情報を登録します.
   *
   * @param insertBookParam 新規登録するオブジェクト
   * @return 新規登録された本情報
   */
  @Transactional
  public Book insert(InsertBookParam insertBookParam) {
    return bookRepository.insert(insertBookParam.refill(bookRepository.getNextId()));
  }
}
