package ru.gerasimov.home_budget.mapper;

import org.mapstruct.*;
import ru.gerasimov.home_budget.dto.CategoryDto;
import ru.gerasimov.home_budget.model.Category;
import ru.gerasimov.home_budget.model.User;

/**
 * Маппер сущности Category.
 *
 * @author Evgeniy Gerasimov.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    Category toEntity(CategoryDto dto);

    @Named("toUser")
    default User toUser(Integer userId) {
        return new User(userId);
    }
}
