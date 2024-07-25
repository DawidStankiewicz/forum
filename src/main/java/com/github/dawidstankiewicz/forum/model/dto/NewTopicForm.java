package com.github.dawidstankiewicz.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTopicForm {
    @NotEmpty
    private String title;
    private int sectionId;
    @NotEmpty
    private String content;

}
