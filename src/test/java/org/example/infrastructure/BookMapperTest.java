package org.example.infrastructure;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.sql.DriverManager;
import java.util.List;
import org.example.domain.entity.BookEntity;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DBRider
@DBUnit
class BookMapperTest {
  private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
  private static final String DB_USER = "sa";
  private static final String DB_PASSWORD = "sa";

  private static final ConnectionHolder connectionHolder =
      () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

  @Autowired
  BookMapper sut;

  @BeforeAll
  static void setUpAll() {
    Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load().migrate();
  }

  @Test
  @DataSet(value = "test-yml/two-books.yml")
  @ExpectedDataSet(value = "test-yml/two-books.yml")
  void 全件検索ができる場合() throws Exception {
    // setup
    List<BookEntity> expected = List.of(
        new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new BookEntity(2, "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    // execute
    List<BookEntity> actual = sut.findAll();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
