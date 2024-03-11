package org.example.domain.repository;

import java.util.List;
import java.util.Optional;
import org.example.domain.Book;

/**
 * books テーブルにアクセスするためのリポジトリ.
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
}
