package com.biz.sec.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.service.UserService;
import com.biz.sec.utils.PBEEncryptor;

import lombok.RequiredArgsConstructor;

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
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {
		
		return "join/join";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String user(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {
		
		return "join/join_email";
	}
	
	@ResponseBody
	@RequestMapping(value="/joinok",method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO") UserDetailsVO userVO,Model model,
						SessionStatus session) {
		
		int ret = userService.insert(userVO);
//		String ret = userService.insert(userVO);
		
		model.addAttribute("JOIN","EMAIL_OK");
		
		// sessionAttribute에 저장된 session 값을 clear 시키기
//		session.setComplete();
		
		return "join/join_email";
//		return ret;
	}
	
//	@ResponseBody
	@RequestMapping(value="/emailok",method=RequestMethod.GET)
	public String emailOk(@ModelAttribute("userVO")UserDetailsVO userVO,
			SessionStatus session,Model model) {
		
		boolean ret = userService.emailok(userVO.getUsername(),userVO.getEmail());
		
		session.setComplete();
		if(ret) {
			return "redirect:/user/login";
		}else {
//			userVO = userService.findByUserName(PBEEncryptor.getDecrypt(userVO.getUsername()));
//			model.addAttribute("JOIN","EMAIL_FAIL");
			
			return "join/join_email_fail";
		}
//		return PBEEncryptor.getDecrypt(username) + PBEEncryptor.getDecrypt(email);
	}
	
	
	
	
	
	
	
	
	
	
	
}
