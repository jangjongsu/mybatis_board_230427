package com.jjcompany.boardjj.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jjcompany.boardjj.dao.IDao;
import com.jjcompany.boardjj.dto.FbMemberDto;
import com.jjcompany.boardjj.dto.FreeBoardDto;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlsession; //sqlSession 빈이 컨테이너에서 자동 주입
	
	
	@RequestMapping(value="/joinMember")
	public String joinMember() {
		
		return "joinMember";
	}
	
	@RequestMapping(value="/joinOk", method = RequestMethod.POST)
	public String joinOk(HttpServletRequest request, Model model) {
			
		IDao dao = sqlsession.getMapper(IDao.class);
		
		int checkId = dao.checkId(request.getParameter("mid"));
		
		if(checkId == 0) {
			dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("memberName", request.getParameter("mname"));
			model.addAttribute("checkIdFlag", "joinOk");
		} else {
			model.addAttribute("checkIdFlag", "1");
		}
		
		return "joinOk";
	}
	
	@RequestMapping(value="/checkId")
	public String checkId(HttpServletRequest request, Model model) {
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		int checkIdFlag =dao.checkId(request.getParameter("mid"));
		model.addAttribute("checkIdFlag",checkIdFlag);
		
		
		return "joinOk";
	}
	@RequestMapping(value="/login")
	public String login() {
		
		return "login";
	}
	@RequestMapping(value="/loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		int checkIdFlag = dao.checkId(mid);
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		int checkIdPwFlag = dao.checkIdPwDao(mid, mpw);
		model.addAttribute("checkIdPwFlag", checkIdPwFlag);
		
		if(checkIdPwFlag==1) { //로그인 성공
			// HttpSession session = request.getSession();//컨트롤러에서 세션가져오기
			session.setAttribute("sessionId", mid);
			model.addAttribute("memberId", mid);
		}
		return "loginOk";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();	// 모든 데이터 삭제
		
		return "login";
	}
	@RequestMapping(value="/writeForm")
	public String writeForm(HttpSession session, Model model) {
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		String sid= (String)session.getAttribute("sessionId");
		
		FbMemberDto dto =dao.getMemberInfo(sid);
		
		if(sid == null) {
			return "redirect:login";
		}else {
			model.addAttribute("memberDto", dto);
			
			return "writeForm";
			
		}
		
	}
	@RequestMapping(value="/write")
	public String write(HttpServletRequest request) {
		
		String fid = request.getParameter("mid");
		String fname = request.getParameter("mname");
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		dao.writeDao(fid, fname, ftitle, fcontent);
		
		return "redirect:list";
	}
	@RequestMapping(value="/list")
	public String list(Model model) {
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		int total = dao.totalBoardDao();
		
		ArrayList<FreeBoardDto> dtos = dao.listDao();
		
		model.addAttribute("list", dtos);
		model.addAttribute("total", total);
		
		return "list";
	}
	@RequestMapping(value="/content_view")
	public String content_view(Model model, HttpServletRequest request, HttpSession session) {
		
		String fnum = request.getParameter("fnum");
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		dao.upHitDao(fnum);
		
		FreeBoardDto dto = dao.contentViewDao(fnum);
		
		
		
		model.addAttribute("content", dto);
		
		
		String sId = (String) session.getAttribute("sessionId");
		
		
		if(sId == null) {//로그인하지 않은 경우
			model.addAttribute("delCheck", "0");
		} else if(sId.equals(dto.getFid())) { //로그인한 아이디와 글쓴아이디가 일치
			model.addAttribute("delCheck", "1");
		} else { //로그인한 아이디와 글쓴아이디가 일치하지 않은 경우
			model.addAttribute("delCheck", "0");
		}
		
		
		return "contentView";
	}
	@RequestMapping(value="/delete")
	public String logout(HttpServletRequest request) {
		
		String fnum = request.getParameter("fnum");
		
		IDao dao = sqlsession.getMapper(IDao.class);
		
		dao.deleteDao(fnum);
		
		
		
		return "redirect:list";
	}
}
