package com.cpt.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class UserDTO {
    private String endUserID;
    private String firstname;
    private String lastname;
    private String email;
    private String mobilePhone;

}
