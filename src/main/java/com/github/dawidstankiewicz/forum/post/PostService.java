package com.github.dawidstankiewicz.forum.post;

import com.github.dawidstankiewicz.forum.model.entity.Post;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.topic.TopicService;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class PostService {
    
    private final PostRepository postRepository;
    
    private final TopicService topicService;
    
    private final UserService userService;

    public PostService(PostRepository postRepository, TopicService topicService, UserService userService) {
        this.postRepository = postRepository;
        this.topicService = topicService;
        this.userService = userService;
    }

    public Post findOne(int id) {
        // todo fix optional
        return postRepository.findById(id).get();
    }
    
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    
    public Set<Post> findRecent() {
        return postRepository.findTop5ByOrderByCreationDateDesc();
    }
    
    public List<Post> findByTopic(int idTopic) {
        return findByTopic(topicService.findOne(idTopic));
    }
    
    public List<Post> findByTopic(Topic topic) {
        return postRepository.findByTopicOrderByCreationDate(topic);
    }
    
    public void save(Post post) {
        postRepository.save(post);
    }
    
    public void delete(int id) {
        delete(findOne(id));
    }
    
    public void delete(Post post) {
        postRepository.delete(post);
    }
    
    public void save(String content,
                     String username,
                     int idTopic) {
        Post post = new Post();
        post.setTopic(topicService.findOne(idTopic));
        post.setUser(userService.findByUsername(username));
        post.setContent(content);
        save(post);
    }
    
}
