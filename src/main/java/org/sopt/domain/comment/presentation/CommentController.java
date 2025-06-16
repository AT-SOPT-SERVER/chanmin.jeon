package org.sopt.domain.comment.presentation;

import java.util.List;

import org.sopt.domain.comment.application.dto.CommentCreateCommand;
import org.sopt.domain.comment.application.dto.CommentDeleteCommand;
import org.sopt.domain.comment.application.dto.CommentUpdateCommand;
import org.sopt.domain.comment.application.usecase.CommentManageUsecase;
import org.sopt.domain.comment.application.usecase.CommentQueryUsecase;
import org.sopt.domain.comment.presentation.dto.CommentCreateRequest;
import org.sopt.domain.comment.presentation.dto.CommentResponse;
import org.sopt.domain.comment.presentation.dto.CommentUpdateRequest;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

	private final CommentManageUsecase commentManageUsecase;
	private final CommentQueryUsecase commentQueryUsecase;

	@PostMapping("/{postId}/comments")
	@Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
	public ResponseEntity<ApiResponse<Void>> createComment(
		@PathVariable Long postId,
		@RequestBody @Valid CommentCreateRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		CommentCreateCommand command = CommentCreateCommand.from(postId, userDetails.getUserId(), request);
		commentManageUsecase.createComment(command);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResponse.success(HttpStatus.CREATED.value(), "댓글 작성에 성공했습니다.", null));
	}

	@GetMapping("/{postId}/comments")
	@Operation(summary = "댓글 조회", description = "댓글을 조회합니다.")
	public ResponseEntity<ApiResponse<List<CommentResponse>>> getComments(@PathVariable Long postId) {
		List<CommentResponse> responses = commentQueryUsecase.getCommentsByPostId(postId);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "댓글 목록 조회에 성공했습니다.", responses));

	}

	@PatchMapping("comments/{commentId}")
	@Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
	public ResponseEntity<ApiResponse<Void>> updateComment(
		@PathVariable Long commentId,
		@RequestBody @Valid CommentUpdateRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		CommentUpdateCommand command = CommentUpdateCommand.from(commentId, userDetails.getUserId(), request);
		commentManageUsecase.updateComment(command);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "댓글 수정에 성공했습니다.", null));
	}

	@DeleteMapping("comments/{commentId}")
	@Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
	public ResponseEntity<ApiResponse<Void>> deleteComment(
		@PathVariable Long commentId,
		@AuthenticationPrincipal CustomUserDetails userDetails
	) {
		CommentDeleteCommand command = CommentDeleteCommand.from(commentId, userDetails.getUserId());
		commentManageUsecase.deleteComment(command);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "댓굴 삭제에 성공했습니다.", null));
	}
}
