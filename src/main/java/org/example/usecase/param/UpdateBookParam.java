package org.example.usecase.param;

import static java.util.Objects.nonNull;

import org.example.domain.Book;

/**
 * 更新したい本情報を保持するオブジェクト.
 *
 * @param id        本ID
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     金額
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
