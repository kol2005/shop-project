package com.biz.tour.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.tour.domain.member.UserDetailsVO;
import com.biz.tour.service.member.UserService;
import com.biz.tour.service.util.PbeEncryptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes("userVO")
@Controller
@RequestMapping(value = "/join")
@RequiredArgsConstructor
@Slf4j
/**
 * Spring Security 기반 회원가입 및 email 인증 프로젝트 메인컨트롤러 
 *@since 2020-04-20
 *@author najongjine
 */
public class JoinController {
	private final UserService userSerive;
	
	@ModelAttribute("userVO")//sessionAtribute과 연동 
	public UserDetailsVO newUser() {
		return new UserDetailsVO();
	}
	
	/**
	 * 회원가입 화면 보여주기
	 *@since 2020-04-20
	 *
	 *sessionAttribute 활용
	 *localhost/sec/join: 회원가입 화면 보이기
	 *join/user: 회원가입 버튼 클릭 후
	 *join/email_ok: email 인증 화면에서 email 보내기 후 
	 * UPDATE: 2020-04-21
	 *localhost/sec/join:회원가입 화면 보이기
	 *join/join_next: 회원가입 버튼 클릭후 DB에 회원정보를 보여준 후 email 인증화면 보이기
	 *join/join_last: email 인증후 이후 처리 
	 */
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {//jsp form과 연동
		return "join/join";
	}
	
	/**
	 *@since 2020-04-21
	 *최초회원 가입 화면에서 username과 password를 입력한 후 회원가입 버튼을 클릭하면
	 *userVO에 데이터를 받아서 sessionAttributes에 설정된 저장소에 저장해두고 이메일 인증 화면 보여주기  
	 */
	@RequestMapping(value = "/join_next",method=RequestMethod.POST)
	public String join_next(@ModelAttribute("userVO") UserDetailsVO userVO) {
		return "join/join_email";
	}
	
	/**
	 *@since 2020-04-21
	 *Email 인증폼에서 Email 보내기 버튼을 클릭했을때 
	 *userVO에 데이터를 받아서(Email) sessionattribute에 저장된 데이터와 통합(merge)하고
	 *DB에 저장한 후 인증정보를 Email로 보내고 인증코드를 입력받는 화면을 다시 보여주기
	 *이땐 JOIn 변수에 EMAIL_OK 문자열을 실어서 보내고
	 *화면에는 인증코드 입력하는 란이 보이도록 설정 
	 */
	@RequestMapping(value = "/join_last",method=RequestMethod.POST)
	public String join_last(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {
		//jsp로 내려보낸 인증코드(token)은 암호화 시킨거고, 메일로 보낸건 암호화 안시킨 인증코드(token)임.
		//인증코드(token)만들면서 이메일도 같이 보냄.
		String email_token=userSerive.insert_getToken(userVO);//요건 암호화 된 인증코드(token)
		model.addAttribute("My_Email_Secret", email_token);
		model.addAttribute("JOIN", "EMAIL_OK");
		model.addAttribute("username", PbeEncryptor.getEncrypt(userVO.getUsername()));
		return "join/join_email";
	}
	
	/**
	 *@since 2020-04-21
	 *이메일 인증폼에서 인증키와 인증값을 받아서 인증처리 
	 */
	@ResponseBody
	@RequestMapping(value = "/email_token_check",method=RequestMethod.POST)
	public String email_token_check(@RequestParam("secret_id") String username,
			@RequestParam("secret_key") String secret_key,@RequestParam("secret_value") String secret_value) {
		boolean bKey=userSerive.email_token_ok(username,secret_key,secret_value);
		//jsp로 내려보낸 인증코드(token)은 암호화 시킨거고, 메일로 보낸건 암호화 안시킨 인증코드(token)임.
		
		if(bKey) return "OK";
		else return "FAIL";
	}
	/**
	 *@since 2020-04-20
	 *회원가입 화면에서 username,pass 입력후 회원가입 버튼 클릭했을때 호출 
	 */
	@RequestMapping(value = "/user",method=RequestMethod.POST)
	public String user(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {//jsp form과 연동
		return "join/join_email";
	}
	
	/**
	 *@since 2020-04-20
	 *회원가입에서 username,password 입력후 email 보내기 화면으로 이동하기 
	 */
	@RequestMapping(value = "/joinok",method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO") UserDetailsVO userVO,SessionStatus session,
			Model model) {//jsp form과 연동
		int ret=userSerive.insert(userVO);
		model.addAttribute("JOIN", "EMAIL_OK");
		//session.setComplete();//sessionattribute clear 시키기
		return "join/join_email";
	}
	
	/**
	 *@since 2020-04-20 
	 *회원가입중 email 보내기 화면
	 *email 보내기 후 다시 재 전송화면으로
	 */
	@RequestMapping(value = "/emailok",method=RequestMethod.GET)
	//여기로 오는 query는 enc String
	public String emailOk(@ModelAttribute("userVO") UserDetailsVO userVO,SessionStatus session,Model model) {
		boolean ret=userSerive.emailok(userVO.getUsername(),userVO.getEmail());
		session.setComplete();
		if(ret) {
			return "redirect:/user/login";
		}else {
			return "join/join_email_fail";
		}
	}
}
