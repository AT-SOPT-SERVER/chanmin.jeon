package org.sopt.controller;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/post")
  public ResponseEntity<?> createPost(@RequestBody final PostRequest postRequest) {
    try {
      postService.createPost(postRequest.getTitle());
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("✅ 게시글이 성공적으로 작성되었습니다.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body("❌ 게시글 작성 실패: " + e.getMessage());
    }
  }

  @GetMapping("/posts")
  public ResponseEntity<?> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/post/{id}")
  public ResponseEntity<?> getPostById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("❌ 게시글 조회 실패: " + e.getMessage());
    }
  }

  @PutMapping("/post/{id}")
  public ResponseEntity<?> updatePostTitle(@PathVariable Long id, @RequestParam String newTitle) {
    try {
      postService.updatePostTitle(id, newTitle);
      return ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body("❌ 게시글 수정 실패: " + e.getMessage());
    }
  }

  @DeleteMapping("/post/{id}")
  public ResponseEntity<?> deletePostById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("❌ 게시글 삭제 실패: " + e.getMessage());
    }
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchPostsByKeyword(@RequestParam String keyword) {
    try {
      List<Post> matchedPosts = postService.searchPostsByKeyword(keyword);
      return ResponseEntity.ok().body(matchedPosts);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("❌ 게시글 검색 실패: " + e.getMessage());
    }
  }
}