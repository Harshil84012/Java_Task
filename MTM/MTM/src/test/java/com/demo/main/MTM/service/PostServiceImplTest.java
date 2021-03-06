package com.demo.main.MTM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.main.MTM.exception.ResourceNotFoundException;
import com.demo.main.MTM.model.Post;
import com.demo.main.MTM.model.Tag;
import com.demo.main.MTM.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

	@Mock
	PostRepository postRepository;

	@InjectMocks
	PostServiceImpl postServiceImpl;

	@Test
	void getAllPost() {

		// request
		int mockPostId = 1;
		Set<Tag> tagSet = new HashSet<>();
		List<Post> mockPost = new ArrayList<>();

		// mock
		tagSet.add(new Tag(mockPostId, "name", null));
		mockPost.add(new Post(mockPostId, "title", "description", tagSet));

		when(postRepository.findAll()).thenReturn(mockPost);

		List<Post> actualpost = postServiceImpl.getAllPost();

		Mockito.verify(postRepository, times(1)).findAll();
		assertEquals(mockPost, actualpost);

	}

	@Test
	void getPostById() {

		// request
		int mockPostId = 1;
		Set<Tag> tagSet = new HashSet<>();

		// mock
		tagSet.add(new Tag(mockPostId, "name", null));
		Post mockPost = new Post(mockPostId, "title", "description", tagSet);

		when(postRepository.findById(mockPostId)).thenReturn(Optional.of(mockPost));

		Post post = postServiceImpl.getPostById(mockPostId);

		verify(postRepository, times(1)).findById(mockPostId);
		assertEquals(mockPost, post);

	}

	@Test
	void addPost() {

		int postid = 4;
		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(new Tag(postid, "harshil", null));
		Post mockpost = new Post(postid, "java", "language", tagSet);

		when(postRepository.save(mockpost)).thenReturn(mockpost);

		Post post = postServiceImpl.addPost(mockpost);

		Assertions.assertThat(post.getId()).isNotEqualTo(null);
		assertEquals(mockpost, post);

	}

	@Test
	void updatePost() {
		int postid = 4;
		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(new Tag(postid, "harshil", null));
		Post mockpost = new Post(postid, "java", "language", tagSet);

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");
		when(postRepository.findById(postid)).thenThrow(expeactedResponce);
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> postServiceImpl.getPostById(postid));

		Mockito.verify(postRepository, times(1)).findById(postid);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}

	@Test
	void deletePost() {

		// Request
		int deleteid = 3;

		// Mocks
		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(new Tag(deleteid, "harshil", null));
		Post mockpost = new Post(deleteid, "java", "language", tagSet);

		when(postRepository.findById(deleteid)).thenReturn(Optional.of(mockpost));
		doNothing().when(postRepository).delete(mockpost);

		postServiceImpl.deletePostById(deleteid);

		// assertions
		verify(postRepository, times(1)).delete(mockpost);

	}

	@Test
	void deletePostByIdnotFound() {

		// request
		int deleteid = 1;

		when(postRepository.findById(deleteid)).thenReturn(Optional.empty());
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> postServiceImpl.deletePostById(deleteid));

		// assertions
		Mockito.verify(postRepository, times(1)).findById(deleteid);
		assertNotNull(actualResponce);

	}

}
