package com.github.dawidstankiewicz.forum.section;

import com.github.dawidstankiewicz.forum.model.entity.Section;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.topic.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SectionControllerTest {

    @Mock
    private SectionService sectionService;
    @Mock private TopicService topicService;
    @Mock private Model model;
    private SectionController controller;

    @Before
    public void setUp() {
        controller = new SectionController(sectionService, topicService);
    }

    @Test
    public void shouldReturnSectionPage() {
        //given
        int id = 1;
        Section section = Section.builder().id(id).build();
        when(sectionService.findOneOrExit(id)).thenReturn(section);
        Set<Topic> topics = Set.of(Topic.builder().id(12).build());
        when(topicService.findBySection(section)).thenReturn(topics);
        //when
        String resultTemplate = controller.getSection(1, model);
        //then
        assertEquals("sections/section", resultTemplate);
        verify(model).addAttribute("section", section);
        verify(model).addAttribute("topics", topics);
    }
}
