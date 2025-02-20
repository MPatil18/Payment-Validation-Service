package com.cpt.payments.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class User {
    private String endUserID;
    private String firstname;
    private String lastname;
    private String email;
    private String mobilePhone;

}
