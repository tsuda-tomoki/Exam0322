package org.example.presentation.response;

import java.util.ArrayList;
import java.util.List;
import org.example.domain.Book;

/**
 * すべての本の情報も持つレスポンスクラス.
 *
 * @param books 本の情報を持つレスポンスオブジェクトのリスト
 */
public record AllBooksResponse(List<BookResponse> books) {

  /**
   * 本の情報を表すオブジェクトのリストをAllBooksResponseオブジェクトに変換します.
   *
   * @param bookList 本情報のリスト.
   * @return 変換されたAllBookResponseオブジェクト.
   */
  public static AllBooksResponse format(List<Book> bookList) {
    List<BookResponse> bookResponses = new ArrayList<>();

    for (Book book : bookList) {
      bookResponses.add(BookResponse.format(book));
    }
    return new AllBooksResponse(bookResponses);
  }
}
