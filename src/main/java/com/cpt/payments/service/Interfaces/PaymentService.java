package com.cpt.payments.service.Interfaces;

import com.cpt.payments.dto.CreatePaymentResDTO;
import com.cpt.payments.dto.PaymentRequestDTO;

public interface PaymentService {

		public CreatePaymentResDTO createPayment(PaymentRequestDTO paymentRequestDTO);
}
