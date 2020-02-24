package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.CartVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.CartService;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("productVO")
@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {

	private final ProductService proService;
	private final CartService cartService;
	
	@ModelAttribute("productVO")
	public ProductVO newProduct() {
		return new ProductVO();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@ModelAttribute("productVO")ProductVO productVO, Model model) {
		List<ProductVO> proList = proService.selectAll();
		model.addAttribute("B2C_LIST",proList);
		model.addAttribute("BODY","LIST");
		
		//return "admin/main";
		return "users/user_main";
	}
	
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public String detail(@ModelAttribute("productVO")ProductVO productVO ,@PathVariable("id")String strId,Model model) {
		
		long id = Long.valueOf(strId);
		productVO = proService.findById(id);
		
		model.addAttribute("pVO",productVO);
		model.addAttribute("BODY","DETAIL");
		
		//return "admin/main";
		return "users/user_main";
	}
	
	/*
	 * Authentication : 스프링 시큐리티 로그인된 사용자 정보 추출을 위한 인터페이스
	 */
	@ResponseBody
	@RequestMapping(value="/cart",method=RequestMethod.POST)
	public String cart(CartVO cartVO, Authentication authen) {
		
		try {
			// 스프링 시큐리티로 로그인한 사용자 username 추출
			cartVO.setUsername(authen.getPrincipal().toString());	
		} catch (Exception e) {
			return "LOGIN_FAIL";
		}
		cartService.save(cartVO);
		return "OK";
		
		//return "LOGIN USER : " + authen.getPrincipal();
		//return cartVO;
	}
	
	@RequestMapping(value="/cart_view",method=RequestMethod.GET)
	public String cart_view(Authentication authen,Model model) {
		
		model.addAttribute("BODY","CART_VIEW");
		try {
			String username = authen.getPrincipal().toString();
			List<CartVO> cartList = cartService.selectCart(username);
			model.addAttribute("CART_LIST",cartList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "users/user_main";
	}
	

}
