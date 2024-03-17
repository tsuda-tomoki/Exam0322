package org.example.usecase;

import java.util.Optional;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doReturn;

class DeleteBookUseCaseTest {
  @Mock
  BookRepository bookRepository;

  @InjectMocks
  private DeleteBookUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 削除ができる場合() {
    Optional<Book> book = Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

    doReturn(book).when(bookRepository).findById("1");

    // execute & assert
    assertThatCode(() -> sut.delete("1"))
        .doesNotThrowAnyException();
  }
}
