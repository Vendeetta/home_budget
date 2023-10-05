package ru.gerasimov.home_budget.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность описывает категорию бюджета, по которым могут приходить или уходить деньги.
 * <p>
 * author Evgeniy_Gerasimov
 */
@Entity
@Getter
@Setter
@Table(name = "Category", schema = "home_budget")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "limit")
    private Integer limit;
    @Column(name = "name", nullable = false)
    private String name;
}
