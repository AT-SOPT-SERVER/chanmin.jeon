package org.sopt.domain.post.controller;

import java.util.List;
import org.sopt.common.response.ApiResponse;
import org.sopt.domain.post.dto.PostDetailResponse;
import org.sopt.domain.post.dto.PostSummaryResponse;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.dto.PostRequest;
import org.sopt.domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Void>> createPost(
      @RequestHeader("userId") Long userId,
      @RequestBody final PostRequest request) {
    postService.createPost(userId, request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success(201,"게시글이 성공적으로 작성되었습니다.", null));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<PostSummaryResponse>>> getPosts(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String author,
      @RequestParam(required = false) String tag
  ) {
    List<PostSummaryResponse> posts = postService.getPosts(title, author, tag);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(200, "게시글 조회 성공", posts));
  }


  @GetMapping("/{post-id}")
  public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(@PathVariable("post-id") Long id) {
    PostDetailResponse response = postService.getPost(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(200, "게시글 조회 성공", response));
  }

  @PatchMapping("/{post-id}")
  public ResponseEntity<ApiResponse<Void>> updatePost(
      @RequestHeader("userId") Long userId,
      @PathVariable("post-id") Long id,
      @RequestBody PostRequest request) {
    postService.updatePostTitle(id, userId, request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(200, "게시글을 성공적으로 수정되었습니다.", null));
  }

  @DeleteMapping("/{post-id}")
  public ResponseEntity<ApiResponse<Void>> deletePost(
      @RequestHeader("userId") Long userId,
      @PathVariable("post-id") Long id
  ) {
    postService.deletePostById(id, userId);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(200, "게시글이 성공적으로 삭제되었습니다.", null));
  }


}