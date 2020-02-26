package com.biz.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.service.BBsService;

@Controller
public class BBsController {

	@Autowired
	private BBsService bbsService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		bbsService.selectAll();
		return "bbs_list";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		return "bbs_write";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(BBsVO bbsVO) {
		bbsService.insert(bbsVO);
		return null;
	}
	
	public String update(BBsVO bbsVO) {
		bbsService.update(bbsVO);
		return null;
	}
	
	public String delete(String strId) {
		bbsService.delete(Long.valueOf(strId));
		return null;
	}
	
}
