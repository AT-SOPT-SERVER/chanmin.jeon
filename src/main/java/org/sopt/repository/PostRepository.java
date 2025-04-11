package org.sopt.repository;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.util.PostFileHandler;
import org.sopt.util.PostIdGenerator;

public class PostRepository {

  List<Post> postList;

  public PostRepository() {
    this.postList = PostFileHandler.loadPosts();

    int maxId = 0;

    for (Post post : postList) {
      if (post.getId() > maxId) {
        maxId = post.getId();
      }
    }

    PostIdGenerator.initializedId(maxId + 1);
  }

  public void save(Post post) {
    postList.add(post);
    PostFileHandler.savePosts(postList);
  }

  public List<Post> findAll() {
    postList = PostFileHandler.loadPosts();
    return postList;
  }

  public Post findPostById(int id) {
    for (Post post : postList) {
      if (post.getId() == id) {
        return post;
      }
    }

    return null;
  }

  public boolean delete(int id) {
    boolean isMatched = false;
    for (Post post : postList) {
      if (post.getId() == id) {
        postList.remove(post);
        isMatched = true;
      }
    }

    if (isMatched) {
      PostFileHandler.savePosts(postList);
    }

    return isMatched;
  }

  public Boolean update(int id, String newTitle) {
    for (Post post : postList) {
      if (post.getId() == id) {
        post.setTitle(newTitle);
        PostFileHandler.savePosts(postList);
        return true;
      }
    }
    return false;
  }
}