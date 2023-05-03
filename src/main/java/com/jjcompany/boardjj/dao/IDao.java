package com.jjcompany.boardjj.dao;

import java.util.ArrayList;

import com.jjcompany.boardjj.dto.FbMemberDto;
import com.jjcompany.boardjj.dto.FreeBoardDto;

public interface IDao {
	
	//맴버관련
	public void joinMemberDao(String mid, String mpw, String mname, String memail ) ;
		
	public int checkId(String mid);
	
	public int checkIdPwDao(String mid, String mpw);
	
	//게시판 관련
	public FbMemberDto getMemberInfo(String mid); //아이디로 검색하여 회원정보가져오기
	public void writeDao(String mid, String mname, String ftitle, String fcontent);
	public ArrayList<FreeBoardDto> listDao();
	public int totalBoardDao(); 
	public FreeBoardDto contentViewDao(String fnum);
	public void deleteDao(String fnum);
	
	
}
