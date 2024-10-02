package com.cpt.payments.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class PaymentRequest {
    private User user;
    private Payment payment;

}
