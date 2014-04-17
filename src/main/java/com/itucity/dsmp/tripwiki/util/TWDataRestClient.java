package com.itucity.dsmp.tripwiki.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TWDataRestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://www.itangogo.com:8080/twdata/data/collection/list";

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("timestamp", String.valueOf(System.currentTimeMillis()));
		form.add("appkey", "tripwiki");
		String signature = "";
		form.add("signature", signature);
		
		String result = restTemplate.getForObject(url, String.class, form);
		
		System.out.println(result);
		
	}

}
