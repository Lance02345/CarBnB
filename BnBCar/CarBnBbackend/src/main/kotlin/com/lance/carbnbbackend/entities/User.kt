package com.lance.carbnbbackend.entities

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "carbnb")
class User{
    @Id
    @Column
    var id : BigDecimal? = null

    var email: String? = null

    var username: String? = null

    var password: String? = null
}