package com.biz.shop.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.service.DeptService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("deptVO")
@RequiredArgsConstructor
@RequestMapping(value="/admin/dept")
@Controller
public class DeptController {

	private final DeptService dService;
	
	@ModelAttribute("deptVO")
	public DeptVO newDept() {
		return new DeptVO();
	}
	
	// /admin/dept 로 매핑
	@RequestMapping(value= {"/",""},method=RequestMethod.GET)
	public String input(@ModelAttribute("deptVO")DeptVO deptVO, Model model) {
		this.modelMapping(model);
		deptVO = new DeptVO();
		
		model.addAttribute("deptVO",deptVO);
		return "admin/main";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		this.modelMapping(model);
		return "admin/dept-list";
	}
	
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String input(@Valid @ModelAttribute("deptVO") DeptVO deptVO,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			this.modelMapping(model);
			model.addAttribute("deptVO",deptVO);
			return "admin/main";
		}
		DeptVO ret = dService.save(deptVO);
		return "redirect:/admin/dept";
	}
	
	private void modelMapping(Model model) {

		List<DeptVO> deptList = dService.selectAll();
		
		model.addAttribute("DEPT_LIST",deptList);
		
		model.addAttribute("BODY","DEPT");
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@ModelAttribute("deptVO")DeptVO deptVO,
			@PathVariable("id")String strId,Model model) {
	List<DeptVO> dList = dService.selectAll();
	
	model.addAttribute("DEPT_LIST",dList);
	
	long id = Long.valueOf(strId);
	deptVO = dService.findById(id);
	
	model.addAttribute("deptVO",deptVO);
	model.addAttribute("BODY","DEPT");
	return "admin/main";
	}
	
}
