package org.sopt.domain.post.presentation;

import java.util.List;

import org.sopt.domain.post.application.dto.PostCreateCommand;
import org.sopt.domain.post.application.dto.PostUpdateCommand;
import org.sopt.domain.post.application.usecase.PostCommandUsecase;
import org.sopt.domain.post.application.usecase.PostQueryUsecase;
import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.presentation.dto.PostDetailResponse;
import org.sopt.domain.post.presentation.dto.PostInfoResponse;
import org.sopt.domain.post.presentation.dto.PostRequest;
import org.sopt.domain.post.presentation.dto.PostUpdateRequest;
import org.sopt.global.exception.ApiResponse;
import org.sopt.global.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostCommandUsecase postCommandUsecase;
	private final PostQueryUsecase postQueryUsecase;

	@PostMapping
	@Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
	public ResponseEntity<ApiResponse<Void>> createPost(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestBody @Valid PostRequest request
	) {
		postCommandUsecase.createPost(PostCreateCommand.from(request, userDetails.getUserId()));
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResponse.success(HttpStatus.CREATED.value(), "게시글 생성에 성공했습니다.", null));
	}

	@PutMapping("/{postId}")
	@Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
	public ResponseEntity<ApiResponse<Void>> updatePost(
		@PathVariable Long postId,
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestBody @Valid PostUpdateRequest request
	) {
		postCommandUsecase.updatePost(PostUpdateCommand.from(postId, userDetails.getUserId(), request));
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "게시글 수정에 성공했습니다.", null));
	}

	@GetMapping
	@Operation(summary = "전체 게시글 조회", description = "전체 게시글을 최신순으로 조회합니다.")
	public ResponseEntity<ApiResponse<List<PostInfoResponse>>> getAllPosts() {
		List<PostInfoResponse> responses = postQueryUsecase.getAllPosts();
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "게시글 목록 조회에 성공했습니다.", responses));
	}

	@GetMapping("/search")
	@Operation(summary = "게시글 조건 검색", description = "게시글을 제목, 작성자, 태그로 검색하여 조회합니다.")
	public ResponseEntity<ApiResponse<List<PostDetailResponse>>> searchPosts(
		@RequestParam(required = false) String title,
		@RequestParam(required = false) String author,
		@RequestParam(required = false) String tag
	) {
		List<PostDetailResponse> responses = postQueryUsecase.getPostsByCondition(
			title, author, tag != null ? Tag.from(tag) : null);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "게시글 조건 검색에 성공했습니다.", responses));
	}

	@GetMapping("/{postId}")
	@Operation(summary = "게시글 상세 조회", description = "게시글 id로 게시글을 상세하게 조회합니다.")
	public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(
		@PathVariable Long postId
	) {
		PostDetailResponse response = postQueryUsecase.getPostById(postId);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "게시글 상세 조회에 성공했습니다.", response));
	}
}
