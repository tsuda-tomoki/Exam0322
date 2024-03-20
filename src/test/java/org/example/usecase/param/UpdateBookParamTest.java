package org.example.usecase.param;

import java.util.stream.Stream;
import org.example.domain.Book;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookParamTest {

  @ParameterizedTest
  @MethodSource("insertData")
  void 入力された本情報を更新オブジェクトに変換するとき(UpdateBookParam sut, Book expected) {
      Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

      Book actual = sut.refill(book);

      assertEquals(expected, actual);
    }

    private static Stream<Arguments> insertData() {
      return Stream.of(
          Arguments.of(
              new UpdateBookParam("1", "アジャイルサムライ", "Jonathan Rasmusson", null, 2860),
              new Book("1", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
          ),
          Arguments.of(
              new UpdateBookParam("1", null, null, "ドワンゴ", null),
              new Book("1", "テスト駆動開発", "Kent Beck", "ドワンゴ", 3080)
          )
      );
    }
}
