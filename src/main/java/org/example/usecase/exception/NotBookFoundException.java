package org.example.usecase.exception;

/**
 * 指定されたIDの本情報が存在しないときにスローされる例外.
 */
public class NotBookFoundException extends RuntimeException {

  public NotBookFoundException(String id) {
    super(id);
  }
}
