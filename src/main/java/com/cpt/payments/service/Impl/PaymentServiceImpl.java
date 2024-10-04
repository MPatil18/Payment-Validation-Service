package com.cpt.payments.service.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cpt.payments.constant.ErrorCodeEnum;
import com.cpt.payments.dto.CreatePaymentResDTO;
import com.cpt.payments.dto.PaymentRequestDTO;
import com.cpt.payments.exception.ValidationException;
import com.cpt.payments.service.Interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public CreatePaymentResDTO createPayment(PaymentRequestDTO paymentRequestDTO) {

		if(!paymentRequestDTO.getPayment().getProviderId().equals("PAYPAL"))
		{
			System.out.println("invalid user");

			throw new ValidationException(ErrorCodeEnum.INVALID_PROVIDER.getErrorCode(),
											ErrorCodeEnum.INVALID_PROVIDER.getErrorMessage(),
											HttpStatus.BAD_REQUEST);
			
		}
		else
		{
			System.out.println("valid user");
		}
		
		CreatePaymentResDTO createPaymentResDTO= new CreatePaymentResDTO(); 
		createPaymentResDTO.setTxnRef("txnref");
		createPaymentResDTO.setProviderRef("providerRef");
		createPaymentResDTO.setRedirectUrl("redirectUrl");
		
		return createPaymentResDTO;
	}

}
