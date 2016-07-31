/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.szczypioreg.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.szczypioreg.forum.controller.form.NewSectionForm;
import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.service.SectionService;
import com.github.szczypioreg.forum.service.TopicService;


@Controller
public class SectionController {
    
    @Autowired
    private SectionService sectionService;
    
    @Autowired
    private TopicService topicService;
    
    @RequestMapping("/section/{id}")
    public String getTopicsFromSection(@PathVariable int id, Model model) {
        model.addAttribute("section", sectionService.findOne(id));
        model.addAttribute("topics", topicService.findBySection(id));
        return "section";
    }
    
    @RequestMapping(value = "/section/new", method = RequestMethod.GET)
    public String getNewSectionForm(Model model) {
        model.addAttribute("newSection", new NewSectionForm());
        return "new_section_form";
    }
    
    @RequestMapping(value = "/section/new", method = RequestMethod.POST)
    public String processAndAddNewSection(
            @Valid @ModelAttribute("newSection") NewSectionForm newSection, BindingResult result) {
        
        if (result.hasErrors()) {
            return "new_section_form";
        }
        
        Section section = new Section();
        section.setName(newSection.getName());
        section = sectionService.save(section);
        return "redirect:/section/" + section.getIdSection();
    }
    
}
