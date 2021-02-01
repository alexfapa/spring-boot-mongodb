package com.fapa.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fapa.springbootmongodb.domain.Post;
import com.fapa.springbootmongodb.domain.User;
import com.fapa.springbootmongodb.dto.AuthorDTO;
import com.fapa.springbootmongodb.repository.PostRepository;
import com.fapa.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		stf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User alexpinto = new User(null, "Alex Pinto", "alexfapa32@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob, alexpinto));
		
		Post post1 = new Post(null, stf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, stf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
	
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
		
	}

}
