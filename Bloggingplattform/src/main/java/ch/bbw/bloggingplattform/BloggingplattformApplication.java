package ch.bbw.bloggingplattform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingplattformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingplattformApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BlogRepository blogRepositoryDb, UserRepository userRepository) {
        return args -> {
            BlogUser user1 = new BlogUser("loris", "123");
            BlogUser user2 = new BlogUser("joris", "password1234");
            BlogUser user3 = new BlogUser("anna", "securePass1");
            BlogUser user4 = new BlogUser("ben", "qwertz987");
            BlogUser user5 = new BlogUser("carla", "pass1234");
            BlogUser user6 = new BlogUser("david", "admin2024");
            BlogUser user7 = new BlogUser("elena", "mySecret!");
            BlogUser user8 = new BlogUser("felix", "testUser77");
            BlogUser user9 = new BlogUser("greta", "pw123456");
            BlogUser user10 = new BlogUser("hannes", "letmein99");
            BlogUser user11 = new BlogUser("irina", "sunshine55");
            BlogUser user12 = new BlogUser("jonas", "welcome@1");
            BlogUser user13 = new BlogUser("miriam", "mySecretPasswort1634?!");
            BlogUser user14 = new BlogUser("markus", "WinterthurerGhost@1");
            BlogUser user15 = new BlogUser("silvana", "4667hd@1");
            BlogUser user16 = new BlogUser("valentin", "qwerty101213");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
            userRepository.save(user6);
            userRepository.save(user7);
            userRepository.save(user8);
            userRepository.save(user9);
            userRepository.save(user10);
            userRepository.save(user11);
            userRepository.save(user12);
            userRepository.save(user13);
            userRepository.save(user14);
            userRepository.save(user15);
            userRepository.save(user16);

            blogRepositoryDb.save(new Blog("Kaffetrinken mit Mama", "Heute Morgen bin ich zusammen mit meiner Mutter im Hardrockkaffee Berlin Mittagessen gegangen! Es hat mich sehr gefreut dich wiedereinmal zu sehen, Mama!", user7));
            blogRepositoryDb.save(new Blog("Einkaufen gehen mit Wuffi", "Heute bin ich gemeinsam mit meinem Hund im Migros Bern einkaufen gegangen!", user2));
        };
    }
}

