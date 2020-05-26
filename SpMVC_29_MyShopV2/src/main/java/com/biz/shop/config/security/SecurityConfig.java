package com.biz.shop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	/*
	 * ROLE에 따라 접근통제를 설정하는 method
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		
		// 한글 Encoding을 수행할 필터 선언
		CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
		charFilter.setEncoding("UTF-8");
		charFilter.setForceEncoding(true);
		
		// spring security filter를 통해서 설정을 수행하기 전
		// 한글 Encoding을 먼저 수행하라
		// security에서 POST 전송을 했을때 한글 깨짐을 방지
		http.addFilterBefore(charFilter, CsrfFilter.class);
		
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/mypage").hasAnyRole("ADMIN","USER")
		.antMatchers("/user/**").permitAll()
		.antMatchers("/**").permitAll();
//		.anyRequest().authenticated();// 위에 나열한 것 외에는 모두 인증 필요
		
		http.formLogin()
		.loginProcessingUrl("/login") // security에서 지원하는 login URL
		.loginPage("/user/login") // login form
		.usernameParameter("username")
		.passwordParameter("password");
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.authenticationProvider(authProvider);
	}

	/*
	 * security 필터를 거치지 않고 요청에 응답할 요소들
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(web);
		
		web.ignoring().antMatchers("/resources/**");
		
	}
	
	/*
	 * 암호화된 password를 비교(match)하기 위한 클래스 생성
	 * Authen...Provider에 주입되는 객체
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	
	
}









