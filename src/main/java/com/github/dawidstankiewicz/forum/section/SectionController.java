package com.github.dawidstankiewicz.forum.section;


import com.github.dawidstankiewicz.forum.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sections/")
public class SectionController {

    @Autowired private SectionService sectionService;
    @Autowired private TopicService topicService;

    @RequestMapping("{id}")
    public String getSection(@PathVariable int id,
                                       Model model) {
        model.addAttribute("section", sectionService.findOne(id));
        model.addAttribute("topics", topicService.findBySection(id));
        return "sections/section";
    }

}
