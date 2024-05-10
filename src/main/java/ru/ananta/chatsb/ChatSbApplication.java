package ru.ananta.chatsb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatSbApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        SpringApplication.run(ChatSbApplication.class, args);
    }
}
