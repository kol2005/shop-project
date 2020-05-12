package com.biz.shop.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/user")
public class UserController {

	private final UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "auth/login";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO")UserDetailsVO userVO,Model model) {
		model.addAttribute("userVO",userVO);
		return "join/join";
	}
	
	@ResponseBody
	@RequestMapping(value="/join",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String join(String username, String password) {
		log.debug("아이디 {} , 비번 {} " , username,password);
		
		userService.insert(username,password);
		
		return String.format("아이디 : %s, 비번 : %s",username,password);
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck",method=RequestMethod.GET)
	public String idcheck(String username) {
		boolean ret = userService.isExistsUserName(username);
		if(ret) {
			return "Exists".toUpperCase();
		}
		return "NonExists".toUpperCase();
	}
	
	@ResponseBody
	@RequestMapping(value="/password",method = RequestMethod.POST)
	public String password(String password) {
		boolean ret = userService.check_password(password);
		if(ret)return "PASS_OK";
		
		return "PASS_FAIL";
	}
	
	@ResponseBody
	@RequestMapping(value="",method=RequestMethod.GET)
	public String user() {
		return "user HOME";
	}
	
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public String mypage(Model model, Principal principal) {
		
		UsernamePasswordAuthenticationToken upa = (UsernamePasswordAuthenticationToken) principal;
		UserDetailsVO userVO = (UserDetailsVO) upa.getPrincipal();
		userVO.setAuthorities(upa.getAuthorities());
		
		model.addAttribute("userVO",userVO);
		
		model.addAttribute("PRINCIPAL",principal);
		
		return "auth/mypage";
	}
	
	@RequestMapping(value="/mypage",method=RequestMethod.POST)
	public String mypage(UserDetailsVO userVO,Model model,Principal principal) {
		int ret = userService.update(userVO);
		return "redirect:/user/mypage";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(String username,Model model,Principal principal) {
		UserDetailsVO userVO = userService.update(username);
		return "redirect:/";
	}
	
}















