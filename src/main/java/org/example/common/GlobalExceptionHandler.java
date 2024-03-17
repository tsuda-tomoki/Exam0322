package org.example.common;

import static java.util.Collections.emptyList;

import org.example.infrastructure.exception.SqlFailException;
import org.example.presentation.response.ErrorResponse;
import org.example.usecase.exception.NotBookFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 例外ハンドリングを行うクラス.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * SQL実行時に問題があった場合の例外ハンドリングメソッドです.
   *
   * @param e SQL実行時に問題があった場合の例外
   * @return エラーレスポンス
   */
  @ExceptionHandler(SqlFailException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleSqlExecution(SqlFailException e) {
    return new ErrorResponse(
        "0004",
        e.getMessage(),
        emptyList()
    );
  }

  /**
   * ID検索時に失敗した場合にスローされる例外のハンドリングメソッドです.
   *
   * @param e ID検索時にスローされる例外
   * @return エラーレスポンス
   */
  @ExceptionHandler(NotBookFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleNotBookFoundException(NotBookFoundException e) {
    return new ErrorResponse(
        "0003",
        String.format("specified book [id = %s] is not found.", e.getId()),
        emptyList()
    );
  }
}
