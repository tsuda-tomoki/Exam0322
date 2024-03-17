package org.example.infrastructure;

import java.util.List;
import java.util.Optional;
import org.example.domain.Book;
import org.example.domain.entity.BookEntity;
import org.example.infrastructure.exception.SqlFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

  @Test
  void ID検索ができる場合() {
    // setup
    BookEntity bookEntity = new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    Optional<Book> expected = Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

    when(bookMapper.findById(bookEntity.id())).thenReturn(bookEntity);

    // execute
    Optional<Book> actual = sut.findById("1");

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void 追加ができる場合() {
    // setup
    BookEntity bookEntity = new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    when(bookMapper.insert(bookEntity)).thenReturn(1);

    // execute & assert
    assertThatCode(() -> sut.insert(book))
        .doesNotThrowAnyException();
  }

  @Test
  void 追加が失敗したときに例外がスローされる場合() {
    // setup
    BookEntity bookEntity = new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    when(bookMapper.insert(bookEntity)).thenReturn(2);

    // assert & execute
    assertThatThrownBy(() -> sut.insert(book))
        .isInstanceOf(SqlFailException.class)
        .hasMessageContaining("SQLの実行時にエラーが発生しました。");
  }

  @Test
  void 追加予定のIDが取得できる場合() {
    // setup
    when(bookMapper.getNextId()).thenReturn(5L);

    // execute & assert
    assertThatCode(() -> sut.getNextId())
        .doesNotThrowAnyException();
  }

  @Test
  void 更新ができる場合() {
    // setup
    BookEntity bookEntity = new BookEntity(1, "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

    Book book = new Book("1", "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

    when(bookMapper.update(bookEntity)).thenReturn(1);

    // execute & assert
    assertThatCode(() -> sut.update(book)).doesNotThrowAnyException();
  }

  @Test
  void 削除ができる場合() {
    // setup
    when(bookMapper.delete(1)).thenReturn(1);

    // execute & assert
    assertThatCode(() -> sut.delete("1")).doesNotThrowAnyException();
  }
}
