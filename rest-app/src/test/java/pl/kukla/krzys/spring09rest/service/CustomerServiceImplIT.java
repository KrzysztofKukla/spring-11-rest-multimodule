package pl.kukla.krzys.spring09rest.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.kukla.krzys.spring09rest.domain.CustomerDto;
import pl.kukla.krzys.spring09rest.exception.CustomerNotFoundException;
import pl.kukla.krzys.spring09rest.repository.CustomerRepository;
import pl.kukla.krzys.spring09rest.web.mapper.CustomerMapperImpl;

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

        CustomerDto customerDto = createCustomerDto("first created","last created");
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
        CustomerDto customerToSave = createCustomerDto("Chris","kukla");
        CustomerDto customerPreparedToUpdate = createCustomerDto("first created","last created");

        CustomerDto savedCustomer = customerService.createCustomer(customerToSave);
        Assumptions.assumeTrue(customerRepository.count() == 1);
        Long id = customerRepository.findAll().stream().findFirst()
            .orElseThrow(() -> new CustomerNotFoundException("Cannot find any Customer")).getId();

        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerPreparedToUpdate);

        assertEquals(1, customerRepository.count());
        assertAll(
            () -> assertEquals(customerPreparedToUpdate.getFirstName(), updatedCustomer.getFirstName()),
            () -> assertEquals(customerPreparedToUpdate.getLastName(), updatedCustomer.getLastName())
        );
    }

    @Test
    void deleteCustomer() throws Exception {
        Assumptions.assumeTrue(customerRepository.count() == 0);
        CustomerDto customerToSave = createCustomerDto("Chris", "kukla");
        CustomerDto customerPreparedToUpdate = createCustomerDto("first created","last created");

        CustomerDto savedCustomer = customerService.createCustomer(customerToSave);
        Assumptions.assumeTrue(customerRepository.count() == 1);

        Long id = customerRepository.findAll().stream().findFirst()
            .orElseThrow(() -> new CustomerNotFoundException("Cannot find any Customer")).getId();
        customerService.deleteCustomer(id);
        assertEquals(0, customerRepository.count());
    }

    private CustomerDto createCustomerDto(String firstName, String lastName) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(firstName);
        customerDto.setLastName(lastName);
        return customerDto;
    }

}