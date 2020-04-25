package pl.kukla.krzys.spring09rest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kukla.krzys.spring09rest.domain.Category;
import pl.kukla.krzys.spring09rest.repository.CategoryRepository;
import pl.kukla.krzys.spring09rest.web.mapper.CategoryMapper;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Long id;
    private String name;

    @BeforeEach
    void setUp() {
        id = 1L;
        name = "first";
    }

    @Test
    void getOne() {
        Category category = Category.builder().id(id).name(name).build();
        CategoryDto categoryDto = CategoryDto.builder().name(name).build();

        BDDMockito.when(categoryRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(category));
        BDDMockito.when(categoryMapper.categoryToCategoryDto(ArgumentMatchers.any(Category.class))).thenReturn(categoryDto);

        CategoryDto categoryDtoResponse = categoryService.getById(id);

        Assertions.assertNotNull(categoryDtoResponse);
        Assertions.assertEquals(categoryDto, categoryDtoResponse);

    }

    @Test
    void getAll() {
    }

}