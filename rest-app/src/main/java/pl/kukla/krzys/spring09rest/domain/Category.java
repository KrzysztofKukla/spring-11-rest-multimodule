package pl.kukla.krzys.spring09rest.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Category extends BaseEntity {

    private String name;

    @Builder
    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }

}
