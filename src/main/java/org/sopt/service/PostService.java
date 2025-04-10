package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;
import org.sopt.validator.PostValidator;

public class PostService {
  private final PostRepository postRepository = new PostRepository();
  private int postId = 1;

  public void createPost(String title) {
    PostValidator.validateTitle(title);
    Post post = new Post(postId++, title);
    postRepository.save(post);
  }

  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  public Post getPostById(int id) {
    return postRepository.findPostById(id);
  }

  public boolean deletePostById(int id) {
    return postRepository.delete(id);
  }

  public Boolean updatePostTitle(int id, String newTitle) {
    PostValidator.validateTitle(newTitle);
    return postRepository.update(id, newTitle);
  }
}