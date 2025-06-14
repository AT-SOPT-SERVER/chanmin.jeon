package org.sopt.domain.post.domain.repository;

import java.util.List;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.QPost;
import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.user.domain.entity.QUser;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Post> searchByCondition(String title, String author, Tag tag) {
		QPost post = QPost.post;
		QUser user = QUser.user;

		return queryFactory.selectFrom(post)
			.join(post.user, user).fetchJoin()
			.where(
				title != null ? post.title.containsIgnoreCase(title) : null,
				author != null ? user.author.containsIgnoreCase(author) : null,
				tag != null ? post.tag.eq(tag) : null
			)
			.orderBy(post.createdAt.desc())
			.fetch();
	}
}
