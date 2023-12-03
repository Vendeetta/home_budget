package ru.gerasimov.home_budget.dto;

/**
 * ДТО для передачи данных при работе с Category.
 * @param categoryId id category.
 * @param categoryLimit установленный лимит по категории.
 * @param userId id пользователя, которому принадлежит категория.
 * @param title наименование категории.
 *
 * @author Evgeniy Gerasimov.
 */
public record CategoryDto(
        Integer categoryId,
        Integer categoryLimit,
        Integer userId,
        String title
) {
}
