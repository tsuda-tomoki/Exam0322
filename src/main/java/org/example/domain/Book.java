package org.example.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 本の情報を表す Value Object.
 *
 * @param id 本のid. nullであってはなりません.
 * @param title 本のタイトル名.
 * @param author
 * @param publisher
 * @param price
 */
public record Book(String id, String title, String author, String publisher, Integer price) {

  /**
   * 本の Value Object を初期化します.
   *
   * @param id 本のid. nullであってはなりません.
   * @param title 本のタイトル.
   * @param author
   * @param publisher
   * @param price
   */
  public Book {
    if (isNull(id)) {
      throw new IllegalArgumentException("本のIDがnullです.");
    }

    if (!isNumeric(id)) {
      throw new IllegalArgumentException("本のIDが数字ではありません.");
    }

    if (Long.parseLong(id) <= 0L || Long.parseLong(id) > 9999999999L) {
      throw new IllegalArgumentException("IDは1~9999999999でなくてはなりません.");
    }
  }
}
