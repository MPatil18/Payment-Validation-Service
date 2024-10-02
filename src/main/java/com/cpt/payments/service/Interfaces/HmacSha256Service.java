package com.cpt.payments.service.Interfaces;

public interface HmacSha256Service {
	public String calculateHmac(String data);
	public boolean verifyHmac(String data,String recievedHmac);
}
