package org.example.common;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;
import org.example.infrastructure.exception.SqlFailException;
import org.example.presentation.response.ErrorResponse;
import org.example.usecase.exception.NotBookFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  /**
   * リクエストの入力が不正だったときにスローされる例外のハンドリングメソッドです.
   *
   * @param e リクエストの入力が不正だったときにスローされる例外
   * @return エラーレスポンス
   */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<String> details = new ArrayList<>();

    for (FieldError error : e.getFieldErrors()) {
      details.add(String.format("%s %s", error.getField(), error.getDefaultMessage()));
    }
    return new ErrorResponse(
        "0002",
        "request validation error is occurred.",
        details
    );
  }

  /**
   * データベースへのアクセスが失敗したときにスローされる例外のハンドリングメソッドです.
   *
   * @param e データベースへのアクセスが失敗したときにスローされる例外
   * @return エラーレスポンス
   */
  @ExceptionHandler(DataAccessException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleDatabaseAccessException(DataAccessException e) {
    return new ErrorResponse(
        "0001",
        "Failed to access the database.",
        emptyList()
    );
  }

  /**
   * 予期せぬ例外を処理するハンドリングメソッドです.
   *
   * @param e 発生した予期せぬ例外
   * @return エラーレスポンス
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception e) {
    return new ErrorResponse(
        "9999",
        String.format("An unexpected exception has occurred: %s", e.getMessage()),
        emptyList()
    );
  }
}
