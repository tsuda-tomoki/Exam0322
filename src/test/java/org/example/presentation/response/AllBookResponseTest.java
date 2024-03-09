package org.example.presentation.response;

import java.util.List;
import org.example.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AllBookResponseTest {

  @Test
  void BookオブジェクトのリストをAllBookResponseオブジェクトに変換するとき() {
    // setup
    List<Book> bookList = List.of(
        new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
        );

    List<BookResponse> bookResponseList = List.of(
        new BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new BookResponse("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    AllBookResponse expected = new AllBookResponse(bookResponseList);

    // execute
    AllBookResponse actual = AllBookResponse.format(bookList);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
