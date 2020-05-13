package com.biz.tour.service.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.tour.dao.member.MemberDao;
import com.biz.tour.domain.member.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberDao memDao;
	
	public MemberVO findByUName(String u_name) {
		return memDao.findByUName(u_name);
	}
	public int insert(MemberVO memberVO) {
		if(!memberVO.getU_password().equals(memberVO.getU_repassword())){
			return -1;
		}
		String encPass=passwordEncoder.encode(memberVO.getU_password());
		memberVO.setU_password(encPass);
		return memDao.insert(memberVO);
	}
	public int delete(String u_name) {
		return memDao.delete(u_name);
	}
	public MemberVO checkLogin(MemberVO inputtedMemberVO) {
		// TODO Auto-generated method stub
		MemberVO existingMemberVO=memDao.findByUName(inputtedMemberVO.getU_name());
		if(existingMemberVO==null) return null;
		if(!passwordEncoder.matches(inputtedMemberVO.getU_password(), existingMemberVO.getU_password())) {
			return null;
		}
		
		return existingMemberVO;
	}
}
