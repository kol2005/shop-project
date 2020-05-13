package com.biz.tour.controller.member;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.tour.service.member.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/password")
public class PasswordController {
	private final UserService userService;
	
	@RequestMapping(value = "/resetPass",method=RequestMethod.GET)
	public String resetPass(Model model) {
		return "password/resetPass";
	}
	
	/**
	 * 비밀번호를 초기화 시켜서 이메일로 초기화된 비밀번호를 발송해줌.
	 * @param username
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/resetPass",method=RequestMethod.POST)
	public String resetPass(String username,String email) {
		boolean bRet=userService.resetPassword(username, email);
		return "redirect:/";
	}
	
	@Secured(value = {"ROLE_ADMIN","ROLE_USER","USER"})
	@RequestMapping(value = "/changePass",method = RequestMethod.GET)
	public String changePass() {
		return "password/changePass";
	}
	
	@RequestMapping(value = "/changePass",method = RequestMethod.POST)
	public String changePass(String username,String newPassword,Model model) {
		boolean bRet=false;
		bRet=userService.changePass(username,newPassword);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkPass",method=RequestMethod.POST)
	public boolean checkPass(String username,String password) {
		boolean bRet=false;
		bRet=userService.checkPassword(username, password);
		return bRet;
	}
	
}
