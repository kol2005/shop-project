package com.biz.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.tour.service.mail.MailSendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/mail")
public class MailController {
	private final MailSendService mService;
	
	@ResponseBody
	@RequestMapping(value = "/send",method=RequestMethod.GET)
	public String sendMain() {
		mService.sendMail();
		return "send ok";
	}
}
