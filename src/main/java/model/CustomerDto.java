package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;
    private String address;
    private String city;
    private String registeredDate;
}
