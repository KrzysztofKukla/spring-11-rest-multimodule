package pl.kukla.krzys.spring09rest.service;

import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

import java.util.List;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryService {

    CategoryDto getById(Long id);

    CategoryDto getByName(String name);

    List<CategoryDto> getAll();

}
