package org.example.infrastructure;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Book;
import org.example.domain.entity.BookEntity;
import org.example.domain.repository.BookRepository;
import org.example.infrastructure.exception.SqlFailException;
import org.springframework.stereotype.Repository;

/**
 * BookRepository の実装クラス. booksデータベースにアクセスします.
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

  private final BookMapper bookMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Book> findAll() {
    return bookMapper.findAll().stream().map(BookEntity::convert).toList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Book> findById(String id) {
    if (isNull(bookMapper.findById(Integer.parseInt(id)))) {
      return Optional.empty();
    }
    return Optional.of(bookMapper.findById(Integer.parseInt(id)).convert());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Book insert(Book book) {
    Integer createNum = bookMapper.insert(
        new BookEntity(
            Integer.parseInt(book.id()),
            book.title(),
            book.author(),
            book.publisher(),
            book.price()));

    if (failSqlcheck(createNum)) {
      handleSqlFailException();
    }

    return book;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextId() {
    return bookMapper.getNextId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(Book book) {
    Integer updateNum = bookMapper.update(
        new BookEntity(
            Integer.parseInt(book.id()),
            book.title(),
            book.author(),
            book.publisher(),
            book.price()));

    if (failSqlcheck(updateNum)) {
      handleSqlFailException();
    }
  }

  private boolean failSqlcheck(Integer num) {
    return num != 1;
  }

  private void handleSqlFailException() {
    log.error("SQLの実行時にエラーが発生しました。");
    throw new SqlFailException("SQLの実行時にエラーが発生しました。");
  }
}
