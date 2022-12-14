package com.springproject.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.base.model.HostReplyReviewDto;
import com.springproject.base.model.RoomDto;
import com.springproject.base.service.HostManageDaoService;

@Controller
public class HostManageController {	// 옵션 관리하는 컨트롤러
	
	@Autowired
	HostManageDaoService manageDao;
		
	// 후기 리스트 출력
	@RequestMapping("/review_list")
	public String reviewList(HttpSession session, Model model) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		
		// 후기 리스트
		List<HostReplyReviewDto> reviewList = manageDao.reviewListDao(hSeq);
		model.addAttribute("review_list", reviewList);
		
		// 콤보박스
		List<String> nameList = manageDao.campNameDao(hSeq);
		model.addAttribute("regName_list", nameList);
		
		return "host/HostReviewList";
	}

	// 후기 상세 내용 보기
	@RequestMapping("/review_content")
	public String reviewContentView(HttpServletRequest request, Model model) throws Exception {
		int rvSeq = Integer.parseInt(request.getParameter("rvSeq"));
		HostReplyReviewDto content = manageDao.reviewContentDao(rvSeq);
		model.addAttribute("review_content_view", content);
		return "host/HostReviewContentView";
	}
	
	// 후기 답변하기 + 댓글 그룹 업데이트
	@RequestMapping("/reply_review")
	public String replyReview(HttpSession session, HttpServletRequest request) throws Exception {
		String replyContent = request.getParameter("replyContent");
		int rvSeq = Integer.parseInt(request.getParameter("rvSeq"));
		
		String rvCId = manageDao.reviewInfoDao(rvSeq).getRvCId();
		int regSeq = manageDao.reviewInfoDao(rvSeq).getRegcamp_regSeq();
		
		int hSeq = (int)session.getAttribute("HSEQ");
		
		// 답변 내용 insert 먼저
		manageDao.replyReviewDao(replyContent, rvSeq, rvCId, regSeq, hSeq);
		// 댓글 / 대댓글 Group update
		manageDao.updateReviewGroupDao();
		
		return "redirect:/review_list";
	}
	
	// 캠핑장 신규 등록중 취소시 DB에서 정보 완전히 삭제 (delete)하고 메인메뉴로 복귀
	@RequestMapping("/back_to_main")
	public String backToMain(HttpSession session) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		manageDao.backToMainDao(hSeq);
		
		return "redirect:/host_main";
	}
	
	@RequestMapping("/back_to_main_f")
	public String backToMainF(HttpSession session) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		int regSeq = (int)session.getAttribute("REGSEQ");
		
		manageDao.deleteFacilityDao(regSeq, hSeq);
		manageDao.backToMainDao(hSeq);
		
		return "redirect:/host_main";
	}
	
	@RequestMapping("/back_to_main_k")
	public String backToMainK(HttpSession session) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		int regSeq = (int)session.getAttribute("REGSEQ");
		
		manageDao.deleteKeywordDao(regSeq, hSeq);
		manageDao.backToMainDao(hSeq);
		
		return "redirect:/host_main";
	}
	
	
	
	// 캠핑장 추가 페이지 띄우기
	@RequestMapping("/add_camp")
	public String addCamp() throws Exception {
		return "host/AddCamp1";
	}
	
	// 위치, 설명, 이름, 전화번호, 카테고리 등록
	@RequestMapping("/add_camp_location")
	public String addCampLocation(HttpSession session, HttpServletRequest request) throws Exception {
		String regDetailaddress = request.getParameter("regDetailaddress");
		String regSummary = request.getParameter("regSummary");
		String regName = request.getParameter("regName");
		String regTel = request.getParameter("regTel");
		String regCategory = request.getParameter("regCategory");
		int hSeq = (int)session.getAttribute("HSEQ");
		manageDao.addCampLocationDao(regDetailaddress, regSummary, regName, regTel, regCategory, hSeq);
		
		// 정보 등록하며 생성된 캠핑장 번호 가져오기
		int regSeq = manageDao.myNewRegseqDao(hSeq);
		session.setAttribute("REGSEQ", regSeq);
		
		return "host/AddCamp2";
	}
	
	// 편의시설 추가 (중복선택 가능)
	@RequestMapping("/add_camp_facility")
	public String addCampFacility(HttpServletRequest request, HttpSession session) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		int regSeq = (int)session.getAttribute("REGSEQ");
		String[] facilities = request.getParameterValues("facility");
		
		for (int i = 0; i < facilities.length; i++) {
			manageDao.addCampFacilityDao(facilities[i], regSeq, hSeq);
		}
		
		return "host/AddCamp3";
	}

	@RequestMapping("/add_camp_keyword")
	public String addCampKeyword(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		int hSeq = (int)session.getAttribute("HSEQ");
		int regSeq = (int)session.getAttribute("REGSEQ");
		String[] keywords = request.getParameterValues("keys");
		
		for (int i = 0; i < keywords.length; i++) {
			manageDao.addCampKeywordDao(keywords[i], regSeq, hSeq);
		}
		
		return "redirect:show_rough_map";
	}
	
	@RequestMapping("/show_rough_map")
	public String showRoughMap (HttpSession session, Model model) throws Exception {
		int regSeq = (int)session.getAttribute("REGSEQ");
		// 약도 디폴트 이미지 보여주기
		String roughMap = manageDao.showDefaultMapDao(regSeq);
		model.addAttribute("roughMap", roughMap);
		return "host/AddCamp4";	
	}

	@RequestMapping("/rooms_view")
	public String roomsView (HttpSession session, Model model) throws Exception {
		int regSeq = (int)session.getAttribute("REGSEQ");
		int hSeq = (int)session.getAttribute("HSEQ");
		
		List<RoomDto> dto = manageDao.roomsViewDao(regSeq, hSeq);
		model.addAttribute("roomlist", dto);
		
		return "host/AddCamp5";
	}
	
	@RequestMapping("/add_rooms")
	public String addRooms (HttpSession session, HttpServletRequest request, Model model) throws Exception {
		int regSeq = (int)session.getAttribute("REGSEQ");
		int hSeq = (int)session.getAttribute("HSEQ");
		int roNum = Integer.parseInt(request.getParameter("roNum"));
		int roPrice = Integer.parseInt(request.getParameter("roPrice"));
		int roMax = Integer.parseInt(request.getParameter("roMax"));
		
		manageDao.addRoomsDao(roNum, roPrice, roMax, regSeq, hSeq);
		
		return "redirect:rooms_view";
	}
	
	@RequestMapping("/delete_rooms")
	public String deleteRooms (HttpServletRequest request) throws Exception {
		int roSeq = Integer.parseInt(request.getParameter("roSeq"));
		manageDao.deleteRoomsDao(roSeq);
		return "redirect:rooms_view";
	}
	
	
	
	
	
	
	
	
	
	
}
