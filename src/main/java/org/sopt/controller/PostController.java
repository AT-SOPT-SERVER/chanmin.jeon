package org.sopt.controller;

import java.util.Collections;
import java.util.List;
import org.sopt.domain.Post;
import org.sopt.service.PostService;

public class PostController {

  private final PostService postService = new PostService();

  public void createPost(final String title) {
    try {
      postService.createPost(title);
    } catch (IllegalArgumentException e) {
      System.out.println("❌ 게시글 작성 실패: " + e.getMessage());
    }
  }

  public List<Post> getAllPosts() {
    try {
      return postService.getAllPosts();
    } catch (Exception e) {
      System.out.println("❌ 게시글 목록 조회 실패: " + e.getMessage());
      return Collections.emptyList();
    }
  }

  public Post getPostById(final int id) {
    try {
      return postService.getPostById(id);
    } catch (Exception e) {
      System.out.println("❌ 게시글 조회 실패: " + e.getMessage());
      return null;
    }
  }

  public Boolean updatePostTitle(final int id, final String newTitle) {
    try {
      return postService.updatePostTitle(id, newTitle);
    } catch (IllegalArgumentException e) {
      System.out.println("❌ 게시글 수정 실패: " + e.getMessage());
      return false;
    }
  }

  public boolean deletePostById(final int id) {
    try {
      return postService.deletePostById(id);
    } catch (Exception e) {
      System.out.println("❌ 게시글 삭제 실패: " + e.getMessage());
      return false;
    }
  }

  public List<Post> searchPostsByKeyword(final String keyword) {
    try {
      return postService.searchPostsByKeyword(keyword);
    } catch (Exception e) {
      System.out.println("❌ 게시글 검색 실패: " + e.getMessage());
      return Collections.emptyList();
    }
  }
}
