package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.repository.DeptDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeptService {

	private final DeptDao deptDao;
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptList = deptDao.findAll();
		return deptList;
	}

	public DeptVO save(DeptVO deptVO) {
		DeptVO ret = deptDao.save(deptVO);
		log.debug("거래처정보 : " + ret.toString());
		return ret;
	}

	public DeptVO findById(long id) {
		Optional<DeptVO> deptVO = deptDao.findById(id);
		return deptVO.get();
	}

}
