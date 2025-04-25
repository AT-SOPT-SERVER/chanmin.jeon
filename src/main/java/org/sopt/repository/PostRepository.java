package org.sopt.repository;

import java.util.List;
import org.sopt.common.code.ErrorCode;
import org.sopt.domain.Post;
import org.sopt.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  boolean existsByTitle(String title);

  Post findTopByOrderByCreatedAtDesc();

  List<Post> findByTitleContaining(String keyword);

  default Post getByIdOrThrow(Long id) {
    return findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
  }
}