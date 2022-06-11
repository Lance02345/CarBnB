package com.lance.carbnbbackend.controllers

import com.lance.carbnbbackend.entities.User
import com.lance.carbnbbackend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
class MainController {
    @Autowired
    lateinit var userService: UserService

    @RequestMapping(value = ["/get-users"], method = [RequestMethod.GET])
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @RequestMapping(value = ["/add-user"], method = [RequestMethod.POST])
    fun addUser(@RequestBody user: User): String {
        return userService.addUser(user)
    }

    @RequestMapping(value = ["/delete-user"], method = [RequestMethod.DELETE])
    fun deleteUser(@RequestParam id:BigDecimal): String {
        return userService.deleteUser(id)
    }
}