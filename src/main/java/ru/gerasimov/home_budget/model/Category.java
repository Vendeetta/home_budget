package ru.gerasimov.home_budget.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Builder;

/**
 * Сущность описывает категорию бюджета, по которым могут приходить или уходить деньги.
 * <p>
 * author Evgeniy_Gerasimov
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category", schema = "home_budget")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_limit")
    private Integer categoryLimit;
    @Column(name = "title", nullable = false)
    private String title;
}
