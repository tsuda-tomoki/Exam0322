package org.example.infrastructure;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.domain.entity.BookEntity;

/**
 * 本情報を取得するマッパーインターフェイス.
 */
@Mapper
public interface BookMapper {

  /**
   * 全ての本情報を取得します.
   *
   * @return 本情報のリスト
   */
  @Select("SELECT id, title, author, publisher, price FROM books")
  List<BookEntity> findAll();

  /**
   * 指定されたIDの本情報を取得します.
   *
   * @return 指定された本情報
   */
  @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
  BookEntity findById(Integer id);
}
