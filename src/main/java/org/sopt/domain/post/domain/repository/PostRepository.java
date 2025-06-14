package org.sopt.domain.post.domain.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
  List<Post> findAllByOrderByCreatedAtDesc();

}
