package com.demo.main.MTM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.MTM.exception.ResourceNotFoundException;
import com.demo.main.MTM.model.Post;
import com.demo.main.MTM.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(Integer id) {
		// TODO Auto-generated method stub
		return getPostByIdRequired(id);
	}

	@Override
	public Post addPost(Post post) {
		// TODO Auto-generated method stub

		return postRepository.save(post);
	}

	@Override
	public void deletePostById(Integer id) {
		// TODO Auto-generated method stub

		Post post = getPostById(id);
		postRepository.delete(post);
	}

	@Override
	public Post updatePost(Post post, Integer id) {
		// TODO Auto-generated method stub

		Post epost = getPostByIdRequired(id);
		epost.setTitle(post.getTitle());

		epost.setDescription(post.getDescription());

		return postRepository.save(epost);
	}

	private Post getPostByIdRequired(Integer id) {
		// TODO Auto-generated method stub
		return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post  id not found " + id));

	}

}
