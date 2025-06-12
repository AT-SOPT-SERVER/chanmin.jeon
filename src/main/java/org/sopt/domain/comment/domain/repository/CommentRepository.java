package org.sopt.domain.comment.domain.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(Long postId);

	Optional<Comment> findByIdAndUserId(Long commentId, Long userId);
}
