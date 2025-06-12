package org.sopt.domain.comment.domain.entity;

import org.sopt.domain.post.entity.Post;
import org.sopt.domain.user.domain.entity.User;
import org.sopt.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 300)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private Comment(Post post, User user, String content) {
		this.post = post;
		this.user = user;
		this.content = content;
	}

	public static Comment create(Post post, User user, String content) {
		return new Comment(post, user, content);
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public boolean isAuthor(Long userId) {
		return this.user.getId().equals(userId);
	}
}
