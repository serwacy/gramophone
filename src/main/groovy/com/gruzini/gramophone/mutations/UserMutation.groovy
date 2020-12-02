package com.gruzini.gramophone.mutations

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.gruzini.gramophone.domain.User
import com.gruzini.gramophone.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserMutation implements GraphQLMutationResolver {

    @Autowired
    UserService userService

    User createUser(String email, String password) {
        return userService.createUser(email, password)
    }

    User deleteUser(String email) {
        return userService.deleteUser(email)
    }
}
