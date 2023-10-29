package ru.gerasimov.home_budget.model;

import jakarta.persistence.*
import lombok.Data

/**
 * Сущность описывает пользователя.
 * <p>
 * author Evgeniy_Gerasimov
 */
@Entity
@Data
@Table(name = "User", schema = "home_budget")
open class User() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    open var userId: Int? = null

    @Column(name = "username", nullable = false)
    open var username: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    @Column(name = "category")
    open lateinit var category: MutableSet<Category>

    constructor(userId: Int?, username: String?, password: String?, email: String?, category: MutableSet<Category>) : this() {
        this.userId = userId
        this.username = username
        this.password = password
        this.email = email
        this.category = category
    }
}
