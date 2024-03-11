package org.example.usecase;

import java.util.Optional;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
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
}
