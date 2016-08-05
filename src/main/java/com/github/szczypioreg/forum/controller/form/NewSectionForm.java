/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.szczypioreg.forum.controller.form;

import javax.validation.constraints.Size;


public class NewSectionForm {
    
    @Size(min = 3, max = 50, message = "{Size.Section.name.validation}")
    private String name;
    
    public NewSectionForm() {}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
