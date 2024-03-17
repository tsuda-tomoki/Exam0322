package org.example.usecase.param;

import org.example.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class InsertBookParamTest {

  @Test
  void 追加用のオブジェクトに変換するとき() {
    // setup
    Book expected =  new Book("5", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    InsertBookParam sut = new InsertBookParam("テスト駆動開発", "Kent Beck", "オーム社", 3080);

    // execute
    Book actual = sut.refill(5L);

    // assert
    assertThat(expected).isEqualTo(actual);
  }
}
