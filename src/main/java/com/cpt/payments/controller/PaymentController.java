package com.cpt.payments.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.service.Interfaces.HmacSha256Service;
import com.google.gson.Gson;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	HmacSha256Service hmacSha256Service;
	Gson gson;
	
	public PaymentController(HmacSha256Service hmacSha256Service,Gson gson)
	{
		this.hmacSha256Service=hmacSha256Service;
		this.gson=gson;
	}

	@PostMapping
	public String createPayment(@RequestBody PaymentRequest paymentRequest ,@RequestHeader ("HmacSignature")String hmacSignature)
	{
		System.out.println("Request recieved from user paymentRequest "+paymentRequest+"| HmacSignature "+hmacSignature);
		
		String paymentReqAsJson=gson.toJson(paymentRequest);
		System.out.println("paymentReqAsJson : "+paymentReqAsJson);
		
		String receivedHmacSignature = hmacSignature;	//pass in header
		boolean isValid=hmacSha256Service.verifyHmac(paymentReqAsJson, receivedHmacSignature);
		
		System.out.println("isVlaid "+isValid);
		
		return "payment created | valid "+isValid;
	}
}





