package org.example.common;

import org.example.domain.Book;
import org.example.infrastructure.BookMapper;
import org.example.infrastructure.BookRepositoryImpl;
import org.example.usecase.GetAllBooksUseCase;
import org.example.usecase.GetIdBookUseCase;
import org.example.usecase.InsertBookUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.TestUtils.readFrom;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GlobalExceptionHandlerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookMapper bookMapper;

  @MockBean
  private GetIdBookUseCase getIdBookUseCase;

  @MockBean
  private GetAllBooksUseCase getAllBooksUseCase;

  @MockBean
  private InsertBookUseCase insertBookUseCase;

  @MockBean
  private BookRepositoryImpl bookRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void SqlFailExceptionがスローされた場合() throws Exception {
    // setup
    Book book = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

    doReturn(book).when(bookRepository).insert(book);

    // assert & execute
    mockMvc.perform(post("/v1/books")
            .content(readFrom("test-json/postBookBad.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
