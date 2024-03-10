package org.example.usecase;

import java.util.List;
import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GetAllBooksUseCaseTest {

  @Mock
  BookRepository bookRepository;

  @InjectMocks
  private GetAllBooksUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件検索ができる場合() {
    // setup
    List<Book> expected = List.of(
        new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    when(bookRepository.findAll()).thenReturn(expected);

    // execute
    List<Book> actual = sut.findAll();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
