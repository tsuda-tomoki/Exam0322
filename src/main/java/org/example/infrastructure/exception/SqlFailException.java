package org.example.infrastructure.exception;

/**
 * Sqlの実行が失敗したときにスローされる例外.
 */
public class SqlFailException extends RuntimeException {
  public SqlFailException(String message) {
    super(message);
  }
}
