package com.project.demo.config;

import com.project.demo.domain.Post;
import com.project.demo.domain.User;
import com.project.demo.dto.AuthorDTO;
import com.project.demo.repository.PostRepository;
import com.project.demo.repository.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {
    userRepository.deleteAll();
    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 = new Post(
      null,
      formattedInstant("23/01/2018"),
      "Partiu viagem",
      "Vou viajar para São Paulo. Abraços",
      new AuthorDTO(maria)
    );

    Post post2 = new Post(
      null,
      formattedInstant("23/03/2018"),
      "Bom Dia",
      "Acordei feliz hoje",
      new AuthorDTO(maria)
    );

    postRepository.saveAll(Arrays.asList(post1, post2));
    maria.getPosts().addAll(Arrays.asList(post1, post2));

    userRepository.save(maria);
  }

  private Instant formattedInstant(String string) {
    DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("dd/MM/yyyy")
      .withZone(ZoneId.systemDefault());

    return LocalDate
      .parse(string, formatter)
      .atStartOfDay(ZoneId.systemDefault())
      .toInstant();
  }
}
