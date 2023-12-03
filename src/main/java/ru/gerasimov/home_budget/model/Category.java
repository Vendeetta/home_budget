package ru.gerasimov.home_budget.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Сущность описывает категорию бюджета, по которым могут приходить или уходить деньги.
 *
 * @author Evgeniy Gerasimov.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Category(Integer categoryId, Integer categoryLimit, String title) {
        this.categoryId = categoryId;
        this.categoryLimit = categoryLimit;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return categoryId != null ? categoryId.hashCode() : 0;
    }
}
