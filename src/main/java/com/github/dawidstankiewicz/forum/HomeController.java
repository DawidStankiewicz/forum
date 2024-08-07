package com.github.dawidstankiewicz.forum;

import com.github.dawidstankiewicz.forum.post.PostService;
import com.github.dawidstankiewicz.forum.section.SectionService;
import com.github.dawidstankiewicz.forum.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private final SectionService sectionService;
    private final TopicService topicService;
    private final PostService postService;

    public HomeController(SectionService sectionService, TopicService topicService, PostService postService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
        this.postService = postService;
    }

    @RequestMapping(value = {"/",
            "/home"})
    public String home(Model model) {
        model.addAttribute("sections", sectionService.findAll());
        model.addAttribute("topics", topicService.findRecent());
        model.addAttribute("posts", postService.findRecent());
        return "home";
    }

}
