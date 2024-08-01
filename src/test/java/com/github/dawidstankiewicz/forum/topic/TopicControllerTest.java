package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.model.dto.NewTopicForm;
import com.github.dawidstankiewicz.forum.model.entity.Section;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.section.SectionService;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TopicControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private SectionService sectionService;
    @Mock
    private TopicService topicService;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Authentication authentication;
    @Spy
    @InjectMocks
    private TopicController controller;


    @Test
    public void shouldGetNewTopicForm() {
        //given
        int sectionId = 12;
        String expectedRedirect = "topics/new_topic_form";
        List<Section> sections = Collections.singletonList(Section.builder().build());
        doReturn(sections).when(sectionService).findAll();
        doReturn(new Section()).when(sectionService).findOne(sectionId);
        //when
        String result = controller.getNewTopicForm(sectionId, model);
        //then
        assertEquals(expectedRedirect, result);
        verify(model).addAttribute(eq("selectedSection"), any(Section.class));
        verify(model).addAttribute(eq("newTopic"), any(NewTopicForm.class));
        verify(model).addAttribute(eq("sections"), eq(sections));
        verify(sectionService).findOne(sectionId);
        verify(sectionService).findAll();
    }

    @Test
    public void shouldCreateNewTopic() {
        //given
        String username = "username";
        int sectionId = 12;
        doReturn(username).when(authentication).getName();
        User user = User.builder().build();
        doReturn(user).when(userService).findByUsername(eq(username));
        Section section = Section.builder().build();
        doReturn(section).when(sectionService).findOne(sectionId);
        doReturn(Topic.builder().id(1).build()).when(topicService).createNewTopic(any(), eq(user), eq(section));
        String expectedRedirect = "redirect:/topics/1";
        //when
        String result = controller.processAndAddNewTopic(sectionId, new NewTopicForm(), bindingResult, authentication, model);
        //then
        assertEquals(expectedRedirect, result);
        verify(userService).findByUsername(any());
        verify(sectionService).findOne(eq(sectionId));
        verify(topicService).createNewTopic(any(), any(), any());
    }

    @Test
    public void shouldNotCreateNewTopic_WhenFormHasErrors() {
        //given
        int sectionId = 12;
        doReturn(true).when(bindingResult).hasErrors();
        String expectedRedirect = "topics/new_topic_form";
        //when
        String result = controller.processAndAddNewTopic(sectionId, new NewTopicForm(), bindingResult, authentication, model);
        //then
        assertEquals(expectedRedirect, result);
        verifyNoInteractions(userService);
        verifyNoInteractions(topicService);
    }

}
