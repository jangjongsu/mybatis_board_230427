package com.jjcompany.boardjj.controller;

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
		
		int checkId = dao.checkId(request.getParameter("checkId"));
		
		if(checkId == 0) {
			dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("memberName", request.getParameter("mname"));
			model.addAttribute("checkIdFiag", "joinOk");
		} else {
			model.addAttribute("checkIdFiag", "1");
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
			session.setAttribute("session", mid);
			model.addAttribute("memberId", mid);
		}
		return "loginOk";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();	// 모든 데이터 삭제
		
		return "login";
	}
}
