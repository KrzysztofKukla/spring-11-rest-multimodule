package pl.kukla.krzys.spring09rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kukla.krzys.spring09rest.domain.Customer;

/**
 * @author Krzysztof Kukla
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
