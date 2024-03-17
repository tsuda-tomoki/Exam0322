package org.example.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

/**
 * 本をを更新用リクエストを表すオブジェクト.
 *
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     金額
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
