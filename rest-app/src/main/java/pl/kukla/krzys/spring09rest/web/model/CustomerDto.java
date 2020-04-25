package pl.kukla.krzys.spring09rest.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    @ApiModelProperty(value = "This is the first name", required = true)
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;

}
