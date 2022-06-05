package com.demo.main.MTM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.MTM.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
