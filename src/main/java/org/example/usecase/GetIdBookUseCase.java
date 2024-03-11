package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

/**
 * 指定IDの本情報を取得するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class GetIdBookUseCase {

  private final BookRepository bookRepository;

  public Book findById(String id) {
    return bookRepository.findById(id).orElseThrow();
  }
}
