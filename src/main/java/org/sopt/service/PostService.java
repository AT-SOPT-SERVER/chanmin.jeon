package org.sopt.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;
import org.sopt.util.PostIdGenerator;
import org.sopt.validator.PostValidator;

public class PostService {
  private final PostRepository postRepository = new PostRepository();
  private LocalDateTime lastModifiedAt;

  public void createPost(String title) {
    PostValidator.validateTitle(title);
    if (isDuplicateTitle(title)) {
      throw new IllegalArgumentException("중복되는 제목은 등록할 수 없습니다.");
    }

    if (lastModifiedAt != null) {
      Duration duration = Duration.between(lastModifiedAt, LocalDateTime.now());
      if (duration.toMinutes() < 3) {
        throw new IllegalArgumentException("마지막 게시글 작성 이후 3분이 지나지 않았습니다.");
      }
    }
    Post post = new Post(PostIdGenerator.generateId(), title);
    postRepository.save(post);
    lastModifiedAt = LocalDateTime.now();
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

  public List<Post> searchPostsByKeyword(String keyword) {
    List<Post> posts = postRepository.findAll();
    List<Post> matchedPosts = new ArrayList<>();

    for (Post post : posts) {
      if (post.getTitle().contains(keyword)) {
        matchedPosts.add(post);
      }
    }

    return matchedPosts;
  }
}