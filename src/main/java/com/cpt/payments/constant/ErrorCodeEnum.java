package com.cpt.payments.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCodeEnum {

	INVALID_PROVIDER(10001, "Invalid provider Id , currenty we are processing only Paypal"),
	INVALID_PAYMENT_METHOD(10002, "Invalid PAYMENT methods , currenty only APMs are supported");
	
	private final int errorCode;
	private final String errorMessage;
	
    ErrorCodeEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
