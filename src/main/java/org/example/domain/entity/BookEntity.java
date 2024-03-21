package org.example.domain.entity;

import org.example.domain.Book;

/**
 * 本の情報を表すエンティティクラス.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record BookEntity(Integer id, String title, String author, String publisher, Integer price) {

  /**
   * 本の情報を表す Value Object に変換します.
   *
   * @return 変換されたオブジェクト
   */
  public Book convert() {
    return new Book(String.valueOf(id), title, author, publisher, price);
  }
}
