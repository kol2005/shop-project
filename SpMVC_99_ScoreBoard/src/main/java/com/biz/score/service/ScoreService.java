package com.biz.score.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.score.domain.ScorePivotVO;
import com.biz.score.domain.ScoreVO;
import com.biz.score.persistance.ScoreDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreService {

	private final ScoreDao sDao;

	public List<ScorePivotVO> selectAll() {
		List<ScorePivotVO> scoreList = sDao.selectAll();
		
		return scoreList;
	}

	public int update(ScoreVO scoreVO) {
		int ret = sDao.update(scoreVO);
		return ret;
	}

	public int insert(ScoreVO scoreVO) {
		int ret = sDao.insert(scoreVO);
		return ret;
	}

	public ScoreVO findById(String s_num) {
		return sDao.findById(s_num);
	}
	
}
