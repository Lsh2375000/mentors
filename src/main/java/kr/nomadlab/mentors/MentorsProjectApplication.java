package kr.nomadlab.mentors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MentorsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MentorsProjectApplication.class, args);
    }

}
