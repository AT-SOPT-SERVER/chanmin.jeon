package org.sopt.domain.post.domain.entity;

import java.util.ArrayList;
import java.util.List;

import org.sopt.domain.user.domain.entity.User;
import org.sopt.global.entity.BaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
	@Column(name = "tag", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Tag> tags = new ArrayList<>();

	private Post(String title, String content, User user, List<Tag> tags) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.tags = tags;
	}

	public static Post create(String title, String content, User user, List<Tag> tags) {
		return new Post(title, content, user, tags);
	}

	public void update(String title, String content, List<Tag> tags) {
		this.title = title;
		this.content = content;
		this.tags = tags;
	}

	public boolean isAuthor(Long userId) {
		return this.user.getId().equals(userId);
	}

}