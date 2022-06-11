package com.lance.carbnbbackend.service

import com.lance.carbnbbackend.entities.User
import com.lance.carbnbbackend.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class UserService {
    @Autowired
    lateinit var userRepo: UserRepo

    fun getUsers(): List<User> {
        return userRepo.findAll()
    }

    fun addUser(user: User): String {
        val existingUser: Optional<User> = userRepo.findById(user.id!!)
        return if (existingUser.isPresent) {
            "User already exists"
        } else {
            val savedUser = userRepo.save(user)

            return if (savedUser.id != null) {
                "Success"
            } else {
                "Failed"
            }
        }
    }

    fun deleteUser(id: BigDecimal): String {
        val userToDelete = User()
        userToDelete.id = id

        return try {
            userRepo.delete(userToDelete)
            "Success"
        } catch (ex: Exception) {
            "Failed"
        }
    }
}