package com.demo.main.MTM.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.main.MTM.model.Post;
import com.demo.main.MTM.model.Tag;
import com.demo.main.MTM.service.PostService;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

	@Mock
	PostService postService;

	@InjectMocks
	PostController postController;

	@Test
	void getAllPost() {

		// request
		int mockPostId = 1;
		Set<Tag> tagSet = new HashSet<>();
		List<Post> mockPost = new ArrayList<>();

		// mock
		tagSet.add(new Tag(mockPostId, "name", null));
		mockPost.add(new Post(mockPostId, "title", "description", tagSet));

		// Mocking
		when(postService.getAllPost()).thenReturn(mockPost);

		// test
		List<Post> actualList = postController.GetAllPost();

		// Assertion
		verify(postService, times(1)).getAllPost();
		assertEquals(mockPost, actualList);

	}

	@Test
	void getPostById() {

		// request
		int mockPostId = 1;
		Set<Tag> tagSet = new HashSet<>();

		// mock
		tagSet.add(new Tag(mockPostId, "name", null));
		Post mockPost = new Post(mockPostId, "title", "description", tagSet);
		// Mocking
		when(postService.getPostById(mockPostId)).thenReturn(mockPost);

		// test
		Post actualPost = postController.getPostById(mockPostId);

		// Assertion
		verify(postService, times(1)).getPostById(mockPostId);
		assertEquals(mockPost, actualPost);

	}

	@Test

	void addPost() {
		int postid = 4;
		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(new Tag(postid, "harshil", null));
		Post post = new Post(postid, "java", "language", tagSet);

		when(postService.addPost(post)).thenReturn(post);

		Post mockpost = postController.addPost(post);

		Mockito.verify(postService, times(1)).addPost(mockpost);

		Assertions.assertThat(post.getId()).isNotEqualTo(null);

		assertEquals(post, mockpost);

	}

	@Test
	void updatePost() {

		int updateid = 3;

		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(new Tag(updateid, "harshil", null));
		Post post = new Post(updateid, "java", "language", tagSet);

		when(postService.updatePost(post, updateid)).thenReturn(post);

		Post mockpost = postController.updatePost(post, updateid);

		post.setDescription("dfg");
		post.setTitle("erfd");

		Mockito.verify(postService, times(1)).updatePost(mockpost, updateid);

		Assertions.assertThat(post.getId()).isNotEqualTo(null);
		assertEquals(post, mockpost);

	}

	@Test
	void deletePost() {

		int deleteid = 4;

		doNothing().when(postService).deletePostById(deleteid);

		postController.deletePost(deleteid);

		Mockito.verify(postService, times(1)).deletePostById(deleteid);

	}
//
//	@Test
//	void getPostByIdNotFOund() {
//
//		int postid = 3;
//
//		Set<Tag> tagSet = new HashSet<>();
//		tagSet.add(new Tag(postid, "harshil", null));
//		Post post = new Post(postid, "java", "language", tagSet);
//
//		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("id not found");
//
//		when(postService.getPostById(postid)).thenThrow(expeactedResponce);
//
//		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
//				() -> postController.getPostById(postid));
//
//		Mockito.verify(postService, times(1)).getPostById(postid);
//
//		Assertions.assertThat(expeactedResponce.getMessage()).isEqualTo(actualResponce.getMessage());
//
//	}
//	

}
