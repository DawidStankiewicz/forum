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
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private UserService userService;
    
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
    
    public Set<Post> findAllByOrderByCreationDateDesc() {
        return postRepository.findAllByOrderByCreationDateDesc();
    }
    
    public Set<Post> findByUser(User user) {
        return postRepository.findByUser(user);
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
