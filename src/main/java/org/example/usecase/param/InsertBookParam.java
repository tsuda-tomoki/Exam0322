package org.example.usecase.param;

import org.example.domain.Book;

/**
 * 本情報を新規登録するためのデータを保持するクラス.
 *
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record InsertBookParam(String title, String author, String publisher, Integer price) {

  /**
   * 指定されたIDから本情報のオブジェクトを作成します.
   *
   * @param id 新規登録する本ID
   * @return Bookオブジェクト
   */
  public Book refill(Long id) {
    return new Book(String.valueOf(id), title, author, publisher, price);
  }
}
