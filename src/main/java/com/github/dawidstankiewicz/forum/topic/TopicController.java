package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.model.entity.Section;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.model.dto.NewPostForm;
import com.github.dawidstankiewicz.forum.model.entity.Post;
import com.github.dawidstankiewicz.forum.model.dto.NewTopicForm;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.post.PostService;
import com.github.dawidstankiewicz.forum.section.SectionService;
import com.github.dawidstankiewicz.forum.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@Slf4j
public class TopicController {

    @Autowired
    private PostService postService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/topics/{idTopic}", method = RequestMethod.GET)
    public String getTopicById(@PathVariable int idTopic,
                               Model model) {
        log.debug("Getting topic id {}", idTopic);
        Topic topic = topicService.findOne(idTopic);
        topic.setViews(topic.getViews() + 1);

        model.addAttribute("topic", topic);
        model.addAttribute("posts", postService.findByTopic(idTopic));
        model.addAttribute("newPost", new NewPostForm());
        return "topics/topic";
    }

    @RequestMapping(value = "/topics/{idTopic}", method = RequestMethod.POST)
    public String addPost(
//            @Valid
            @ModelAttribute("newPost") NewPostForm newPost,
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
        post.setUser(userService.findByEmail(authentication.getName()));
        postService.save(post);

        model.asMap().clear();
        return "redirect:/topic/" + idTopic;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/sections/{sectionId}/topics/new", method = RequestMethod.GET)
    public String getNewTopicForm(@PathVariable int sectionId, Model model) {
        model.addAttribute("selectedSection", sectionService.findOne(sectionId));
        model.addAttribute("newTopic", NewTopicForm.builder().sectionId(sectionId).build());
        model.addAttribute("sections", sectionService.findAll());
        return "topics/new_topic_form";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/sections/{selectedSectionId}/topics/new", method = RequestMethod.POST)
    public String processAndAddNewTopic(@PathVariable int selectedSectionId,
                                        @Valid @ModelAttribute("newTopic") NewTopicForm newTopic,
                                        BindingResult result,
                                        Authentication authentication,
                                        Model model) {
        log.info("Create new topic requested by user: " + authentication.getName());
        if (result.hasErrors()) {
            return getNewTopicForm(selectedSectionId, model);
        }
        User user = userService.findByEmailOrExit(authentication.getName());
        Section section = sectionService.findOne(selectedSectionId);
        Topic topic = topicService.createNewTopic(newTopic, user, section);
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
        if (!authentication.getName().equals(topic.getUser().getEmail())) {
            return "redirect:/topic/" + id;
        }

        topicService.delete(topic);

        model.addFlashAttribute("message", "topic.successfully.deleted");
        return "redirect:/section/" + topic.getSection().getId();
    }

}
