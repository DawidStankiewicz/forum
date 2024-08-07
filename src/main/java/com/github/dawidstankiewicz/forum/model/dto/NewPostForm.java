package com.github.dawidstankiewicz.forum.model.dto;

import com.github.dawidstankiewicz.forum.model.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPostForm {

    private String content;
    private Post.ContentType contentType;

}
