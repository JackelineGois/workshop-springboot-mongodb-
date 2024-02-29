package com.project.demo.config;

import com.project.demo.domain.Post;
import com.project.demo.domain.User;
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

    Post post1 = new Post(
      null,
      formattedInstant("23/01/2018"),
      "Partiu viagem",
      "Vou viajar para São Paulo. Abraços",
      maria
    );

    Post post2 = new Post(
      null,
      formattedInstant("23/03/2018"),
      "Bom Dia",
      "Acordei feliz hoje",
      maria
    );

    userRepository.saveAll(Arrays.asList(maria, alex, bob));
    postRepository.saveAll(Arrays.asList(post1, post2));
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
