package org.example.presentation.response;

import java.util.List;

/**
 * エラー応答の情報を保持するクラス.
 *
 * @param code    エラーコード
 * @param message エラーメッセージ
 * @param details エラーの詳細情報
 */
public record ErrorResponse(String code, String message, List<String> details) {

}
