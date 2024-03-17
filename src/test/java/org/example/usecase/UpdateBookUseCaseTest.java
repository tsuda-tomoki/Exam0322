package org.example.usecase;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.example.usecase.param.UpdateBookParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateBookUseCaseTest {

  @Mock
  BookRepository bookRepository;

  @InjectMocks
  private UpdateBookUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 更新ができる場合() {
    // setup
    Optional<Book> book = Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

    doReturn(book).when(bookRepository).findById("1");

    UpdateBookParam updateBookParam = new UpdateBookParam("1","テスト駆動開発", "Uncle Bob", "オーム社", 3080);

    // execute & assert
    assertThatCode(() -> sut.update(updateBookParam))
        .doesNotThrowAnyException();
  }
}
