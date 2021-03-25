package com.github.dawidstankiewicz.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewSectionForm {
    
    private String name;
    private String description;
    
}
