package com.fapa.springbootmongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fapa.springbootmongodb.domain.Post;
import com.fapa.springbootmongodb.exception.ObjectNotFoundException;
import com.fapa.springbootmongodb.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Post post = repo.findById(id).orElse(null);
		if (post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		return post;
	}
}
