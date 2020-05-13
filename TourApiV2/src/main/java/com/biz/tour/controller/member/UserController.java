package com.biz.tour.controller.member;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.tour.domain.member.UserDetailsVO;
import com.biz.tour.service.auth.UserDetailsServiceImp;
import com.biz.tour.service.member.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserDetailsServiceImp userDService;
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value = "/join",method=RequestMethod.GET)
	public String join() {
		return "auth/join";
	}
	
	@ResponseBody
	@RequestMapping(value = "/join",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String join(String username, String password) {
		int ret=userService.insert(username, password);
		//return "redirect:/";
		return String.format("아이디: %s, 비번: %s", username,password);
	}

	@ResponseBody
	@RequestMapping(value = "/idcheck",method=RequestMethod.GET)
	public String idcheck(String username) {
		boolean ret=userService.isExistsUserName(username);
		if(ret) {
			return "EXISTS";
		}
		return "NONEXISTS";
	}
	@ResponseBody
	@RequestMapping(value = "/password",method=RequestMethod.POST)
	public String password(String password) {
		boolean ret=userService.checkPassword(password);
		if(ret) return "PASS_OK";
		return "PASS_FAIL";
	}
	@ResponseBody
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String user() {
		return "user HOME";
	}
	
	@Secured(value = {"ROLE_ADMIN","ADMIN","ROLE_USER","USER"})
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Model model) {

		// 로그인한 사용자 정보
		UserDetailsVO userVO 
			= (UserDetailsVO) SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getPrincipal();

		// 권한(ROLE) 리스트 추출하여 userVO에 setting
		userVO.setAuthorities(
				SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities());

		model.addAttribute("userVO", userVO);
		return "auth/mypage";
	}
	@RequestMapping(value = "/mypage2",method=RequestMethod.GET)
	public String mypage(Principal principal,Model model) {
		UsernamePasswordAuthenticationToken upa=(UsernamePasswordAuthenticationToken) principal;
		UserDetailsVO userVO=(UserDetailsVO) upa.getPrincipal();
		//UserDetailsVO userVO=(UserDetailsVO) userDService.loadUserByUsername(principal.getName());
		userVO.setAuthorities(upa.getAuthorities());
		model.addAttribute("userVO", userVO);
		return "auth/mypage";
	}
	
	@RequestMapping(value = "/mypage",method=RequestMethod.POST)
	public String mypage(UserDetailsVO userVO,String[] auth,Principal principal,Model model) {
		int ret=userService.update(userVO);
		return "redirect:/user/mypage";
	}

}
