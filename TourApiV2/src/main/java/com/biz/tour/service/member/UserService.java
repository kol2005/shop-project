package com.biz.tour.service.member;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.tour.dao.member.AuthoritiesDao;
import com.biz.tour.dao.member.UserDao;
import com.biz.tour.domain.member.AuthorityVO;
import com.biz.tour.domain.member.UserDetailsVO;
import com.biz.tour.service.mail.MailSendService;
import com.biz.tour.service.util.PbeEncryptor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final  AuthoritiesDao authDao;
	private final MailSendService mailService;
	
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao,AuthoritiesDao authDao
			,MailSendService mailService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao=authDao;
		this.mailService=mailService;
		
		String create_user_table=" create table if not exists tbl_users( " + 
				" id bigint  primary key auto_increment, " + 
				" username varchar(50) unique, " + 
				" password varchar(125), " + 
				" enabled boolean default true, " +
				" email varchar(50), " + 
				" phone varchar(20), " + 
				" address varchar(125) " + 
				" ) ";
		String create_auth_table=" create table if not exists authorities( " + 
				" id bigint  primary key auto_increment, " + 
				" username varchar(50), " + 
				" authority varchar(50) " + 
				" ) ";
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
	}

	/**
	 * @since 2020-04-09
	 * @author Jong park
	 * @param username
	 * @param password
	 * @return
	 * 2020-4-10
	 * Map String,String 구조의 VO 데이터를 UserDetailsVO로 변경
	 */
	@Transactional
	public int insert(String username,String password) {
		//회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword=passwordEncoder.encode(password);
		UserDetailsVO userVO=UserDetailsVO.builder().username(username).password(encPassword).build();
		int ret=userDao.insert(userVO);
		//기본권한 수동 설정
		List<AuthorityVO> authList=new ArrayList<AuthorityVO>();
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
		authDao.insert(authList);
		return ret;
	}
	@Transactional
	//@since 2020-04-20, author najongjine
	//새로 작성된 회원가ㅓ입에서 회원가입을 처리할 method
	//email 인증 방식으로 회원가입을 처리할 것이므로
	//userVO를 parameter로 받아서 enabled를 false로 처리하고 role 정보는 업데이트 하지 않는 것으로 처리해 놓는다.
	//이후 email 인증이 오면 enabled와 role 정보를 설정 하도록 한다.
	public int insert(UserDetailsVO userVO) {
		//회원정보에 저장할 준비가 되지만 접근금지 상태가 된다.
		userVO.setEnabled(false);
		userVO.setAuthorities(null);
		//회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword=passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		//boolean bRet=mailService.join_send(userVO);
		String sRet;
		try {
			sRet = mailService.join_send(userVO);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret=userDao.insert(userVO);
		return ret;
	}
	public boolean isExistsUserName(String username) {
		UserDetailsVO userVO=userDao.findByUserName(username);
		//이미 DB에 회원정보(username)가 저장되어있다
		if(userVO!=null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}

	public UserDetailsVO findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Transactional
	public int update(UserDetailsVO userVO,String[] authList) {
		int ret= userDao.update(userVO);
		if(ret>0) {
			//bulk insert를 하기위해 List 하나 만들어주고
			List<AuthorityVO> authCollection=new ArrayList();
			for(String auth:authList) {//authList는 걍 문자열 배열
				if(auth.trim().isEmpty()) continue; //공백문자열이면 아래거 무시
				//유저이름과 권한을 세트로 묶기위해 vo에 세팅 해주고
				AuthorityVO authVO=AuthorityVO.builder().username(userVO.getUsername()).authority(auth).build();
				authCollection.add(authVO);
			}
			//전에 있던내용 싹 지우고
			authDao.delete(userVO.getUsername());
			//bulk insert 시작
			authDao.insert(authCollection);
		}
		return ret;
	}
	
	@Transactional
	public int update(UserDetailsVO userVO) {
		/*
		 * 기본형 변수, 객체 ,배열 모두 변수라고 할수 있다.
		 * 
		 * type형 변수(참조형 변수)
		 * 객체나 배열은 method로 해당 객체나 배열을 전달 하고 method에서 객체나 비열의 일부 요소의 값을 변경하면
		 * 원본의 객체나 배열에 반영이 됨.
		 * 
		 * 기본형 변수(primitive)
		 * int,char,long,double 등등으로 만든 변수는 method로 전달하여 method 내에서 값을 변경 하여도 원본은 절대 변경되지 않음.
		 */
		//Authentication >= Principal
		//업뎃 전 olduserVO 를 수정후 VO 내용으로 세팅해주고
		Authentication oldAuth=SecurityContextHolder.getContext().getAuthentication();
		UserDetailsVO oldUserDetailsVO=(UserDetailsVO) oldAuth.getPrincipal();
		oldUserDetailsVO.setEmail(userVO.getEmail());
		oldUserDetailsVO.setPhone(userVO.getPhone());
		oldUserDetailsVO.setAddress(userVO.getAddress());
		//기본 유저정보 DB에 업뎃 하고
		int ret= userDao.update(oldUserDetailsVO);
		if(ret>0) {
			
			//회원정보 update후, olduservo를 인자로 줘서 Principal에 담겨있는 정보도 새로 갱신
			Authentication newAuth=new UsernamePasswordAuthenticationToken(oldUserDetailsVO,oldAuth.getCredentials(),
					oldAuth.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return ret;
	}

	public boolean checkPassword(String password) {
		UserDetailsVO userVO=(UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return passwordEncoder.matches(password,userVO.getPassword());
	}
	public boolean checkPassword(String username,String inputtedPassword) {
		UserDetailsVO userVO=userDao.findByUserName(username);
		return passwordEncoder.matches(inputtedPassword,userVO.getPassword());
	}
	
	public Collection<GrantedAuthority> getAuthorities(String[] authList){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		for(String auth:authList) {//query로 날라온 권한 목록들
			if(!auth.trim().isEmpty()) {
				//spring security용 auth list로 복사
				SimpleGrantedAuthority sAuth=new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
		}
		return authorities;
	}

	@Transactional
	public List<UserDetailsVO> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}
	@Transactional
	public UserDetailsVO findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}
	@Transactional
	public boolean emailok(String username, String email) {
		String strUserName=PbeEncryptor.getDecrypt(username);
		UserDetailsVO userVO=userDao.findByUserName(strUserName);
		String strEmail=PbeEncryptor.getDecrypt(email);
		if(strEmail.equalsIgnoreCase(userVO.getEmail())) {
			userVO.setEnabled(true);
			userVO.setAccountNonExpired(true);
			userVO.setAccountNonLocked(true);
			userVO.setCredentialsNonExpired(true);
			userDao.update(userVO);
			//기본권한 수동 설정
			List<AuthorityVO> authList=new ArrayList<AuthorityVO>();
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
			authDao.insert(authList);
			return true;
		}
		return false;
	}

	/**
	 *@since 2020-04-21
	 *회원정보를 받아서 DB에 저장하고
	 *회원정보를 활성화 할수 있도록 하기위해 인증정보를 생성한후 controller로 return
	 */
	@Transactional
	public String insert_getToken(UserDetailsVO userVO) {
		// DB에 저장
		userVO.setEnabled(false);
		String encPassword=passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		userDao.insert(userVO);
		
		String email_token=UUID.randomUUID().toString().split("-")[0].toUpperCase();
		String enc_email_token=PbeEncryptor.getEncrypt(email_token);
		//email보내기
		mailService.email_auth(userVO,email_token);
		return enc_email_token;
	}
	@Transactional
	public boolean email_token_ok(String username, String secret_key, String secret_value) {
		boolean bKey=PbeEncryptor.getDecrypt(secret_key).equals(secret_value);
		if(bKey) {
			String strUsername=PbeEncryptor.getDecrypt(username);
			UserDetailsVO userVO=userDao.findByUserName(strUsername);
			userVO.setEnabled(true);
			userDao.update(userVO);
			authDao.delete(userVO.getUsername());
			List<AuthorityVO> authList=new ArrayList<AuthorityVO>();
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
			authDao.insert(authList);
		}
		return bKey;
	}
	@Transactional
	public boolean resetPassword(String username,String inputEmail) {
		UserDetailsVO userVO=userDao.findByUserName(username);
		if(userVO==null) {
			return false;
		}
		if(!userVO.getEmail().equalsIgnoreCase(inputEmail)) {
			return false;
		}
		String tempPass="1111";
		String encPass=passwordEncoder.encode(tempPass);
		userVO.setPassword(encPass);
		int ret=userDao.changePass(userVO);
		if(ret<1) return false;
		String subject="비밀번호를 초기화 시켜드렸습니다";
		String content="초기화된 비밀번호는  "+tempPass+"  입니다.";
				
		mailService.sendMail(userVO.getEmail(), subject, content);
		
		return true;
	}

	public boolean changePass(String username, String inputtedPassword) {
		int ret=0;
		boolean bRet=false;
		
		//입력한 유저가 있나 확인
		UserDetailsVO userVO=userDao.findByUserName(username);
		//없으면 false
		if(userVO==null) return false;
		
		//비번 암호화
		String encPass=passwordEncoder.encode(inputtedPassword);
		// 암호화 비번으로 세팅
		userVO.setPassword(encPass);
		//비번 바꿈
		userDao.changePass(userVO);
		bRet=true;
		
		return bRet;
	}
}
