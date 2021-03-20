package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetIp {
	
	
	@RequestMapping("/getUserIP")
	public String getUserIP(HttpServletRequest request){
		String remoteAddr = request.getRemoteAddr();
		return ">>>>>>>你的ip是-------> "+remoteAddr;
	}
}
