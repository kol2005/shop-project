package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SessionAttributes("userVO")
@RequestMapping(value="/join")
@Controller
public class JoinController {

	private final UserService userService;
	
	@ModelAttribute("userVO")
	public UserDetailsVO newUser() {
		return new UserDetailsVO();
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO")UserDetailsVO userVO,Model model) {
		return "join/join";
	}
	
	@RequestMapping(value="/joinok",method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO")UserDetailsVO userVO,Model model,
			SessionStatus session) {
		int ret = userService.insert(userVO);
		model.addAttribute("JOIN",userVO);
		return "auth/login";
	}
	
	
}










