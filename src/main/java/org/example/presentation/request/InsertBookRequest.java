package org.example.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 新規の本情報をに送信するリクエストを表すクラス.
 *
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record InsertBookRequest(
    @NotBlank
    @Length(max = 100)
    @JsonProperty("title")
    String title,

    @NotBlank
    @Length(max = 100)
    @JsonProperty("author")
    String author,

    @NotBlank
    @Length(max = 100)
    @JsonProperty("publisher")
    String publisher,

    @NotNull
    @Min(1)
    @JsonProperty("price")
    Integer price
) {

}
