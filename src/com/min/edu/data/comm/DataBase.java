package com.min.edu.data.comm;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataBase {

	public DataBase() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}//DataBase
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.8.170:1521:XE";
		String user = "gambler";
		String pw = "onecard";
		
		conn = DriverManager.getConnection(url, user, pw);
		
		return conn;
	}//connection
	
	public void closed(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}if(stmt != null) {
				stmt.close();
			}if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("6단계 닫기 실패");
			e.printStackTrace();
		}
	}
}

