package pl.kukla.krzys.spring09rest.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;

    @Transient //will be not persistent in Customer table
    private String customerUrl;

    @Builder
    public Customer(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
