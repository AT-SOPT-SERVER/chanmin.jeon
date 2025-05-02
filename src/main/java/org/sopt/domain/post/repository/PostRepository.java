package org.sopt.domain.post.repository;

import java.util.List;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.type.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  boolean existsByTitle(String title);

  List<Post> findByTitleContainingAndUser_AuthorContainingAndTag(String title, String author,
      Tag tag);
  List<Post> findByTitleContainingAndUser_AuthorContaining(String title, String author);

  List<Post> findByTitleContainingAndTag(String title, Tag tag);
  List<Post> findByUser_AuthorContainingAndTag(String author, Tag tag);

  List<Post> findByTitleContaining(String title);

  List<Post> findByUser_AuthorContaining(String author);

  List<Post> findByTag(Tag tag);

  Post findTopByOrderByCreatedAtDesc();

  List<Post> searchByCondition(String title, String author, Tag tag);

}