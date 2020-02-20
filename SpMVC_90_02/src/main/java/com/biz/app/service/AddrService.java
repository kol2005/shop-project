package com.biz.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.app.AddrVO;

@Service
public class AddrService {

	public List<AddrVO> addrListAll() {
		List<AddrVO> addrList = new ArrayList<AddrVO>();
		
		AddrVO addrVO = new AddrVO();
		addrVO.setName("홍길동");
		addrVO.setTel("010");
		addrVO.setPost("6001");
		addrVO.setCity("서울");
		addrVO.setAddr("특별시");
		addrList.add(addrVO);
		
		addrVO = new AddrVO();
		addrVO.setName("이몽룡");
		addrVO.setTel("010-111-1321");
		addrVO.setPost("6002");
		addrVO.setCity("대전");
		addrVO.setAddr("광역시");
		addrList.add(addrVO);
		
		addrVO = new AddrVO();
		addrVO.setName("성춘향");
		addrVO.setTel("010-2222-1321");
		addrVO.setPost("6003");
		addrVO.setCity("대구");
		addrVO.setAddr("코로나19");
		addrList.add(addrVO);
		
		return addrList;
		
	}
	
}
