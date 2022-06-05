package com.demo.main.MTM.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.MTM.model.Tag;
import com.demo.main.MTM.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping("/getall")
	public List<Tag> getAllTag() {
		return tagService.getAllTag();
	}

	@PostMapping("/addtag")
	public Tag addTag(@RequestBody Tag tag) {
		return tagService.addTag(tag);
	}

	@GetMapping("/{id}")
	public Tag getTagById(@PathVariable Integer id) {
		return tagService.getTagById(id);
	}

	@PutMapping("/{id}")
	public Tag updateTag(@PathVariable Integer id, @RequestBody Tag tag) {
		return tagService.updateTag(id, tag);

	}

	@DeleteMapping("/{id}")
	public String deleteTag(@PathVariable Integer id) {

		tagService.deleteTagById(id);
		return "data deleted successfully";
	}

}
