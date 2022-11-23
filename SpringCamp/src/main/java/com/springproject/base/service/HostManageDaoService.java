package com.springproject.base.service;

import java.util.List;

import com.springproject.base.model.HostReplyReviewDto;

public interface HostManageDaoService {
	
	public List<HostReplyReviewDto> reviewListDao(int hSeq) throws Exception;
	
	public HostReplyReviewDto reviewContentDao (int rvSeq) throws Exception;
	
	public HostReplyReviewDto reviewInfoDao (int rvSeq) throws Exception;
	
	public void replyReviewDao(String replyContent, int rvSeq, String rvCId, int regSeq, int hSeq) throws Exception;

	public void updateReviewGroupDao() throws Exception;
	
	public List<String> campNameDao(int hSeq) throws Exception;

	public void addCampLocationDao(String regDetailaddress, String regSummary, String regName, String regTel, String regCategory, int hSeq) throws Exception;

	public int myNewRegseqDao(int hSeq) throws Exception;
	
	public void addCampFacilityDao(String fName, int regSeq, int hSeq) throws Exception;
	
	public void addCampKeywordDao(String kName, int regSeq, int hSeq) throws Exception;
	
	public String showDefaultMapDao(int regSeq) throws Exception;
	



}
