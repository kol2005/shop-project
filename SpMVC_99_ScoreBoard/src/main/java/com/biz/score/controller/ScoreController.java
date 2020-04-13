package com.biz.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.score.domain.ScorePivotVO;
import com.biz.score.domain.ScoreVO;
import com.biz.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
//@RequestMapping(value="/")
@Controller
public class ScoreController {

	private final ScoreService sService;
	
//	@ResponseBody
	@RequestMapping(value="/score",method=RequestMethod.GET)
	public String scorelist(Model model) {
		
		List<ScorePivotVO> scoreList = sService.selectAll();
		model.addAttribute("SCORE_LIST",scoreList);
		
		System.out.println(scoreList);
		
		return "score";
	}
	
	@RequestMapping(value="/score/insert",method=RequestMethod.GET)
	public String insert() {
		return "insert";
	}
	
	@RequestMapping(value="/score/insert",method=RequestMethod.POST)
	public String insert(ScoreVO scoreVO) {
		
//		String s_num = scoreVO.getS_num();
//		if(!s_num.equals("")) {
		int s_num = Integer.valueOf(scoreVO.getS_num());
		if(s_num < 0) {
			System.out.println("업데이트 완료");
			sService.update(scoreVO);
		}else {
			System.out.println("인서트 완료");
			sService.insert(scoreVO);
		}
		return "redirect:/score";
	}
	
	@RequestMapping(value="/score/update",method=RequestMethod.GET)
	public String update(@RequestParam("s_num")String s_num,Model model) {
		
		ScoreVO scoreVO = sService.findById(s_num);
		model.addAttribute("SCORE",scoreVO);
		return "insert";
	}
	
	@RequestMapping(value="/score/update",method=RequestMethod.POST)
	public String update(ScoreVO scoreVO,Model model) {
		sService.update(scoreVO);
		return "redirect:/score";
	}
	
	
	
	
	
}
