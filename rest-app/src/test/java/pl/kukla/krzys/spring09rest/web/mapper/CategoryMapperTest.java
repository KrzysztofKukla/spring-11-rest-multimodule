package pl.kukla.krzys.spring09rest.web.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kukla.krzys.spring09rest.config.CustomContextConfiguration;
import pl.kukla.krzys.spring09rest.domain.Category;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomContextConfiguration.class)
class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

    private static final long ID = 222L;
    private static final String NAME = "category first";

    @Test
    void categoryToDto() throws Exception {
        Category category = Category.builder().id(ID).name(NAME).build();

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);

        Assertions.assertEquals(NAME, categoryDto.getName());
    }

    @Test
    void categoryDtoToCategory() throws Exception {
        CategoryDto categoryDto = CategoryDto.builder().name(NAME).build();

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        Assertions.assertEquals(NAME, category.getName());
    }

}