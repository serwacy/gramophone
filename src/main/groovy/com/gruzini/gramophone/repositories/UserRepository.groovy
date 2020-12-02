package com.gruzini.gramophone.repositories

import com.gruzini.gramophone.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email)
}