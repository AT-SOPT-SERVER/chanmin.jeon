package org.sopt.service;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.validator.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public void createPost(String title) {
    PostValidator.validateTitle(title, postRepository);
    PostValidator.validatePostInterval(postRepository);
    postRepository.save(new Post(title));
  }

  @Transactional(readOnly = true)
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Post getPostById(Long id) {
    return postRepository.getByIdOrThrow(id);
  }

  @Transactional
  public void updatePostTitle(Long id, String newTitle) {
    PostValidator.validateTitle(newTitle, postRepository);
    Post post = postRepository.getByIdOrThrow(id);
    post.updateTitle(newTitle);
  }

  @Transactional
  public void deletePostById(Long id) {
    Post post = postRepository.getByIdOrThrow(id);
    postRepository.delete(post);
  }

  @Transactional(readOnly = true)
  public List<Post> searchPostsByKeyword(String keyword) {
    return postRepository.findByTitleContaining(keyword);
  }
}
