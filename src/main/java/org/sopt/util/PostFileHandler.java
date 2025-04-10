package org.sopt.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.sopt.domain.Post;

public class PostFileHandler {

  private static final String FILE_PATH = "src/main/java/org/sopt/assets/Post.txt";

  public static void savePosts(final List<Post> allPosts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (Post post : allPosts) {
        writer.write("id: " + post.getId() + " | title: " + post.getTitle());
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("❎ 파일 저장 중 오류가 발생했습니다: " + e.getMessage());
    }
  }

  public static List<Post> loadPosts() {
    List<Post> posts = new ArrayList<>();
    File file = new File(FILE_PATH);

    if (!file.exists()) return posts;

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] split = line.split("\\|");

        String idPart = split[0].trim();
        int id = Integer.parseInt(idPart.substring(4));

        String titlePart = split[1].trim();
        String title = titlePart.substring(7);

        posts.add(new Post(id, title));
      }
    } catch (IOException e) {
      System.out.println("❎ 파일 로딩 중 오류가 발생했습니다: " + e.getMessage());
    }

    return posts;
  }

}
