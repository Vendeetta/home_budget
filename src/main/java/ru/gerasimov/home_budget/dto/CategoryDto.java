package ru.gerasimov.home_budget.dto;

public record CategoryDto(
        Integer categoryId,
        Integer categoryLimit,
        String title
) {
}
