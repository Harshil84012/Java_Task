package com.demo.main.MTM.service;

import java.util.List;

import com.demo.main.MTM.model.Post;

public interface PostService {

	List<Post> getAllPost();

	Post getPostById(Integer id);

	Post addPost(Post post);

	void deletePostById(Integer id);

	Post updatePost(Post post, Integer id);

}
