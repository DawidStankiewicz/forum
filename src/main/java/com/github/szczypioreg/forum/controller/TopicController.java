/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.service.PostService;
import com.github.szczypioreg.forum.service.SectionService;
import com.github.szczypioreg.forum.service.TopicService;


@Controller
public class TopicController {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private SectionService sectionService;
    
    @RequestMapping(value = "/topic/{idTopic}", method = RequestMethod.GET)
    public String getTopicById(@PathVariable int idTopic, Model model) {
        model.addAttribute("topic", topicService.findOne(idTopic));
        model.addAttribute("posts", postService.findByTopic(idTopic));
        model.addAttribute("reply", new Post());
        return "topic";
    }
    
    @RequestMapping(value = "/topic/{idTopic}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("reply") String content, Authentication authentication,
            @PathVariable int idTopic, Model model) {
        postService.save(content, authentication.getName(), idTopic);
        model.asMap().clear();
        return "redirect:/topic/" + idTopic;
    }
    
    @RequestMapping(value = "/topic/new", method = RequestMethod.GET)
    public String getNewTopictForm(Model model) {
        Topic topicModel = new Topic();
        model.addAttribute("topicModel", topicModel);
        model.addAttribute("sections", sectionService.findAll());
        return "newtopicform";
    }
    
    @RequestMapping(value = "/topic/new", method = RequestMethod.POST)
    public String processAndAddNewTopic(@ModelAttribute("topicModel") Topic topicToAdded,
            Authentication authentication) {
        Topic topic = topicService.save(topicToAdded);
        System.out.println();
        return "redirect:/topic/" + topic.getIdTopic();
    }
    
}
