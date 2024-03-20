package org.example.common;

import org.example.domain.Book;
import org.example.infrastructure.BookMapper;
import org.example.infrastructure.BookRepositoryImpl;
import org.example.infrastructure.exception.SqlFailException;
import org.example.usecase.DeleteBookUseCase;
import org.example.usecase.GetAllBooksUseCase;
import org.example.usecase.GetIdBookUseCase;
import org.example.usecase.InsertBookUseCase;
import org.example.usecase.UpdateBookUseCase;
import org.example.usecase.exception.NotBookFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.TestUtils.readFrom;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
  private UpdateBookUseCase updateBookUseCase;

  @MockBean
  private DeleteBookUseCase deleteBookUseCase;

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

    when(bookRepository.insert(book)).thenThrow(new SqlFailException("") {});
    // assert & execute
    mockMvc.perform(post("/v1/books")
            .content(readFrom("test-json/postBookBad.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());  }

  @Test
  void DataAccessExceptionがスローされた場合() throws Exception {
    // setup
    when(getAllBooksUseCase.findAll()).thenThrow(new DataAccessException("") {});

    // assert & execute
    mockMvc.perform(get("/v1/books")
            .content(readFrom("test-json/dataAccessException.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());
  }

  @Test
  void NotBookFoundExceptionがスローされた場合() throws Exception {
    // setup
    when(getIdBookUseCase.findById("1")).thenThrow(new NotBookFoundException("") {});

    // assert & execute
    mockMvc.perform(get("/v1/books/1")
            .content(readFrom("test-json/notFoundBookException.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void 予期せぬ例外がスローされた場合() throws Exception {
    // setup
    when(getAllBooksUseCase.findAll()).thenThrow(new RuntimeException("") {});

    // assert & execute
    mockMvc.perform(get("/v1/books")
            .content(readFrom("test-json/exception.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());
  }
}
