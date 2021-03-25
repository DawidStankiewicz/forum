package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.model.entity.UserProfile;
import com.github.dawidstankiewicz.forum.post.PostService;
import com.github.dawidstankiewicz.forum.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private TopicService topicService;
    
    public UserProfile findOne(int userId) {
        UserProfile userProfile = new UserProfile();
        User user = userService.findOne(userId);
        userProfile.setUser(user);
//        userProfile.setPosts(postService.findByUser(user));
//        userProfile.setTopics(topicService.findByUser(user));
        return userProfile;
    }
    
    public UserProfile findOne(String username) {
        return findOne(userService.findByUsername(username).getId());
    }
    
}
