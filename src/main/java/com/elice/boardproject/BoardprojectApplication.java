package com.elice.boardproject;

//import com.elice.boardproject.comment.repository.CommentRepository;
//import com.elice.boardproject.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardprojectApplication.class, args);
	}

//	@Bean
//	@Profile("local")
//	public DataInit stubDataInit(BoardRepository boardRepository) {
//		return new DataInit(boardRepository);
//	}

}
