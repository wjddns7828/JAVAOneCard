package com.min.edu.data.dao;

public interface IUsersDao {
	
	/*
	 * 회원가입
	 */
	public int createAcc(String id, String pw, String name);
	
	
	/*
	 * 계좌 등록
	 */
	public boolean setAccountNum(String id,String acc_num);
	
	
	/*
	 * 로그인
	 */
	public String login(String id);
	
	
	/*
	 * 돈 가져오기
	 */
	public int getMoney(String id);
	
	
	/*
	 * 돈 충전하기
	 */
	public void chargeMoney(String id, int money);
	
}
