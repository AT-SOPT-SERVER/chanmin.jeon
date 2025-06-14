package org.sopt.domain.post.domain.repository;

import java.util.List;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.Tag;

public interface PostRepositoryCustom {
	List<Post> searchByCondition(String title, String author, Tag tag);
}
