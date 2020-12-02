package com.gruzini.gramophone.queries

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.gruzini.gramophone.domain.User
import com.gruzini.gramophone.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQuery implements GraphQLQueryResolver{

    @Autowired
    UserService userService

    User getUser(String email) {
        return userService.getUserByEmail(email)
    }
}
