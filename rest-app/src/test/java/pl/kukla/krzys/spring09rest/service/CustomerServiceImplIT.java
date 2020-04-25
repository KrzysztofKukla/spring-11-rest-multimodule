package pl.kukla.krzys.spring09rest.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.kukla.krzys.spring09rest.domain.Customer;
import pl.kukla.krzys.spring09rest.repository.CustomerRepository;
import pl.kukla.krzys.spring09rest.web.mapper.CustomerMapperImpl;
import pl.kukla.krzys.spring09rest.web.model.CustomerDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Krzysztof Kukla
 */
//it contains @ExtendWith(SpringExtension.class)
//it brings up smaller suite components of Spring Context essentially DataJpa components,
// NOT whole Spring Context
@DataJpaTest
class CustomerServiceImplIT {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, new CustomerMapperImpl());
        customerRepository.deleteAll();
    }

    @Test
    void findAllCustomers() throws Exception {
        List<CustomerDto> customers = customerService.findAll();

        Assertions.assertThat(customers).isEmpty();
    }

    @Test
    void createCustomer() throws Exception {
        Assumptions.assumeTrue(customerRepository.count() == 0);
        CustomerDto customerDto = CustomerDto.builder().firstName("first created").lastName("last created").build();

        CustomerDto savedCustomer = customerService.createCustomer(customerDto);

        assertEquals(1, customerRepository.count());
        assertAll(
            () -> assertEquals(customerDto.getFirstName(), savedCustomer.getFirstName()),
            () -> assertEquals(customerDto.getLastName(), savedCustomer.getLastName())
        );
    }

    @Test
    void updateCustomer() throws Exception {
        Assumptions.assumeTrue(customerRepository.count() == 0);
        Customer customerToSave = Customer.builder().firstName("Chris").lastName("kukla").build();
        CustomerDto customerPreparedToUpdate = CustomerDto.builder().firstName("first created").lastName("last created").build();

        Customer savedCustomer = customerRepository.save(customerToSave);
        Assumptions.assumeTrue(customerRepository.count() == 1);

        CustomerDto updatedCustomer = customerService.updateCustomer(savedCustomer.getId(), customerPreparedToUpdate);

        assertEquals(1, customerRepository.count());
        assertAll(
            () -> assertEquals(customerPreparedToUpdate.getFirstName(), updatedCustomer.getFirstName()),
            () -> assertEquals(customerPreparedToUpdate.getLastName(), updatedCustomer.getLastName())
        );
    }

    @Test
    void deleteCustomer() throws Exception {
        Assumptions.assumeTrue(customerRepository.count() == 0);
        Customer customerToSave = Customer.builder().firstName("Chris").lastName("kukla").build();
        CustomerDto customerPreparedToUpdate = CustomerDto.builder().firstName("first created").lastName("last created").build();

        Customer savedCustomer = customerRepository.save(customerToSave);
        Assumptions.assumeTrue(customerRepository.count() == 1);

        customerService.deleteCustomer(savedCustomer.getId());
        assertEquals(0, customerRepository.count());
    }

}