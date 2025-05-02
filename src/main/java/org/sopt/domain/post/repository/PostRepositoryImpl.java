package org.sopt.domain.post.repository;

import java.util.List;
import org.sopt.domain.post.dto.PostSummaryResponse;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.type.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final PostRepository postRepository;

  public PostRepositoryImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public List<Post> searchByCondition(String title, String author, Tag tag) {
    boolean isTitleBlank = (title == null || title.isBlank());
    boolean isAuthorBlank = (author == null || author.isBlank());
    boolean isTagNull = (tag == null);

    if (!isTitleBlank && !isAuthorBlank && !isTagNull) {
      return postRepository.findByTitleContainingAndUser_AuthorContainingAndTag(title, author, tag);
    }

    if (!isTitleBlank && !isAuthorBlank) {
      return postRepository.findByTitleContainingAndUser_AuthorContaining(title, author);
    }

    if (!isTitleBlank && !isTagNull) {
      return postRepository.findByTitleContainingAndTag(title, tag);
    }

    if (!isAuthorBlank && !isTagNull) {
      return postRepository.findByUser_AuthorContainingAndTag(author, tag);
    }

    if (!isTitleBlank) {
      return postRepository.findByTitleContaining(title);
    }

    if (!isAuthorBlank) {
      return postRepository.findByUser_AuthorContaining(author);
    }

    if (!isTagNull) {
      return postRepository.findByTag(tag);
    }
    return List.of();
  }
}
