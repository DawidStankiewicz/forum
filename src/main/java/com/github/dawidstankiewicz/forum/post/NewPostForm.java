/**
 * Created by Dawid Stankiewicz on 30.07.2016
 */
package com.github.dawidstankiewicz.forum.post;

import javax.validation.constraints.Size;


public class NewPostForm {
    
    @Size(min = 3, message = "{Size.Post.content.validation}")
    private String content;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
}
