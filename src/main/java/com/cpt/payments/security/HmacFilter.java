package com.cpt.payments.security;

import java.io.IOException;
import java.io.StringReader;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cpt.payments.constant.ErrorCodeEnum;
import com.cpt.payments.exception.ValidationException;
import com.cpt.payments.service.Interfaces.HmacSha256Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component                                                       //side business logic 
public class HmacFilter extends OncePerRequestFilter {

	private Gson gson;
	private HmacSha256Service hmacSha256Service;
	public HmacFilter(Gson gson,HmacSha256Service hmacSha256Service)
	{
		this.gson=gson;
		this.hmacSha256Service=hmacSha256Service;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		String receivedHmacSignature=request.getHeader("HmacSignature"); // api caller will send hmac signature in header
		System.out.println("receivedHmacSignature "+receivedHmacSignature);
		
		if(receivedHmacSignature == null)
		{
			
			System.out.println("HmacFilter : hmac Signature not found");
			throw new ValidationException(ErrorCodeEnum.INVALID_HMAC_SIGNATURE.getErrorCode(),
												ErrorCodeEnum.INVALID_HMAC_SIGNATURE.getErrorMessage(),
													HttpStatus.BAD_REQUEST);
		}	
		
		// String temp=null;temp.length();   to test generic exception
		
		
		WrappedRequest wrappedRequest = new WrappedRequest(request);//due to request body was getting caalled two time 
																	//1.at hmac filter 2.at controller,so we created wrapper class
		String data=request.getRequestURI().toString();
	
	  	if (wrappedRequest.getBody() != null && !wrappedRequest.getBody().isEmpty()) //work for api that does not have json structure
		{
	  			data = data+"|"+getNormalizedJson(wrappedRequest.getBody())    ;  // due to spaces in json structure
		}
	  	
	  	//passing data in hmac as "uri | jsonbody" and hmac convert in to secret key.
	  	System.out.println("data : "+data);
	
		//boolean isValid=true;    
		boolean isValid=hmacSha256Service.verifyHmac(data, receivedHmacSignature);// generate hmac signature of data
																				//verify generated & received signature
		
		if(isValid)
		{
			System.out.println("hmac filter is valid calling next filter in line | hmacSha256Service"+hmacSha256Service);
			
			
			SecurityContext context=SecurityContextHolder.createEmptyContext(); //to now check current request is authenticated  or not
			Authentication authentication =new HmacAuthenticationtoken("Ecom","");//testing we should use our hmac//username password 
			context.setAuthentication(authentication);
			
			SecurityContextHolder.setContext(context);		
			
			
			filterChain.doFilter(wrappedRequest, response);   //request, response
		}
		else
		{
			System.out.println(" HmacFilter not valid : sending 401 ");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	
	
	public String getNormalizedJson(String rawJson) {              
		
        JsonReader reader = new JsonReader(new StringReader(rawJson));
        reader.setLenient(true);  
          
        JsonElement jsonElement = JsonParser.parseReader(reader);
	   
	    return gson.toJson(jsonElement);
	}


}
