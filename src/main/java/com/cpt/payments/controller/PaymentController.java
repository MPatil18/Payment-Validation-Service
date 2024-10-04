package com.cpt.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.dto.CreatePaymentResDTO;
import com.cpt.payments.dto.PaymentRequestDTO;
import com.cpt.payments.pojo.CreatePaymentRes;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.service.Interfaces.HmacSha256Service;
import com.cpt.payments.service.Interfaces.PaymentService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private PaymentService paymentService;
	private ModelMapper modelMapper;
	
	public PaymentController(PaymentService paymentService,ModelMapper modelMapper,HmacSha256Service hmacSha256Service)
	{
		this.paymentService=paymentService;
		this.modelMapper=modelMapper;
	}

	@PostMapping
	public ResponseEntity<CreatePaymentRes> createPayment(@RequestBody PaymentRequest paymentRequest)
	{

		System.out.println("paymentRequest :"+paymentRequest);
		
		PaymentRequestDTO paymentRequestDTO=modelMapper.map(paymentRequest, PaymentRequestDTO.class);
		System.out.println("PaymentRequest   as   DTO :"+paymentRequestDTO);  
		
		CreatePaymentResDTO createPaymentResDTO=paymentService.createPayment(paymentRequestDTO);
		
		
		
		CreatePaymentRes createPaymentRes=modelMapper.map(createPaymentResDTO, CreatePaymentRes.class);
		
		ResponseEntity<CreatePaymentRes> responseEntity= 
				new ResponseEntity<CreatePaymentRes>( createPaymentRes, HttpStatus.CREATED);	//customize http status
	
		return responseEntity;
	}
	
	
	
	@PostMapping("/{txnRef}/capture")
	public String capturePayment(@PathVariable String txnRef)
	{
		System.out.println("capture Payment request recieved : "+txnRef);
		return "payment capture"+txnRef;
	}
	
	@GetMapping("/{txnRef}") 
	public String getPayment(@PathVariable String txnRef)
	{
		System.out.println("get payment request recieved : "+txnRef);
		return "get payment"+txnRef;
	}
	
}





