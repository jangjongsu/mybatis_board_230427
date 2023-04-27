package com.jjcompany.boardjj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
		
		model.addAttribute("memberName", request.getParameter("mname"));
		
		return "joinOk";
	}
}
