package com.github.dawidstankiewicz.forum.model;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ForumModelMapper extends ModelMapper {


    public <T> Page<T> mapPage(Page<?> sections, Class<T> destinationClass) {
        return new PageImpl<>(
                sections.getContent()
                        .stream()
                        .map(item -> map(item, destinationClass))
                        .collect(Collectors.toList()),
                sections.getPageable(),
                sections.getTotalElements());
    }
}
