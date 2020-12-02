package com.gruzini.gramophone.bootstrap

import com.gruzini.gramophone.domain.User
import com.gruzini.gramophone.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class UserInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository

    @Override
    void run(final String... args) throws Exception {

        User user1 = new User(email: "jimmy@gmail.com", password: "test")
        User user2 = new User(email: "susan@gmail.com", password: "test")

        userRepository.saveAll(List.of(user1, user2))
    }
}
