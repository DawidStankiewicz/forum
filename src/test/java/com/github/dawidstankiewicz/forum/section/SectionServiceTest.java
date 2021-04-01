package com.github.dawidstankiewicz.forum.section;

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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceTest {

    @Mock private SectionRepository sectionRepository;
    @Spy @InjectMocks private SectionService service;

    @Test
    public void shouldFindSections() {
        //given
        Pageable pageable = Pageable.unpaged();
        Page<Section> page = new PageImpl<>(Arrays.asList(
                Section.builder().id(1).build(),
                Section.builder().id(2).build()
        ));
        doReturn(page).when(sectionRepository).findAll(pageable);
        //when
        Page<Section> result = service.findSections(pageable);
        //then
        assertEquals(page, result);
        verify(sectionRepository).findAll(pageable);
    }
}
