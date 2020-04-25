package pl.kukla.krzys.spring09rest.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kukla.krzys.spring09rest.service.CategoryService;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;
import pl.kukla.krzys.spring09rest.web.model.CategoryListDto;

import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    static final String BASE_URL = "/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAll() {
        List<CategoryDto> categories = categoryService.getAll();
        return CategoryListDto.builder().categories(categories).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getByName(@PathVariable String name) {
        return categoryService.getByName(name);
    }

}
