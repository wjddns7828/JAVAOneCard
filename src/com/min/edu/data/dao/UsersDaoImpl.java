package com.min.edu.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.min.edu.data.comm.DataBase;


public final class UsersDaoImpl extends DataBase implements IUsersDao {
	
	@Override
	public int createAcc(String id, String pw, String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		String sql = "INSERT INTO USERS u (ID,PW,NAME)"
				+ "VALUES (?, ?, ?)";
		
			try {
				conn = getConnection();
				System.out.println("로딩중  [■■■    ] ");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);
				stmt.setString(2, pw);
				stmt.setString(3, name);
				System.out.println("로딩중  [■■■■■  ] ");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				stmt.executeUpdate();
				System.out.println("로딩중  [■■■■■■■] ");
				try {
					Thread.sleep(1000);
					System.out.println("#회원가입 완료#");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				n=1;//회원가입 실패	
			}finally {
				closed(rs, stmt, conn);
			}
			
		return n;
	}

	@Override
	public boolean setAccountNum(String id, String acc_num) {

		return false;
	}

	@Override
	public String login(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String n = "";
		// SqlSyntexException 머시기 발생시 쿼리문제 해결해야됨
		String sql = " SELECT PW"
				+" FROM USERS u" 
				+" WHERE ID = ?";
		
		
		try {
			conn = getConnection();
			System.out.println("로딩중  [■■■    ] ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);
			System.out.println("로딩중  [■■■■■  ] ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			rs = stmt.executeQuery();
			System.out.println("로딩중  [■■■■■■■] ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (rs.next()) {
				n = rs.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getOneEmp 실패");
			e.printStackTrace();
		}finally {
			closed(rs, stmt, conn);
		}
		return n;
	}

	@Override
	public int getMoney(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		
		String sql = " SELECT ua.MONEY" 
				+" FROM USERS_ACC ua JOIN USERS u " 
				+" ON ua.ID = u.ID " 
					+" WHERE ua.ID = ? ";
		
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				n = rs.getInt(1);
			}			
		} catch (SQLException e) {
			System.out.println("getOneEmp 실패");
			e.printStackTrace();
		}finally {
			closed(rs, stmt, conn);
		}
		return n;
	}

	@Override
	public void chargeMoney(String id,int money) {
		int n = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = " UPDATE USERS_ACC "
				+ " SET MONEY = ? "
				+ " WHERE ID = ? ";
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,money);
			stmt.setString(2,id);
			n=stmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closed(rs, stmt, conn);
		}
	}
}
