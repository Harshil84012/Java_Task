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
import com.demo.main.MTM.repository.TagRepository;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

	@Mock
	TagRepository tagRepository;

	@InjectMocks
	TagServiceImpl tagServiceImpl;

	@Test
	void getAllTag() {

		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		List<Tag> mockList = new ArrayList<>();

		// Mocks
		post.add(new Post(mocktagid, "java", "language", null));
		mockList.add(new Tag(mocktagid, "harshil", post));

		when(tagRepository.findAll()).thenReturn(mockList);

		List<Tag> actualtag = tagServiceImpl.getAllTag();

		Mockito.verify(tagRepository, times(1)).findAll();
		assertEquals(mockList, actualtag);

	}

	@Test
	void addTag() {
		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(mocktagid, "title", "description", null));
		Tag tag = new Tag(mocktagid, "harshil", post);

		when(tagRepository.save(tag)).thenReturn(tag);
		Tag actualTag = tagServiceImpl.addTag(tag);
		verify(tagRepository, times(1)).save(actualTag);
		Assertions.assertThat(tag.getId()).isNotEqualTo(null);

		assertEquals(mocktagid, mocktagid);
	}

	@Test
	void updateTag() {
		int mocktagid = 2;
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(mocktagid, "title", "description", null));
		Tag tag = new Tag(mocktagid, "harshil", post);

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");
		when(tagRepository.findById(mocktagid)).thenThrow(expeactedResponce);
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> tagServiceImpl.getTagById(mocktagid));

		Mockito.verify(tagRepository, times(1)).findById(mocktagid);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}

	@Test
	void deleteTagByIdnotFound() {
		int deleteid = 3;

		Set<Post> post = new HashSet<Post>();
		post.add(new Post(deleteid, "title", "description", null));
		Tag tag = new Tag(deleteid, "harshil", post);

		when(tagRepository.findById(deleteid)).thenReturn(Optional.empty());
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> tagServiceImpl.deleteTagById(deleteid));

		Mockito.verify(tagRepository, times(1)).findById(deleteid);
		assertNotNull(actualResponce);

	}

	@Test
	void deleteTag() {

		// Reqest
		int deleteid = 3;

		// Mocks
		Set<Post> post = new HashSet<Post>();
		post.add(new Post(deleteid, "title", "description", null));
		Tag tag = new Tag(deleteid, "harshil", post);

		// Mocking
		when(tagRepository.findById(deleteid)).thenReturn(Optional.of(tag));
		doNothing().when(tagRepository).delete(tag);

		// test
		tagServiceImpl.deleteTagById(deleteid);

		// Assertion
		Mockito.verify(tagRepository, times(1)).delete(tag);

	}

}
