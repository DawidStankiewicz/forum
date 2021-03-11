/**
 * Created by Dawid Stankiewicz on 04.08.2016
 */
package com.github.dawidstankiewicz.forum.user;

import java.util.Set;

import com.github.dawidstankiewicz.forum.post.Post;
import com.github.dawidstankiewicz.forum.topic.Topic;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserProfile {
    
    private User user;
    private Set<Post> posts;
    private Set<Topic> topics;
    
    public UserProfile(User user,
                       Set<Post> posts,
                       Set<Topic> topics) {
        super();
        this.user = user;
        this.posts = posts;
        this.topics = topics;
    }
}
