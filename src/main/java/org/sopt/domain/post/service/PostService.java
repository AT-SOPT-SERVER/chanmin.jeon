package org.sopt.domain.post.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.sopt.domain.post.dto.PostDetailResponse;
import org.sopt.domain.post.dto.PostRequest;
import org.sopt.domain.post.dto.PostInfoResponse;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.post.type.Tag;
import org.sopt.domain.post.validator.PostValidator;
import org.sopt.domain.user.entity.User;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PostService {

  private static final long POST_INTERVAL_MINUTES = 3;

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public PostService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public void createPost(Long id, PostRequest request) {
    PostValidator.validateTitle(request.title());
    PostValidator.validateContent(request.content());

    User user = userRepository.findById(id)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    if (postRepository.existsByTitle(request.title())) {
      throw new CustomException(PostErrorCode.DUPLICATE_TITLE);
    }

    postRepository.findTopByOrderByCreatedAtDesc()
        .ifPresent(lastPost -> {
          Duration duration = Duration.between(lastPost.getCreatedAt(), LocalDateTime.now());
          if (duration.toMinutes() < POST_INTERVAL_MINUTES) {
            throw new CustomException(PostErrorCode.POST_INTERVAL_LIMIT);
          }
        });

    Tag tag = Tag.parse(request.tag())
        .orElseThrow(() -> new CustomException(PostErrorCode.INVALID_TAG));

    Post post = Post.create(request, tag, user);
    postRepository.save(post);
  }

  public List<PostInfoResponse> getPosts(String title, String author, String tag) {
    boolean isTitleNull = title == null;
    boolean isAuthorNull = author == null;
    boolean isTagNull = tag == null;

    boolean isTitleBlank = title != null && title.isBlank();
    boolean isAuthorBlank = author != null && author.isBlank();
    boolean isTagBlank = tag != null && tag.isBlank();

    if (isTitleNull && isAuthorNull && isTagNull) {
      return postRepository.findAll().stream()
          .map(PostInfoResponse::from)
          .toList();
    }

    if (isTitleBlank && isAuthorBlank && isTagBlank) {
      throw new CustomException(PostErrorCode.EMPTY_SEARCH_CONDITION);
    }

    Tag parsedTag = null;
    if (!isTagNull && !isTagBlank) {
      parsedTag = Tag.parse(tag)
          .orElseThrow(() -> new CustomException(PostErrorCode.INVALID_TAG));
    }


    return postRepository.searchByCondition(
            isTitleBlank ? null : title,
            isAuthorBlank ? null : author,
            parsedTag
        ).stream()
        .map(PostInfoResponse::from)
        .toList();

  }

  public PostDetailResponse getPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
    return PostDetailResponse.from(post);
  }


  @Transactional
  public void updatePostTitle(Long postId, Long userId, PostRequest request) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().getId().equals(userId)) {
      throw new CustomException(PostErrorCode.NO_PERMISSION);
    }

    PostValidator.validateTitle(request.title());
    PostValidator.validateContent(request.content());

    if (!post.getTitle().equals(request.title()) &&
        postRepository.existsByTitle(request.title())) {
      throw new CustomException(PostErrorCode.DUPLICATE_TITLE);
    }

    Tag tag = Tag.parse(request.tag())
        .orElseThrow(() -> new CustomException(PostErrorCode.INVALID_TAG));

    post.update(request, tag);
  }


  @Transactional
  public void deletePostById(Long postId, Long userId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().getId().equals(userId)) {
      throw new CustomException(PostErrorCode.NO_PERMISSION);
    }

    postRepository.delete(post);
  }
}
