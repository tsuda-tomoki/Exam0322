package org.example.presentation;

import java.util.List;
import org.example.domain.Book;
import org.example.presentation.response.AllBooksResponse;
import org.example.presentation.response.BookResponse;
import org.example.usecase.GetAllBooksUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.TestUtils.readFrom;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetAllBooksUseCase getAllBooksUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void ルートURLにアクセスしたとき() throws Exception {
    // assert
    mockMvc.perform(get("/"))
        .andExpect(status().isOk());
  }

  @Test
  void GETでエンドポイントにbooksが指定された場合全件検索が実行される() throws Exception {
    // setup
    List<Book> expected = List.of(
        new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    doReturn(expected).when(getAllBooksUseCase).findAll();

    mockMvc.perform(get("/v1/books"))
        .andExpect(status().isOk())
        .andExpect(content().json(readFrom("test-json/AllBooks.json")));
  }
}
