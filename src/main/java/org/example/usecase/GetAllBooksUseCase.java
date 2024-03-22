package org.example.usecase;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * すべての本情報を取得するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class GetAllBooksUseCase {

  private final BookRepository bookRepository;

  /**
   * すべての本情報を取得します.
   *
   * @return 本情報のリスト
   */
  public List<Book> findAll() {
    return bookRepository.findAll();
  }
}
