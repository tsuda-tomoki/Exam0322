package org.example.presentation.response;

import org.example.domain.Book;

/**
 * 本の情報を表すレスポンスオブジェクト.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record BookResponse(
    String id, String title, String author, String publisher, Integer price) {

  /**
   * Bookオブジェクトをレスポンスオブジェクトに変換します.
   *
   * @param book 本の情報を表す値オブジェクト
   * @return 変換されたレスポンスオブジェクト
   */
  public static BookResponse format(Book book) {
    return new BookResponse(
        book.id(),
        book.title(),
        book.author(),
        book.publisher(),
        book.price()
    );
  }
}
