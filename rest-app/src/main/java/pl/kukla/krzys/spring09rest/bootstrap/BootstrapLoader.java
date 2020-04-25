package pl.kukla.krzys.spring09rest.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.spring09rest.domain.Category;
import pl.kukla.krzys.spring09rest.domain.Customer;
import pl.kukla.krzys.spring09rest.repository.CategoryRepository;
import pl.kukla.krzys.spring09rest.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapLoader implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            populateCategoryTable();
        }
        if (customerRepository.count() == 0) {
            populateCustomerTable();
        }

    }

    private void populateCustomerTable() {
        Customer customer1 = Customer.builder().firstName("first name").lastName("first last Name").build();
        Customer customer2 = Customer.builder().firstName("second name").lastName("second Name").build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2));
    }

    private void populateCategoryTable() {
        Category cat1 = Category.builder().name("first").build();
        Category cat2 = Category.builder().name("second").build();
        Category cat3 = Category.builder().name("third").build();

        List<Category> categories = Arrays.asList(cat1, cat2, cat3);

        categoryRepository.saveAll(categories);
    }

}
