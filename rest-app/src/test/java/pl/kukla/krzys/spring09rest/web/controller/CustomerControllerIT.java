package pl.kukla.krzys.spring09rest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.kukla.krzys.spring09rest.service.CustomerService;
import pl.kukla.krzys.spring09rest.web.model.CustomerDto;

/**
 * @author Krzysztof Kukla
 */
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerIT {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    CustomerDto cus1;
    CustomerDto cus2;

//    @BeforeEach
//    void setUp() {
//        cus1 = CustomerDto.builder().firstName("first").lastName("first").build();
//        cus2 = CustomerDto.builder().firstName("sec").lastName("sec lastName").build();
//        List<CustomerDto> customers = Arrays.asList(cus1, cus2);
//        BDDMockito.when(customerService.findAll()).thenReturn(customers);
//    }
//
//    @Test
//    void getAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL))
//            .andExpect(status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
//    }
//
//    @Test
//    void createCustomer() throws Exception {
//        CustomerDto preparedToSave = CustomerDto.builder().firstName("first to save").lastName("last to save").build();
//        String customerString = new ObjectMapper().writeValueAsString(preparedToSave);
//
//        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL)
//            .content(customerString)
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated());
//
//    }

}