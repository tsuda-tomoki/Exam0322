package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

  @Test
  void 本IDがnullのとき() throws Exception {
    // assert
    assertThatThrownBy(() -> new Book(
        null, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("本のidがnullです.");
  }
}
