package org.example.infrastructure;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

  /**
   * 本情報を新規登録します.
   *
   * @param bookEntity 登録する本エンティティ
   * @return 登録した本情報の件数
   */
  @Insert("INSERT INTO books (id, title, author, publisher, price)"
      + "VALUES(#{id}, #{title}, #{author}, #{publisher}, #{price})")
  Integer insert(BookEntity bookEntity);

  /**
   * 次の本情報IDを取得します.
   *
   * @return 次のID
   */
  @Select("SELECT nextval('BOOK_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextId();

  /**
   * 本情報を更新します.
   *
   * @param bookEntity 更新する本エンティティ
   * @return 更新した本情報の件数
   */
  @Update("UPDATE books SET title = #{title}, author = #{author},"
      + "publisher = #{publisher}, price = #{price} WHERE id = #{id}")
  Integer update(BookEntity bookEntity);

  /**
   * 本情報を削除します.
   *
   * @param id 削除する本ID
   * @return 削除する本情報の件数
   */
  @Delete("DELETE FROM books WHERE id = #{id}")
  Integer delete(Integer id);
}
