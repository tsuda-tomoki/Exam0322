package org.example.infrastructure;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.entity.BookEntity;
import org.example.domain.repository.BookRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository の実装クラス.
 * booksデータベースにアクセスします.
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
}
