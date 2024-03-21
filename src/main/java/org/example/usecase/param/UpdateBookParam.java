package org.example.usecase.param;

import static java.util.Objects.nonNull;

import org.example.domain.Book;

/**
 * 更新したい本情報を保持するクラス.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record UpdateBookParam(
    String id, String title, String author, String publisher, Integer price) {

  /**
   * 入力された本情報を元に更新します. 更新がない場合は、元の情報を返します.
   *
   * @param book 本情報
   * @return 更新された本情報
   */
  public Book refill(Book book) {
    return new Book(
        book.id(),
        nonNull(title) ? title : book.title(),
        nonNull(author) ? author : book.author(),
        nonNull(publisher) ? publisher : book.publisher(),
        nonNull(price) ? price : book.price());
  }
}
