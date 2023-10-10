package ru.gerasimov.home_budget.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.gerasimov.home_budget.TestContainerConfig;
import ru.gerasimov.home_budget.model.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = TestContainerConfig.PostgresInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Тест корректного сохранения и получение сущностей из БД.")
    void repositoryTest() {
        //given
        Category category1 = new Category(null, 100, "test1");
        Category category2 = new Category(null, 200, "test2");
        //when Сохраняем две сущности.
        categoryRepository.saveAll(List.of(category1, category2));
        //then Ожидаем получить из БД две сущности.
        assertEquals(2, categoryRepository.findAll().size());
    }

}