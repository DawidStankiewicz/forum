package com.github.dawidstankiewicz.forum.section;

import com.github.dawidstankiewicz.forum.config.Routes;
import com.github.dawidstankiewicz.forum.model.ForumModelMapper;
import com.github.dawidstankiewicz.forum.model.dto.SectionDto;
import com.github.dawidstankiewicz.forum.model.entity.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SectionAdminControllerTest {

    @Mock private SectionService sectionService;
    @Mock private ForumModelMapper mapper;
    @Mock private Model model;
    @Spy @InjectMocks private SectionAdminController controller;

    @Test
    public void shouldReturnSectionsPage() {
        //given
        Pageable pageable = Pageable.unpaged();
        Page<Section> page = new PageImpl<>(Arrays.asList(
                Section.builder().id(1).build(),
                Section.builder().id(2).build()
        ));
        Page<SectionDto> dtos = new PageImpl<>(Arrays.asList(
                SectionDto.builder().id(1).build(),
                SectionDto.builder().id(2).build()
        ));
        doReturn(page).when(sectionService).findSections(pageable);
        doReturn(dtos).when(mapper).mapPage(page, SectionDto.class);
        //when
        String result = controller.getSectionsPage(model, pageable);
        //then
        assertEquals(Routes.Views.ADMIN_SECTIONS_PANEL, result);
        verify(model).addAttribute("sections", dtos);
        verify(sectionService).findSections(pageable);
        verify(mapper).mapPage(page, SectionDto.class);
    }
}
