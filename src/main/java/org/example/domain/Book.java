package org.example.domain;

/**
 * 本の情報を表す Value Object.
 *
 * @param id
 * @param title
 * @param author
 * @param publisher
 * @param price
 */
public record Book(String id, String title, String author, String publisher, Integer price) {

}
