package org.sopt.domain.post.entity;

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
import java.time.LocalDateTime;
import org.sopt.domain.post.dto.PostRequest;
import org.sopt.domain.post.type.Tag;
import org.sopt.domain.user.entity.User;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(nullable = false, length = 1000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Tag tag;

  private LocalDateTime createdAt;

  protected Post() {
  }

  private Post(String title, String content, User user, Tag tag) {
    this.title = title;
    this.content = content;
    this.user = user;
    this.tag = tag;
  }

  public static Post create(PostRequest request, Tag tag, User user) {
    Post post = new Post();
    post.title = request.title();
    post.content = request.content();
    post.tag = tag;
    post.user = user;
    post.createdAt = LocalDateTime.now();
    return post;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  public void update(PostRequest request, Tag tag) {
    this.title = request.title();
    this.content = request.content();
    this.tag = tag;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public Tag getTag() {
    return this.tag;
  }

  public User getUser() {
    return this.user;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void updateTitle(String newTitle) {
    this.title = newTitle;
  }


}