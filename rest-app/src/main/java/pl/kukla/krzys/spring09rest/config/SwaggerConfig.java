package pl.kukla.krzys.spring09rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Krzysztof Kukla
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {  //extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .apiInfo(apiInfo());
    }

    //Metadata
    //    public ApiInfo(String title, String description, String version, String termsOfServiceUrl, Contact contact, String license, String licenseUrl,
//    Collection<VendorExtension> vendorExtensions) {
    @Bean
    public ApiInfo apiInfo() {
        String url = "http://url.com";
        Contact contact = new Contact("name", url, "blue19k@poczta.fm");
        return new ApiInfo(
            "title", "desciption", "version", url, contact, "licence", url,
            Collections.EMPTY_LIST
        );
    }

    //by default this is configured by Spring Boot automatically
    //but here for demonstration we provided that configuration
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//            .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//            .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

}
