package org.example.usecase.exception;

import lombok.Getter;

/**
 * 指定されたIDの本情報が存在しないときにスローされる例外.
 */
@Getter
public class NotBookFoundException extends RuntimeException {

  private final String id;

  public NotBookFoundException(String id) {
    super(id);
    this.id = id;
  }
}
