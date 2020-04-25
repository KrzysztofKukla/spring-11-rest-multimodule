package pl.kukla.krzys.spring09rest.web.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kukla.krzys.spring09rest.exception.CategoryNotFoundException;
import pl.kukla.krzys.spring09rest.exception.RestResponseEntityControllerAdvice;
import pl.kukla.krzys.spring09rest.service.CategoryService;
import pl.kukla.krzys.spring09rest.web.model.CategoryDto;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    MockMvc mockMvc;

    CategoryDto validCategory;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
            .setControllerAdvice(RestResponseEntityControllerAdvice.class)
            .build();
        validCategory = CategoryDto.builder().name("first").build();
    }

    @Test
    void getAll() throws Exception {
        CategoryDto cat2 = CategoryDto.builder().name("second").build();

        BDDMockito.when(categoryService.getAll()).thenReturn(Arrays.asList(validCategory, cat2));

        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.categories", Matchers.hasSize(2)));

        BDDMockito.then(categoryService).should().getAll();
    }

    @Test
    void getByIdExist() throws Exception {
        BDDMockito.when(categoryService.getById(anyLong())).thenReturn(validCategory);

        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL + "/{id}", 1)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", Matchers.equalTo(validCategory.getName())));

        BDDMockito.then(categoryService).should().getById(anyLong());
    }

    @Test
    void getByIdNotExist() throws Exception {
        BDDMockito.when(categoryService.getById(anyLong())).thenThrow(CategoryNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL + "/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        BDDMockito.then(categoryService).should().getById(anyLong());
    }

    @Test
    void getByNameExist() throws Exception {
        BDDMockito.when(categoryService.getByName(anyString())).thenReturn(validCategory);

        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL + "/name/{name}", "some name")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", Matchers.equalTo(validCategory.getName())));

        BDDMockito.then(categoryService).should().getByName(anyString());
    }

}