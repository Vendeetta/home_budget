package ru.gerasimov.home_budget.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.gerasimov.home_budget.TestContainerConfig;
import ru.gerasimov.home_budget.model.Category;
import ru.gerasimov.home_budget.model.Role;
import ru.gerasimov.home_budget.model.User;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Интеграционные тесты работы приложения с БД.
 */
@SpringBootTest
@ContextConfiguration(initializers = TestContainerConfig.PostgresInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Тест корректного сохранения и получение сущностей User из БД.")
    @Transactional
    void repositoryTest() {
        //given
        User user1 = new User(null, "pass", "email1@email.ru", null, Role.USER);
        Category category1 = new Category(null, 200, "test2", user1);
        user1.setCategory(Set.of(category1));
        int entitiesInCategoryAtStart = categoryRepository.findAll().size();
        //when Сохраняем сущность.
        repository.saveAll(List.of(user1));
        //then Ожидаем получить из БД сущность User, также новую сохраненную сущность Category сущности.
        assertEquals(user1, repository.findById(user1.getUserId()).orElse(null));
        assertEquals(entitiesInCategoryAtStart + 1, categoryRepository.findAll().size());
        assertTrue(repository.findById(user1.getUserId())
                .get()
                .getCategory()
                .contains(category1));
    }
}