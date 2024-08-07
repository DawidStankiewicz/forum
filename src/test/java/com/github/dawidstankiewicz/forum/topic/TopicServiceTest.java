package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.model.dto.NewTopicForm;
import com.github.dawidstankiewicz.forum.model.entity.Post;
import com.github.dawidstankiewicz.forum.model.entity.Section;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.post.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {

    @Mock private TopicRepository repository;
    @Mock private PostRepository postRepository;

    @Spy @InjectMocks private TopicService service;

    @Test
    public void shouldCreateNewTopic() {
        //given
        NewTopicForm topicForm = NewTopicForm.builder().title("title").build();
        User author = new User();
        Section section = new Section();
        Topic expectedTopic = Topic.builder().title(topicForm.getTitle()).user(author).section(section).build();
        doReturn(expectedTopic).when(repository).save(expectedTopic);
        Post expectedPost = Post.builder().topic(expectedTopic).user(author).build();
        //when
        Topic result = service.createNewTopic(topicForm, author, section);
        //then
        verify(repository).save(eq(expectedTopic));
        verify(postRepository).save(eq(expectedPost));
        assertEquals(topicForm.getTitle(), result.getTitle());
    }
}
