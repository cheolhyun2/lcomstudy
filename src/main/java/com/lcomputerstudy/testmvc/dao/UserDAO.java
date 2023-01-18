package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.User;

public class UserDAO {
	
	private static UserDAO dao = null;
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	public ArrayList<User> getUsers() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from user";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()) {
				User user = new User();
				user.setU_idx(rs.getInt("u_idx"));
				user.setU_id(rs.getString("u_id"));
				user.setU_name(rs.getString("u_name"));
				user.setU_tel(rs.getString("u_tel"));
				user.setU_age(rs.getString("u_age"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insertUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user(u_id,u_pw,u_name,u_tel,u_age) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, user.getU_pw());
			pstmt.setString(3, user.getU_name());
			pstmt.setString(4, user.getU_tel());
			pstmt.setString(5, user.getU_age());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
/*	public void detailUser(User user) {	// 2023-01-17
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from user where u_idx=?";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){     
		   	       String u_idx = rs.getString("u_idx");
		           String u_id = rs.getString("u_id");
		           String u_name = rs.getString("u_name");
		           String u_tel = rs.getString("u_tel");
		           String u_age = rs.getString("u_age");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}				
	} */

	public User detailUser(User user) {		// 2023-01-18
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User resultUser = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from user where u_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, user.getU_idx());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultUser = new User();
				resultUser.setU_idx(Integer.parseInt(rs.getString("u_idx")));
				resultUser.setU_id(rs.getString("u_id"));
				resultUser.setU_name(rs.getString("u_name"));
				resultUser.setU_tel(rs.getString("u_tel"));
				resultUser.setU_age(rs.getString("u_age"));
		   	       /*String u_idx = rs.getString("u_idx");
		           String u_id = rs.getString("u_id");
		           String u_name = rs.getString("u_name");
		           String u_tel = rs.getString("u_tel");
		           String u_age = rs.getString("u_age");*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultUser;
	}
		
	

}
