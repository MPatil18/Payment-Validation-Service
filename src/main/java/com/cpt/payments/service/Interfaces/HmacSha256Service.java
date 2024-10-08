package com.cpt.payments.service.Interfaces;

public interface HmacSha256Service {
	public String calculateHmac(String data);          // json input
	public boolean verifyHmac(String data,String recievedHmac);      
}
