package com.springproject.base.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.springproject.base.model.HostReplyReviewDto;
import com.springproject.base.model.RoomDto;

public class HostManageDaoImpl implements HostManageDao {
	
	SqlSession sqlSession;
	public static String nameSpace = "com.springproject.base.dao.HostManageDao";

	@Override
	public List<HostReplyReviewDto> reviewListDao(int hSeq) throws Exception {
		return sqlSession.selectList(nameSpace + ".reviewListDao");
	}


	@Override
	public HostReplyReviewDto reviewContentDao(int rvSeq) throws Exception {
		return sqlSession.selectOne(nameSpace + ".reviewContentDao");
	}

	@Override
	public HostReplyReviewDto reviewInfoDao(int rvSeq) throws Exception {
		return sqlSession.selectOne(nameSpace + ".reviewInfoDao");
	}

	@Override
	public void replyReviewDao(String replyContent, int rvSeq, String rvCId, int regSeq, int hSeq) throws Exception {
		sqlSession.insert(nameSpace + ".replyReviewDao");
	}


	@Override
	public void updateReviewGroupDao() throws Exception {
		sqlSession.update(nameSpace + ".updateReviewGroupDao");
		
	}

	@Override
	public List<String> campNameDao(int hSeq) throws Exception {
		return sqlSession.selectList(nameSpace + ".campNameDao");
	}


	
	@Override
	public void backToMainDao(int hSeq) throws Exception {
		sqlSession.delete(nameSpace + ".backToMainDao");
	}
	
	@Override
	public void deleteFacilityDao(int regSeq, int hSeq) throws Exception {
		sqlSession.delete(nameSpace + ".deleteFacilityDao");
	}

	@Override
	public void deleteKeywordDao(int regSeq, int hSeq) throws Exception {
		sqlSession.delete(nameSpace + ".deleteKeywordDao");
	}
	
	@Override
	public void addCampLocationDao(String regDetailaddress, String regSummary, String regName, String regTel,
			String regCategory, int hSeq) throws Exception {
		sqlSession.insert(nameSpace + ".addCampLocationDao");
	}


	@Override
	public int myNewRegseqDao(int hSeq) throws Exception {
		return sqlSession.selectOne(nameSpace + ".myNewRegseqDao");
	}


	@Override
	public void addCampFacilityDao(String fName, int regSeq, int hSeq) throws Exception {
		sqlSession.insert(nameSpace + ".addCampFacilityDao");
	}


	@Override
	public void addCampKeywordDao(String kName, int regSeq, int hSeq) throws Exception {
		sqlSession.insert(nameSpace + ".addCampKeywordDao");
	}


	@Override
	public String showDefaultMapDao(int regSeq) throws Exception {
		return sqlSession.selectOne(nameSpace + ".showDefaultMapDao");
	}


	@Override
	public List<RoomDto> roomsViewDao(int regSeq, int hSeq) throws Exception {
		return sqlSession.selectList(nameSpace + ".roomsViewDao");
	}


	@Override
	public void addRoomsDao(int roNum, int roPrice, int roMax, int regSeq, int hSeq) throws Exception {
		sqlSession.insert(nameSpace + ".addRoomsDao");
	}


	@Override
	public void deleteRoomsDao(int roSeq) throws Exception {
		sqlSession.delete(nameSpace + ".deleteRoomsDao");
	}


	











	

}
