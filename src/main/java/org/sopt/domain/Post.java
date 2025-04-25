package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private LocalDateTime createdAt;

  protected Post() {
  }

  public Post(String title) {
    this.title = title;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return this.title;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void updateTitle(String newTitle) {
    this.title = newTitle;
  }
}