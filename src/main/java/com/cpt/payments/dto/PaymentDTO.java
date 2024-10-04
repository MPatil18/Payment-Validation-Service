package com.cpt.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class PaymentDTO {
    private String currency;
    private String amount;
    private String brandName;
    private String locale;
    private String returnUrl;
    private String cancelUrl;
    private String country;
    private String merchantTxnRef;
    private String paymentMethod;
    private String providerId;
    private String paymentType;

}
