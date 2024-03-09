package org.example.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {
  private static final String OVER_STRING =
      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
          + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

  @ParameterizedTest
  @CsvSource({
      " , 本のIDがnullです.",
      "a, 本のIDが数字ではありません.",
      "0, IDは1~9999999999でなくてはなりません.",
      "10000000000, IDは1~9999999999でなくてはなりません."
  })
  void 本IDがガード条件に違反するとき(String id, String message) throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        id, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(message);
  }

  @Test
  void 本タイトルがnullのとき() throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        "1", null, "Kent Beck", "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("本タイトルがnullです.");
  }

  @Test
  void 本タイトルの文字数が100文字を超えたとき() throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        "1", OVER_STRING, "Kent Beck", "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("本タイトルは100文字を超えてはなりません.");
  }

  @Test
  void 著者名がnullのとき() throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        "1", "テスト駆動開発", null, "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("著者名がnullです.");
  }

  @Test
  void 著者名の文字数が100文字を超えたとき() throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        "1", "テスト駆動開発", OVER_STRING, "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("著者名は100文字を超えてはなりません.");
  }
}
