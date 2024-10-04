package com.cpt.payments.dto;

import com.cpt.payments.pojo.Payment;
import com.cpt.payments.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class PaymentRequestDTO {
    private User user;
    private Payment payment;

}
