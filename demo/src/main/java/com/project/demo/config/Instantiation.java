package com.project.demo.config;

import com.project.demo.domain.Post;
import com.project.demo.domain.User;
import com.project.demo.dto.AuthorDTO;
import com.project.demo.dto.CommentDTO;
import com.project.demo.repository.PostRepository;
import com.project.demo.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

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

    CommentDTO c1 = new CommentDTO(
      "Boa viagem mano",
      sdf.parse("23/04/2023"),
      new AuthorDTO(alex)
    );

    CommentDTO c2 = new CommentDTO(
      "Aproveite",
      sdf.parse("24/04/2023"),
      new AuthorDTO(bob)
    );

    CommentDTO c3 = new CommentDTO(
      "Tenha um ótimo dia",
      sdf.parse("25/04/2023"),
      new AuthorDTO(alex)
    );

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().addAll(Arrays.asList(c3));

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
