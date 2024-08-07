package com.github.dawidstankiewicz.forum.post;

import com.github.dawidstankiewicz.forum.model.entity.Post;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByTopicOrderByCreationDate(Topic topic);

    Set<Post> findTop5ByOrderByCreationDateDesc();
}
