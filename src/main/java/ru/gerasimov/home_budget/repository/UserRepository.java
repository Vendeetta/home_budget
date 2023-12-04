package ru.gerasimov.home_budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gerasimov.home_budget.model.User;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User.
 *
 * @author Evgeniy Gerasimov.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
