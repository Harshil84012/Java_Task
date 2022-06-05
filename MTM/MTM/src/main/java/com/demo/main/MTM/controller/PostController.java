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

import com.demo.main.MTM.model.Post;
import com.demo.main.MTM.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/getall")
	public List<Post> GetAllPost() {
		return postService.getAllPost();
	}

	@GetMapping("/{id}")
	public Post getPostById(@PathVariable Integer id) {
		return postService.getPostById(id);
	}

	@PostMapping("/addpost")
	public Post addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}

	@PutMapping("/{id}")
	public Post updatePost(@RequestBody Post post, @PathVariable Integer id) {
		return postService.updatePost(post, id);
	}

	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable Integer id) {
		postService.deletePostById(id);
		return "delete post successfully!";
	}
}
