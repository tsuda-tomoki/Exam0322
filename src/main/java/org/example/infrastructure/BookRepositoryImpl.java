package org.example.infrastructure;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.entity.BookEntity;
import org.example.domain.repository.BookRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository の実装クラス. booksデータベースにアクセスします.
 */
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
    return book;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextId() {
    return bookMapper.getNextId();
  }
}
