package com.cpt.payments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String createPayment(@RequestBody PaymentRequest paymentRequest)
	{

		System.out.println("paymentRequest :"+paymentRequest);
		String json=gson.toJson(paymentRequest);
	
		/*	System.out.println("paymentReqAsJson : "+paymentReqAsJson);
		
		String receivedHmacSignature = hmacSignature;	//pass in header
		boolean isValid=hmacSha256Service.verifyHmac(paymentReqAsJson, receivedHmacSignature);
		
		System.out.println("isVlaid "+isValid);
	*/	
		return ""+json;
	}
	
	
	
	@GetMapping("/{txnRef}/capture")
	public String capturePayment(@PathVariable String txnRef)
	{
		System.out.println("capture Payment request recieved : "+txnRef);
		return "payment capture"+txnRef;
	}
	
	@PostMapping("/{txnRef}") 
	public String getPayment(@PathVariable String txnRef)
	{
		System.out.println("get payment request recieved : "+txnRef);
		return "get payment"+txnRef;
	}
	
}





