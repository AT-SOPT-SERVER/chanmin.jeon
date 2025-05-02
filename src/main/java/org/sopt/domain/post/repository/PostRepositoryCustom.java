package org.sopt.domain.post.repository;

import java.util.List;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.type.Tag;

public interface PostRepositoryCustom {
  List<Post> searchByCondition(String title, String author, Tag tag);
}
