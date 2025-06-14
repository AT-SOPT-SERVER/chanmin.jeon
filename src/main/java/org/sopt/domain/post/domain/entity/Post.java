package org.sopt.domain.post.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.sopt.domain.post.presentation.dto.PostRequest;
import org.sopt.domain.user.domain.entity.User;
import org.sopt.global.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long id;

  @Column(name = "title", nullable = false, length = 30)
  private String title;

  @Column(name = "content", nullable = false, length = 1000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Tag tag;


  private Post(String title, String content, User user, Tag tag) {
    this.title = title;
    this.content = content;
    this.user = user;
    this.tag = tag;
  }

  public static Post create(String title, String content, User user, Tag tag) {
    return new Post(title, content, user, tag);
  }

  public void update(String title, String content, Tag tag) {
    this.title = title;
    this.content = content;
    this.tag = tag;
  }

  public boolean isAuthor(Long userId) {
    return this.user.getId().equals(userId);
  }

}