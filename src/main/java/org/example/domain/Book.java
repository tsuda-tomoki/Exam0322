package org.example.domain;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 本の情報を表す Value Object.
 *
 * @param id 本のid. nullであってはなりません.
 * @param title
 * @param author
 * @param publisher
 * @param price
 */
public record Book(String id, String title, String author, String publisher, Integer price) {
  public Book {
    checkIdIsNull();
  }

  private void checkIdIsNull() {
    if (!isNumeric(id)) {
      throw new IllegalArgumentException("本のidがnullです.");
    }
  }
}
