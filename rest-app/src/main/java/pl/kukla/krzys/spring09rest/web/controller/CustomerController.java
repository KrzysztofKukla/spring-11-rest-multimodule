package pl.kukla.krzys.spring09rest.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kukla.krzys.spring09rest.domain.Customer;
import pl.kukla.krzys.spring09rest.service.CustomerService;
import pl.kukla.krzys.spring09rest.web.model.CustomerDto;
import pl.kukla.krzys.spring09rest.web.model.CustomerListDto;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Api(description = "This is description for CustomerController in Swagger")
@RestController
@RequestMapping(CustomerController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @ApiOperation(value = "This method gets list of Customers in Swagger", notes = "Details information in Swagger")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getAll() {
        List<CustomerDto> customers = customerService.findAll();
        return CustomerListDto.builder().customers(customers).build();
    }

    //Content-Type - on request that is going in
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        return customerService.createCustomer(customerDto);
//        return buildAndReturnResponseEntity(savedCustomer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
//        return buildAndReturnResponseEntity(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    private ResponseEntity<CustomerDto> buildAndReturnResponseEntity(Customer savedCustomer) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(BASE_URL + "/" + savedCustomer.getId()).build();
        return ResponseEntity.created(uriComponents.toUri())
            .build();
    }

}
