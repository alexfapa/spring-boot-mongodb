package com.fapa.springbootmongodb.service;

import java.util.Date;
import java.util.List;

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
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); //formatação de data máxima para levar em consideração ano, mês, dia, hora, minuto, segundo e milissegundo
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	
}
