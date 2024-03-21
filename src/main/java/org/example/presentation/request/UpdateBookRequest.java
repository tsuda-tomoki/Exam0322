package org.example.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

/**
 * 本をを更新用リクエストを表すクラス.
 *
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の金額
 */
public record UpdateBookRequest(
    @Length(max = 100)
    @JsonProperty("title")
    String title,

    @Length(max = 100)
    @JsonProperty("author")
    String author,

    @Length(max = 100)
    @JsonProperty("publisher")
    String publisher,

    @Min(1)
    @JsonProperty("price")
    Integer price
) {

}
