package com.demo.main.MTM.service;

import java.util.List;

import com.demo.main.MTM.model.Tag;

public interface TagService {

	List<Tag> getAllTag();

	Tag addTag(Tag tag);


	Tag updateTag(Integer id, Tag tag);

	String deleteTagById(Integer id);

	Tag getTagById(Integer id);



}
