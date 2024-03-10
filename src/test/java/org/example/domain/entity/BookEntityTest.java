package org.example.domain.entity;

import org.example.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookEntityTest {

  @Test
  void EntityオブジェクトをBookオブジェクトに変換できるとき() {
    // setup
    BookEntity bookEntity = new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080);
    Book expected = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    // execute
    Book actual = bookEntity.convert();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
