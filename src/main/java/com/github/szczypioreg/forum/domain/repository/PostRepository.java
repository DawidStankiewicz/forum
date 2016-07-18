/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.User;

public interface PostRepository {
    
    Post getPostById(int id);
    
    List<Post> getRecent(int count);
    
    List<Post> getPostsByUser(User user);
    
    void create(Post post);
}
