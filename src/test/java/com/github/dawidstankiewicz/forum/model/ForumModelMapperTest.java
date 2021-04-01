package com.github.dawidstankiewicz.forum.model;

import com.github.dawidstankiewicz.forum.model.dto.SectionDto;
import com.github.dawidstankiewicz.forum.model.entity.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ForumModelMapperTest {

    @Spy @InjectMocks private ForumModelMapper mapper;

    @Test
    public void shouldMapSectionsPage() {
        //given
        List<Section> sections = Arrays.asList(
                Section.builder().id(1).build(),
                Section.builder().id(2).build()
        );
        Page<Section> page = new PageImpl<>(sections);
        //when
        Page<SectionDto> result = mapper.mapPage(page, SectionDto.class);
        //then
        assertEquals(sections.size(), result.getTotalElements());
        assertTrue(result.getContent().stream().anyMatch(dto -> dto.getId() == 1));
        assertTrue(result.getContent().stream().anyMatch(dto -> dto.getId() == 2));
    }
}
