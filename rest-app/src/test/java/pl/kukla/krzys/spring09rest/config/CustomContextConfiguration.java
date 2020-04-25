package pl.kukla.krzys.spring09rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kukla.krzys.spring09rest.web.mapper.CategoryMapper;
import pl.kukla.krzys.spring09rest.web.mapper.CategoryMapperImpl;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class CustomContextConfiguration {
    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapperImpl();
    }

}
