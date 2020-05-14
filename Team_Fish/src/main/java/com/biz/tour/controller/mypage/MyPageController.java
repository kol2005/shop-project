package com.biz.tour.controller.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.tour.domain.member.MemberVO;
import com.biz.tour.service.fileupload.FileUploadToServerService;
import com.biz.tour.service.member.MemberService;
import com.biz.tour.service.mypage.MyPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("memberVO")
@RequestMapping(value = "/mypage")
public class MyPageController {
	private final MyPageService mypageService;
	private final MemberService memberService;
	private final FileUploadToServerService fUploadService;
	
	@ModelAttribute("memberVO")
	public MemberVO makeMemberVO() {
		MemberVO memberVO=new MemberVO();
		return memberVO;
	}
	
	@RequestMapping(value = "/view",method=RequestMethod.GET)
	public String view(HttpSession session,Model model) {
		//로그인 안되있으면 intercept 발동되게 해야함
		String loggedName=(String) session.getAttribute("U_NAME");
		if(loggedName==null || loggedName.isEmpty()) return null;
		
		MemberVO memberVO=memberService.findByUName(loggedName);
		memberVO.setU_password("password");// 보안때문에 VO에 담겨있는 패스워드값 더미용으로 덮어쓰기
		memberVO.setU_repassword("re_password");// 보안때문에 VO에 담겨있는 패스워드값 더미용으로 덮어쓰기
		model.addAttribute("memberVO", memberVO);
		//마이페이지 보여줄 jsp
		return "mypage/mypage";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(HttpSession session,Model model) {
		//로그인 안되있으면 intercept 발동되게 해야함
		String loggedName=(String) session.getAttribute("U_NAME");
		if(loggedName==null || loggedName.isEmpty()) return null;
		
		MemberVO memberVO=memberService.findByUName(loggedName);
		memberVO.setU_password("password");// 보안때문에 VO에 담겨있는 패스워드값 더미용으로 덮어쓰기
		memberVO.setU_repassword("re_password");// 보안때문에 VO에 담겨있는 패스워드값 더미용으로 덮어쓰기
		model.addAttribute("memberVO", memberVO);
		//마이페이지 수정 form 보여줄 jsp
		return "mypage/update";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("memberVO")MemberVO memberVO,MultipartHttpServletRequest uploaded_files) {
		
//		log.debug("##post업데이트 진입");
		//update을 하고나서, update한 VO의 ID값을 받아와야함
//		int ret=memberService.update(memberVO);
		int ret=mypageService.update(memberVO);
		
//		log.debug("##update 마침");
		long fk=memberVO.getU_id();
		
		//update한 VO의 ID값을 파일업로드+DB 업데이트까지 동시에 수행해주는 method에 Id값 전달
		fUploadService.filesUp(uploaded_files, "tbl_members",fk);
//		log.debug("##파일 업로드 마침");
		
		return "redirect:/mypage/view";
	}
	
	@RequestMapping(value = "/resetpassword",method=RequestMethod.GET)
	public String resetPassword() {
		
		//이메일, 유저네임 입력받는 form jsp
		return "mypage/repassword";
	}
	
	@RequestMapping(value = "/resetpassword",method=RequestMethod.POST)
	public String resetPassword(String username,String email) {
//		MemberVO memberVO=memberService.findByUsernameNemail(username,email);
		MemberVO memberVO=mypageService.findByUsernameNemail(username,email);
		
		//비밀번호 1111 로 만듬과 동시에 유저한테 메일도 같이 보내줘야함
		int ret=memberService.resetPassword(memberVO);
		
		//이메일, 유저네임 입력받는 form jsp
		return "redirect:/";
	}
	
	@RequestMapping(value = "/changepassword",method=RequestMethod.GET)
	public String changePassword() {
		// 비밀번호만 변경 입력받는 form jsp
		return "mypage/repassword";
	}
	
	@RequestMapping(value = "/changepassword",method=RequestMethod.POST)
	public String changePassword(MemberVO memberVO) {
		int ret=mypageService.changePassword(memberVO);
		// 비밀번호만 변경 입력받는 form jsp
		return "redirect:/";
	}
	
	
	/*
	 *  ID찾기,비번 재설정 이메일 인증키 체크 완료 후 비번 재설정 메서드
	 *  db에서 이메일로 검색 후 비번만 초기화 하고 vo에 담아서 보내온 vo를 
	 *  re_join.jsp로 보내기 
	 */
	@RequestMapping(value="/re_join",method=RequestMethod.GET)
	public String re_join(MemberVO memberVO, Model model) {
		
		MemberVO re_join = memberService.findByIdresetpass(memberVO);
		
		model.addAttribute("MEMBERVO",re_join);
		
		return "mypage/re_join";
	}
	/*
	 * ID찾기,비번 재설정 이메일 인증키 체크 완료 후 비번 재설정 메서드
	 * re_join.jsp에서 비번 변경값 받아서 비번 변경 
	 */
	@RequestMapping(value="/re_join",method=RequestMethod.POST)
	public String re_join(MemberVO memberVO, Model model,String email) {
			
		int ret = memberService.re_member_join(memberVO);
			
		return "redirect:/";
	}
}
