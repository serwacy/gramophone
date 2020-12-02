package com.gruzini.gramophone.services

import com.gruzini.gramophone.domain.User
import com.gruzini.gramophone.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    UserRepository userRepository

    User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
    }

    User createUser(String email, String password) {
        User user = new User(email: email, password: password)
        return userRepository.save(user)
    }

    User deleteUser(String email) {
        User user = getUserByEmail(email)
        userRepository.delete(user)
        return user
    }
}
