package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
  private final PostService postService = new PostService();

  public void createPost(final String title) {
    postService.createPost(title);
  }

  public List<Post> getAllPosts() {
    return postService.getAllPosts();
  }

  public Post getPostById(final int id) {
    return postService.getPostById(id);
  }

  public Boolean updatePostTitle(final int id, final String newTitle) {
    return postService.updatePostTitle(id, newTitle);
  }

  public boolean deletePostById(final int id) {
    return postService.deletePostById(id);
  }

  public List<Post> searchPostsByKeyword(final String keyword) {
    return postService.searchPostsByKeyword(keyword);
  }

}