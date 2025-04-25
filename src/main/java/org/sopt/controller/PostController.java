package org.sopt.controller;

import java.util.List;
import org.sopt.common.response.ApiResponse;
import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/posts")
  public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody final PostRequest postRequest) {
    postService.createPost(postRequest.getTitle());
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("게시글이 성공적으로 작성되었습니다.", null));
  }

  @GetMapping("/posts")
  public ResponseEntity<ApiResponse<List<Post>>> getPosts(
      @RequestParam(required = false) String keyword) {
    List<Post> posts = (keyword != null && !keyword.isBlank())
        ? postService.searchPostsByKeyword(keyword)
        : postService.getAllPosts();

    return ResponseEntity.ok(ApiResponse.success("게시글 조회 성공", posts));
  }


  @GetMapping("/posts/{post-id}")
  public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable("post-id") Long id) {
    Post post = postService.getPostById(id);
    return ResponseEntity.ok(ApiResponse.success("게시글 조회 성공", post));
  }

  @PatchMapping("/posts/{post-id}")
  public ResponseEntity<ApiResponse<Void>> updatePostTitle(@PathVariable("post-id") Long id,
      @RequestParam String newTitle) {
    postService.updatePostTitle(id, newTitle);
    return ResponseEntity.ok(ApiResponse.success("게시글 제목이 성공적으로 수정되었습니다.", null));
  }

  @DeleteMapping("/posts/{post-id}")
  public ResponseEntity<ApiResponse<Void>> deletePostById(@PathVariable("post-id") Long id) {
    postService.deletePostById(id);
    return ResponseEntity.ok(ApiResponse.success("게시글이 성공적으로 삭제되었습니다.", null));
  }


}