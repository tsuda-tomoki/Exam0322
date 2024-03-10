package org.example.infrastructure;

import java.util.List;
import org.example.domain.Book;
import org.example.domain.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BookRepositoryImplTest {

  @Mock
  private BookMapper bookMapper;

  @InjectMocks
  private BookRepositoryImpl sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件検索ができる場合() {
    // setup
    List<BookEntity> bookEntityList = List.of(
        new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new BookEntity(2, "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    List<Book> expected = List.of(
        new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    when(bookMapper.findAll()).thenReturn(bookEntityList);

    // execute
    List<Book> actual = sut.findAll();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
