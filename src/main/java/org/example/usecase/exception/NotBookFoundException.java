package org.example.usecase.exception;

import lombok.Getter;

/**
 * 指定されたIDの本情報が存在しないときにスローされる例外クラス.
 */
@Getter
public class NotBookFoundException extends RuntimeException {

  private final String id;

  /**
   * NotBookFoundExceptionを初期化します.
   *
   * @param id 検索に存在しなかった本ID
   */
  public NotBookFoundException(String id) {
    super(id);
    this.id = id;
  }
}
