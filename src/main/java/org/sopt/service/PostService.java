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
    if (isDuplicateTitle(title)) {
      throw new IllegalArgumentException("중복되는 제목은 등록할 수 없습니다.");
    }
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

    if (isDuplicateTitle(newTitle)) {
      throw new IllegalArgumentException("중복되는 제목은 등록할 수 없습니다.");
    }
    return postRepository.update(id, newTitle);
  }

  public boolean isDuplicateTitle(final String title) {
    List<Post> posts = postRepository.findAll();
    for (Post post : posts) {
      if (post.getTitle().equals(title)) {
        return true;
      }
    }
    return false;
  }
}