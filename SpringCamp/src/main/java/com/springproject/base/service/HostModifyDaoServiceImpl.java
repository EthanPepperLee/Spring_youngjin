package com.springproject.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.base.dao.HostModifyDao;
import com.springproject.base.model.FacilityDto;
import com.springproject.base.model.KeywordDto;
import com.springproject.base.model.RegcampDto;
import com.springproject.base.model.RoomDto;

@Service
public class HostModifyDaoServiceImpl implements HostModifyDaoService {
	
	@Autowired
	HostModifyDao dao;

	@Override
	public String checkMyCampDao(int hSeq, int regSeq) throws Exception {
		return dao.checkMyCampDao(hSeq, regSeq);
	}

	@Override
	public RegcampDto myCampInfoDao(int hSeq, int regSeq) throws Exception {
		return dao.myCampInfoDao(hSeq, regSeq);
	}

	@Override
	public List<KeywordDto> myKeywordInfoDao(int hSeq, int regSeq) throws Exception {
		return dao.myKeywordInfoDao(hSeq, regSeq);
	}

	@Override
	public List<FacilityDto> myFacilityInfoDao(int hSeq, int regSeq) throws Exception {
		return dao.myFacilityInfoDao(hSeq, regSeq);
	}
	
	@Override
	public List<RoomDto> myRoomInfoDao(int hSeq, int regSeq) throws Exception {
		return dao.myRoomInfoDao(hSeq, regSeq);
	}

	@Override
	public RegcampDto checkRemainingBookDao(int hSeq, int regSeq) throws Exception {
		return dao.checkRemainingBookDao(hSeq, regSeq);
	}

	@Override
	public void campIsDeletedDao(int hSeq, int regSeq) throws Exception {
		dao.campIsDeletedDao(hSeq, regSeq);
	}
	
	
}
