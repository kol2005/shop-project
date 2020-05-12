package com.biz.tour.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.tour.domain.member.MemberVO;
import com.biz.tour.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("memberVO")
@RequestMapping(value = "/member")
public class MemberController {
	private final MemberService memService;
	
	@ModelAttribute("memberVO")
	public MemberVO makeMemVO() {
		return new MemberVO();
	}
	@RequestMapping(value = "/register",method=RequestMethod.GET)
	public String register(@ModelAttribute("memberVO") MemberVO memberVO,Model model) {
		model.addAttribute("memberVO", memberVO);
		return "member/register";
	}
	
	@RequestMapping(value = "/register",method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("memberVO") MemberVO memberVO,BindingResult result) {
		if(result.hasErrors()) {
			return "member/register";
		}
		if(!memberVO.getU_password().equals(memberVO.getU_repassword())) {
			return null;
		}
		int ret=memService.insert(memberVO);
		return "redirect:/";
	}
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login(@ModelAttribute("memberVO") MemberVO memberVO,Model model) {
		model.addAttribute("memberVO", memberVO);
		return "member/login";
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("memberVO") MemberVO memberVO,BindingResult result,HttpSession session,Model model) {
		if(result.hasErrors()) {
			return "member/login";
		}
		memberVO=memService.checkLogin(memberVO);
		if(memberVO==null) return "member/loginFail";
		session.setAttribute("U_NAME", memberVO.getU_name());
		return "redirect:/";
	}
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("U_NAME");
		return "redirect:/";
	}
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(String u_name,HttpSession session) {
		int ret=memService.delete(u_name);
		return "redirect:/";
	}
}
