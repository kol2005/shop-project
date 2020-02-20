package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("productVO")
@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {

	private final ProductService proService;
	
	@ModelAttribute("productVO")
	public ProductVO newProduct() {
		return new ProductVO();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@ModelAttribute("productVO")ProductVO productVO, Model model) {
		List<ProductVO> proList = proService.selectAll();
		model.addAttribute("PRO_LIST",proList);
		model.addAttribute("BODY","LIST");
		
		return "admin/main";
	}
	
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public String detail(@ModelAttribute("productVO")ProductVO productVO ,@PathVariable("id")String id,Model model) {
		
		long lid = Long.valueOf(id);
		
		productVO = proService.findById(lid);
		
		model.addAttribute("proUserVO",productVO);
		model.addAttribute("BODY","DETAIL");
		
		return "admin/main";
	}
	

}
