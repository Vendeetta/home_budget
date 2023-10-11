package ru.gerasimov.home_budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gerasimov.home_budget.model.Operation;

/**
 * Репозиторий для работы с сущностью Operation.
 * <p>
 * author Evgeniy_Gerasimov
 */
public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
