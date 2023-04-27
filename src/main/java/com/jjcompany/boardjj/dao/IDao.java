package com.jjcompany.boardjj.dao;

public interface IDao {
	
	public void joinMemberDao(String mid, String mpw, String mname, String memail ) ;
		
	public int checkId(String mid);
	
	public int checkIdPwDao(String mid, String mpw);
	
}
