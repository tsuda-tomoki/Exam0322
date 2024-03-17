package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.domain.repository.BookRepository;
import org.example.usecase.exception.NotBookFoundException;
import org.example.usecase.param.UpdateBookParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本情報を更新するビジネスロジックを実装するユースケース.
 */
@Service
@RequiredArgsConstructor
public class UpdateBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 本情報を更新します.
   *
   * @param updateBookParam 更新される本情報
   * @throws NotBookFoundException 指定されたIDの本情報が見つからないときにスローされる例外
   */
  @Transactional
  public void update(UpdateBookParam updateBookParam) {
    bookRepository.update(updateBookParam.refill(
        bookRepository.findById(updateBookParam.id()).orElseThrow(
            () -> new NotBookFoundException(updateBookParam.id()))));
  }
}
