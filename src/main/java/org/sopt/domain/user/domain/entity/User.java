package org.sopt.domain.user.domain.entity;

import org.sopt.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "author", nullable = false, length = 10, unique = true)
	private String author;

	@Column(name = "password", nullable = false)
	private String password;

	@Builder
	private User(String author, String password) {
		this.author = author;
		this.password = password;
	}

	public static User create(String author, String password) {
		return User.builder()
			.author(author)
			.password(password)
			.build();
	}
}
