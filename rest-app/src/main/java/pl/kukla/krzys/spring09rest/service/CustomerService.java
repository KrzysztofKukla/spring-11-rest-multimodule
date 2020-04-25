package pl.kukla.krzys.spring09rest.service;

import pl.kukla.krzys.spring09rest.web.model.CustomerDto;

import java.util.List;

/**
 * @author Krzysztof Kukla
 */
public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

}
