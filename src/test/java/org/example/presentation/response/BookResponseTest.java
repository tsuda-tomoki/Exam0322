package org.example.presentation.response;

import org.example.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookResponseTest {

  @Test
  void BookオブジェクトをBookResponseオブジェクトに変換するとき() {
    // setup
    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);
    BookResponse expected = new BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);
    // execute
    BookResponse actual = BookResponse.format(book);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
