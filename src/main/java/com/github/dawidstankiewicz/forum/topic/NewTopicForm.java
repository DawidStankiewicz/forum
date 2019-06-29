/**
 * Created by Dawid Stankiewicz on 28.07.2016
 */
package com.github.dawidstankiewicz.forum.topic;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class NewTopicForm {
    
    @Size(min = 3, max = 50, message = "{Size.Topic.title.validation}")
    private String title;
    
    @Size(min = 5, message = "{Size.Topic.content.validation}")
    private String content;
    
    @Min(value = 1, message = "{Min.Topic.sectionId.validation}")
    private int sectionId;
    
    public NewTopicForm() {}
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getSectionId() {
        return sectionId;
    }
    
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
    
}
