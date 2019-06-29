/**
 * Created by Dawid Stankiewicz on 04.08.2016
 */
package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.user.UserProfile;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.post.PostService;
import com.github.dawidstankiewicz.forum.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dawidstankiewicz.forum.user.UserService;
import com.github.dawidstankiewicz.forum.user.UserProfileService;


@Service
public class UserProfileServiceImpl implements UserProfileService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private TopicService topicService;
    
    @Override
    public UserProfile findOne(int userId) {
        UserProfile userProfile = new UserProfile();
        User user = userService.findOne(userId);
        userProfile.setUser(user);
        userProfile.setPosts(postService.findByUser(user));
        userProfile.setTopics(topicService.findByUser(user));
        return userProfile;
    }
    
    @Override
    public UserProfile findOne(String username) {
        return findOne(userService.findByUsername(username).getId());
    }
    
}
