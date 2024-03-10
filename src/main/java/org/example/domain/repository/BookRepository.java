package org.example.domain.repository;

import java.util.List;
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
}
