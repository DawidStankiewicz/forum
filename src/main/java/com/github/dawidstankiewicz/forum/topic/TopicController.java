/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.section.SectionService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.dawidstankiewicz.forum.post.NewPostForm;
import com.github.dawidstankiewicz.forum.post.Post;
import com.github.dawidstankiewicz.forum.post.PostService;
import com.github.dawidstankiewicz.forum.user.UserService;


@Controller
@RequestMapping("/topic/")
public class TopicController {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private SectionService sectionService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "{idTopic}", method = RequestMethod.GET)
    public String getTopicById(@PathVariable int idTopic,
                               Model model) {
        Topic topic = topicService.findOne(idTopic);
        topic.setViews(topic.getViews() + 1);
        topicService.save(topic);
        
        model.addAttribute("topic", topic);
        model.addAttribute("posts", postService.findByTopic(idTopic));
        model.addAttribute("newPost", new NewPostForm());
        return "topic";
    }
    
    @RequestMapping(value = "{idTopic}", method = RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute("newPost") NewPostForm newPost,
                          BindingResult result,
                          Authentication authentication,
                          @PathVariable int idTopic,
                          Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("topic", topicService.findOne(idTopic));
            model.addAttribute("posts", postService.findByTopic(idTopic));
            return "topic";
        }
        
        Post post = new Post();
        post.setContent(newPost.getContent());
        post.setTopic(topicService.findOne(idTopic));
        post.setUser(userService.findByUsername(authentication.getName()));
        postService.save(post);
        
        model.asMap().clear();
        return "redirect:/topic/" + idTopic;
    }
    
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getNewTopictForm(Model model) {
        model.addAttribute("newTopic", new NewTopicForm());
        model.addAttribute("sections", sectionService.findAll());
        return "new_topic_form";
    }
    
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processAndAddNewTopic(@Valid @ModelAttribute("newTopic") NewTopicForm newTopic,
                                        BindingResult result,
                                        Authentication authentication,
                                        Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("sections", sectionService.findAll());
            return "new_topic_form";
        }
        
        Topic topic = new Topic();
        topic.setUser(userService.findByUsername(authentication.getName()));
        topic.setTitle(newTopic.getTitle());
        topic.setContent(newTopic.getContent());
        topic.setSection(sectionService.findOne(newTopic.getSectionId()));
        topicService.save(topic);
        
        return "redirect:/topic/" + topic.getId();
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,
                         Authentication authentication,
                         RedirectAttributes model) {
        Topic topic = topicService.findOne(id);
        
        if (topic == null) {
            return "redirect:/";
        }
        if (!authentication.getName().equals(topic.getUser().getUsername())) {
            return "redirect:/topic/" + id;
        }
        
        topicService.delete(topic);
        
        model.addFlashAttribute("message", "topic.successfully.deleted");
        return "redirect:/section/" + topic.getSection().getId();
    }
    
}
