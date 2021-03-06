package com.demo.main.MTM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.MTM.exception.ResourceNotFoundException;
import com.demo.main.MTM.model.Tag;
import com.demo.main.MTM.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> getAllTag() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}

	@Override
	public Tag addTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagRepository.save(tag);
	}

	@Override
	public Tag getTagById(Integer id) {
		// TODO Auto-generated method stub
		return getTagByIdRequired(id);
	}

	@Override
	public Tag updateTag(Integer id, Tag tag) {
		// TODO Auto-generated method stub
		Tag etag = getTagByIdRequired(id);
		etag.setName(tag.getName());
		etag.setPosts(tag.getPosts());
		return tagRepository.save(etag);
	}

	@Override
	public String deleteTagById(Integer id) {
		// TODO Auto-generated method stub
		Tag tag = getTagByIdRequired(id);
		tagRepository.delete(tag);
		return "data deleted successfully";
	}

	private Tag getTagByIdRequired(Integer id) {
		return tagRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student id not found " + id));

	}

}
