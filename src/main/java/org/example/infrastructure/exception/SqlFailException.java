package org.example.infrastructure.exception;

/**
 * Sqlの実行が失敗したときにスローされる例外クラス.
 */
public class SqlFailException extends RuntimeException {

  /**
   * SqlFailExceptionを初期化します.
   *
   * @param message エラーメッセージ
   */
  public SqlFailException(String message) {
    super(message);
  }
}
