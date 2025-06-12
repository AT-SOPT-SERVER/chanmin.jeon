package org.sopt.domain.comment.exception;

import org.sopt.global.exception.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum CommentErrorCode implements BaseErrorCode {

	// 400
	COMMENT_CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, 40030, "댓글 내용은 필수입니다."),
	COMMENT_CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, 40031, "댓글은 최대 300자까지 작성할 수 있습니다."),

	// 403
	FORBIDDEN_COMMENT_UPDATE(HttpStatus.FORBIDDEN, 40330, "댓글 수정 권한이 없습니다."),
	FORBIDDEN_COMMENT_DELETE(HttpStatus.FORBIDDEN, 40331, "댓글 삭제 권한이 없습니다."),

	// 404
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, 40430, "댓글을 찾을 수 없습니다."),
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40431, "게시글을 찾을 수 없습니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40432, "작성자를 찾을 수 없습니다.");

	private final HttpStatus status;
	private final int code;
	private final String message;

	CommentErrorCode(HttpStatus status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

