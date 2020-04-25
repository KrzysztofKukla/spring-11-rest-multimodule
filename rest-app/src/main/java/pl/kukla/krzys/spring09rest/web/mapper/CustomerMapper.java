package pl.kukla.krzys.spring09rest.web.mapper;

import org.mapstruct.Mapper;
import pl.kukla.krzys.spring09rest.domain.Customer;
import pl.kukla.krzys.spring09rest.web.model.CustomerDto;

/**
 * @author Krzysztof Kukla
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

}
