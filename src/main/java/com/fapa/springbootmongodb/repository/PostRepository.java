package com.fapa.springbootmongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fapa.springbootmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//title -> campo que será referenciado
	//$regex -> o número relacionado ao campo que será mostrado, no caso text que é o primeiro campo
	//$options -> no caso i para ignorar letras maiúsculas
	@Query("{'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' }  }, { 'comments.text': { $regex: ?0, $options: 'i' }  } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
