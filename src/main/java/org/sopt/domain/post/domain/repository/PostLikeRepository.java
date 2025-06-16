package org.sopt.domain.post.domain.repository;

import java.util.Optional;

import org.sopt.domain.post.domain.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	boolean existsByUserIdAndPostId(Long userId, Long postId);

	Optional<PostLike> findByUserIdAndPostId(Long userId, Long postId);

	int countByPostId(Long postId);
}
