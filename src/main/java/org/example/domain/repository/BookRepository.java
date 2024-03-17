package org.example.domain.repository;

import java.util.List;
import java.util.Optional;
import org.example.domain.Book;

/**
 * booksテーブルにアクセスするためのリポジトリ.
 */
public interface BookRepository {

  /**
   * 全ての本情報を取得します.
   *
   * @return 本情報のリスト
   */
  List<Book> findAll();

  /**
   * ID検索のした本情報を取得します.
   *
   * @return 指定されたIDの本情報
   */
  Optional<Book> findById(String id);

  /**
   * 新規の本情報を追加します.
   *
   * @param book 追加する本情報
   * @return 追加される本情報
   */
  Book insert(Book book);

  /**
   * 新規で追加予定のIDを取得します.
   *
   * @return 追加予定のID
   */
  Long getNextId();

  /**
   * 本情報を更新します.
   *
   * @param book 更新する本情報
   */
  void update(Book book);

  /**
   * 本情報を削除します.
   *
   * @param id 削除する本情報のID
   */
  void delete(String id);
}
