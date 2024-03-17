package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.domain.repository.BookRepository;
import org.example.usecase.exception.NotBookFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本情報を削除するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class DeleteBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 指定されたIDの本情報を削除します.
   *
   * @param id 指定されたID
   */
  @Transactional
  public void delete(String id) {
    if (bookRepository.findById(id).isEmpty()) {
      throw new NotBookFoundException(id);
    }
    bookRepository.delete(id);
  }
}
