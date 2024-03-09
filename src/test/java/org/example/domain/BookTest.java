package org.example.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

  @ParameterizedTest
  @CsvSource({
      " , 本のIDがnullです.",
      "a, 本のIDが数字ではありません.",
      "0, IDは1~9999999999でなくてはなりません.",
      "10000000000, IDは1~9999999999でなくてはなりません."
  })
  void 従業員IDがガード条件に違反するとき(String id, String message) throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        id, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(message);
  }
}
