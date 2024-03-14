package org.example.usecase.param;

import org.example.domain.Book;

/**
 * 本情報を新規登録するためのデータを保持する.
 *
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     金額
 */
public record InsertBookParam(String title, String author, String publisher, Integer price) {

  /**
   * 指定されたIDから本情報のオブジェクトを作成.
   *
   * @param id 新規登録する本ID
   * @return Bookオブジェクト
   */
  public Book refill(Long id) {
    return new Book(String.valueOf(id), title, author, publisher, price);
  }
}
