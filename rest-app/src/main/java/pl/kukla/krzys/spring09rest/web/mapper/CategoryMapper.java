package pl.kukla.krzys.spring09rest.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kukla.krzys.spring09rest.domain.Category;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

/**
 * @author Krzysztof Kukla
 */
//declare class as Spring Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryDtoToCategory(CategoryDto categoryDto);

    //here we are specifying custom properties, if there are not called the same
    @Mapping(source = "name", target = "name")
    CategoryDto categoryToCategoryDto(Category category);

}
