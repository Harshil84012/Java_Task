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
import com.demo.main.MTM.service.TagService;

@ExtendWith(MockitoExtension.class)

class TagControllerTest {

	@Mock
	TagService tagService;

	@InjectMocks
	TagController tagController;

	@Test

	void getAllTag() {

		// Request
		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		List<Tag> mockList = new ArrayList<>();

		// Mocks
		post.add(new Post(mocktagid, "java", "language", null));
		mockList.add(new Tag(mocktagid, "harshil", post));

		// Mocking
		when(tagService.getAllTag()).thenReturn(mockList);

		// test

		List<Tag> actualList = tagController.getAllTag();

		// assert
		verify(tagService, times(1)).getAllTag();

		assertEquals(mockList, actualList);

	}

	@Test
	void getTagById() {

		// Request
		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(mocktagid, "title", "description", null));
		Tag tag = new Tag(mocktagid, "harshil", post);

		when(tagService.getTagById(mocktagid)).thenReturn(tag);

		Tag actualtag = tagController.getTagById(mocktagid);

		verify(tagService, times(1)).getTagById(mocktagid);

		assertEquals(tag, actualtag);

	}

	@Test
	void addTag() {
		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(mocktagid, "title", "description", null));
		Tag tag = new Tag(mocktagid, "harshil", post);

		when(tagService.addTag(tag)).thenReturn(tag);
		Tag actualTag = tagController.addTag(tag);

		verify(tagService, times(1)).addTag(actualTag);

		Assertions.assertThat(tag.getId()).isNotEqualTo(null);

		assertEquals(tag, actualTag);
	}

	@Test

	void updateTag() {

		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(mocktagid, "title", "description", null));
		Tag tag = new Tag(mocktagid, "harshil", post);

		when(tagService.updateTag(mocktagid, tag)).thenReturn(tag);
		tag.setName("asd");
		tag.setPosts(post);

		Tag actualtag = tagController.updateTag(mocktagid, tag);

		Mockito.verify(tagService, times(1)).updateTag(mocktagid, tag);
		Assertions.assertThat(tag.getId()).isNotEqualTo(null);

		assertEquals(tag, actualtag);
	}

	@Test
	void deleteTag() {

		int deleteid = 1;

		doNothing().when(tagService).deleteTagById(deleteid);

		tagController.deleteTag(deleteid);

		Mockito.verify(tagService, times(1)).deleteTagById(deleteid);

	}

}
