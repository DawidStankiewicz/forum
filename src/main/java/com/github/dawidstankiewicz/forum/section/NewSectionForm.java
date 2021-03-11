/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.dawidstankiewicz.forum.section;

import lombok.Data;


@Data
public class NewSectionForm {
    
//    @Size(min = 1, max = 50, message = "{Size.Section.type.validation}")
    private String name;
    
//    @Size(max = 300)
    private String description;
    
}
