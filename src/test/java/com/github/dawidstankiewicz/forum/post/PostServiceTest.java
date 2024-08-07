package com.github.dawidstankiewicz.forum.post;

import com.github.dawidstankiewicz.forum.exception.ResourceNotFoundException;
import com.github.dawidstankiewicz.forum.model.entity.Post;
import com.github.dawidstankiewicz.forum.model.entity.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock private PostRepository postRepository;
    private PostService postService;

    @Before
    public void setUp() {
        postService = new PostService(postRepository);
    }

    @Test
    public void shouldFindOnePost() {
        //given
        Post post = Post.builder().id(1).build();
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        //when
        Post result = postService.findOneOrExit(1);
        //then
        assertEquals(post, result);
    }

    @Test
    public void shouldExitWhenPostNotFound() {
        //given
        //when
        try {
            Post result = postService.findOneOrExit(11);
            fail("Exception should have been thrown");
        } catch (ResourceNotFoundException ignored) {
        }
        //then
        verify(postRepository).findById(11);
    }

    @Test
    public void shouldFindRecentPosts() {
        //given
        //when
        Set<Post> posts = postService.findRecent();
        //then
        verify(postRepository).findTop5ByOrderByCreationDateDesc();
    }

    @Test
    public void shouldFindByTopic() {
        //given
        Topic topic = Topic.builder().build();
        //when
        List<Post> result = postService.findByTopic(topic);
        //then
        verify(postRepository).findByTopicOrderByCreationDate(eq(topic));
    }

    @Test
    public void shouldSavePost() {
        //given
        Post post = Post.builder().id(1).build();
        //when
        postService.save(post);
        //then
        verify(postRepository).save(post);
    }

    @Test
    public void shouldDeletePost() {
        //given
        Post post = Post.builder().id(1).build();
        //when
        postService.delete(post);
        //then
        verify(postRepository).delete(post);
    }
}
