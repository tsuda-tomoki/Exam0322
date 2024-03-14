package org.example.usecase;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doReturn;

import org.example.domain.Book;
import org.example.domain.repository.BookRepository;
import org.example.usecase.param.InsertBookParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InsertBookUseCaseTest {

  @Mock
  BookRepository bookRepository;

  @InjectMocks
  private InsertBookUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 追加ができる場合() {
    // setup
    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    doReturn(book).when(bookRepository).insert(book);
    doReturn(5L).when(bookRepository).getNextId();

    InsertBookParam insertBookParam = new InsertBookParam( "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    // execute & assert
    assertThatCode(() -> sut.insert(insertBookParam))
        .doesNotThrowAnyException();
  }
}
