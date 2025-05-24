package org.sopt.domain.user.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.exception.CustomException;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 10)
  private String author;


  protected User() {
  }

  private User(String author) {
    this.author = author;
  }

  public static User create(String author) {
    validateAuthor(author);
    return new User(author);
  }

  private static void validateAuthor(String author) {
    if (author == null || author.isBlank()) {
      throw new CustomException(UserErrorCode.NICKNAME_REQUIRED);
    }

    if (author.length() > 10) {
      throw new CustomException(UserErrorCode.NICKNAME_TOO_LONG);
    }
  }

  public Long getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

}
