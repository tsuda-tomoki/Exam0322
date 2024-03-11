package org.example.usecase;

import java.util.Optional;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.example.usecase.exception.NotBookFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class GetIdBookUseCaseTest {

  @Mock
  BookRepository bookRepository;

  @InjectMocks
  private GetIdBookUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void ID検索ができる場合() {
    // setup
    Book expected = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    when(bookRepository.findById("1")).thenReturn(
        Optional.of(new Book(
            "1",
            "テスト駆動開発", "Kent Beck", "オーム社", 3080)));

    // execute
    Book actual = sut.findById("1");

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void 指定されたIDが存在しないときに例外がスローされるとき() {
    // setup
    when(bookRepository.findById("999")).thenReturn(Optional.empty());

    // assert
    assertThatThrownBy(() -> sut.findById("999")).isInstanceOf(NotBookFoundException.class);
  }
}
