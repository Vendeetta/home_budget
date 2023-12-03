package ru.gerasimov.home_budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gerasimov.home_budget.model.Category;

/**
 * Репозиторий для работы с сущностью Category.
 *
 * @author Evgeniy Gerasimov.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
