package org.sopt.domain.post.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.type.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  boolean existsByTitle(String title);

  Optional<Post> findTopByOrderByCreatedAtDesc();

  @Query("""
        SELECT p FROM Post p
        JOIN p.user u
        WHERE (:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')))
        AND (:author IS NULL OR LOWER(u.author) LIKE LOWER(CONCAT('%', :author, '%')))
        AND (:tag IS NULL OR p.tag = :tag)
        ORDER BY p.createdAt DESC
    """)
  List<Post> searchByCondition(
      @Param("title") String title,
      @Param("author") String author,
      @Param("tag") Tag tag
  );
}
