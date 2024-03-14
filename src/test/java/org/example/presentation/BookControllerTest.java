package org.example.presentation;

import java.util.List;
import org.example.domain.Book;
import org.example.usecase.GetAllBooksUseCase;
import org.example.usecase.GetIdBookUseCase;
import org.example.usecase.InsertBookUseCase;
import org.example.usecase.param.InsertBookParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.TestUtils.readFrom;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetAllBooksUseCase getAllBooksUseCase;

  @MockBean
  private GetIdBookUseCase getIdBookUseCase;

  @MockBean
  private InsertBookUseCase insertBookUseCase;

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
    List<Book> bookList = List.of(
        new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    );

    doReturn(bookList).when(getAllBooksUseCase).findAll();

    mockMvc.perform(get("/v1/books"))
        .andExpect(status().isOk())
        .andExpect(content().json(readFrom("test-json/AllBooks.json")));
  }

  @Test
  void GETでエンドポイントにIDが指定された場合ID検索が実行される() throws Exception {
    // setup
    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    doReturn(book).when(getIdBookUseCase).findById("1");

    mockMvc.perform(get("/v1/books/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(readFrom("test-json/IdBook.json")));
  }

  @Test
  void POSTでエンドポイントにemployeesが指定された場合追加が実行される() throws Exception {
    // setup
    Book book = new Book("5", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

    InsertBookParam insertBookParam = new InsertBookParam("Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

    doReturn(book).when(insertBookUseCase).insert(insertBookParam);

    mockMvc.perform(post("/v1/books")
            .content(readFrom("test-json/postBook.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }
}
