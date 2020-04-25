package pl.kukla.krzys.spring09rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.spring09rest.domain.Category;
import pl.kukla.krzys.spring09rest.exception.CategoryNotFoundException;
import pl.kukla.krzys.spring09rest.repository.CategoryRepository;
import pl.kukla.krzys.spring09rest.web.mapper.CategoryMapper;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Cannot find Category with id=" + id));
        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto getByName(String name) {
        Category category = categoryRepository.findByName(name)
            .orElseThrow(() -> new CategoryNotFoundException("Cannot find Category with name " + name));
        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
            .map(category -> categoryMapper.categoryToCategoryDto(category))
            .collect(Collectors.toList());
    }

}
