package org.example.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 本の情報を表す Value Object.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record Book(String id, String title, String author, String publisher, Integer price) {

  /**
   * 本の Value Object を初期化します.
   *
   * @param id        本のID. 1~9999999999の数字でかつ、nullであってはなりません.
   * @param title     本のタイトル. 100文字以下でかつ、nullであってはなりません.
   * @param author    本の著者. 100文字以下でかつ、nullであってはなりません.
   * @param publisher 本の出版社. 100文字以下でかつ、nullであってはなりません.
   * @param price     本の金額. 正の値でかつ、nullであってはなりません.
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

    if (isNull(title)) {
      throw new IllegalArgumentException("本タイトルがnullです.");
    }

    if (title.length() > 100) {
      throw new IllegalArgumentException("本タイトルは100文字を超えてはなりません.");
    }

    if (isNull(author)) {
      throw new IllegalArgumentException("著者名がnullです.");
    }

    if (author.length() > 100) {
      throw new IllegalArgumentException("著者名は100文字を超えてはなりません.");
    }

    if (isNull(publisher)) {
      throw new IllegalArgumentException("出版社名がnullです.");
    }

    if (publisher.length() > 100) {
      throw new IllegalArgumentException("出版社名は100文字を超えてはなりません.");
    }

    if (isNull(price)) {
      throw new IllegalArgumentException("金額がnullです.");
    }

    if (price < 0) {
      throw new IllegalArgumentException("金額がマイナスです.");
    }
  }
}
