package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.UserVO;
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
	public UserVO newUser() {
		return new UserVO();
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO")UserVO userVO,Model model) {
		model.addAttribute(userVO);
		return "join/join";
	}
	
	@RequestMapping(value="/join_next",method=RequestMethod.POST)
	public String join_next(@ModelAttribute("userVO")UserVO userVO) {
		return "join/join_email";
	}
	
	@RequestMapping(value="/joinok",method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO")UserVO userVO,Model model,
			SessionStatus session) {
		int ret = userService.insert(userVO);
		model.addAttribute("JOIN","EMAIL_OK");
		
		return "auth/login";
	}
	
	
}










