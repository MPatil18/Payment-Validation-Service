package com.cpt.payments.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cpt.payments.constant.ErrorCodeEnum;
import com.cpt.payments.pojo.ErrorResponse;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			System.out.println(" ExceptionHandlerFilter Before doFilter");
			filterChain.doFilter(request, response);
			System.out.println(" ExceptionHandlerFilter After doFilter");
		} catch (ValidationException ex) {
			System.out.println(" validation exception is -> " + ex.getErrorMessage());
			ErrorResponse paymentResponse = new ErrorResponse(ex.getErrorCode(),ex.getErrorMessage());
					
			Gson gson = new Gson();
			response.setStatus(ex.getHttpStatus().value());        		 //400
			response.setContentType("application/json");				
			response.getWriter().write(gson.toJson(paymentResponse));	//error code & error msg to json
			response.getWriter().flush();
			
		} catch (Exception ex) {
		
		System.out.println(" generic exception message is -> " + ex.getMessage());
			ErrorResponse paymentResponse = new ErrorResponse(
													ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
													ErrorCodeEnum.GENERIC_ERROR.getErrorMessage());
			
			System.out.println(" paymentResponse is -> " + paymentResponse);
			Gson gson = new Gson();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(paymentResponse));
			response.getWriter().flush();
		}
	}

}
