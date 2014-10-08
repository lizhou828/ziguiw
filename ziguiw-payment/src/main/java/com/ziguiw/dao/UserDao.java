package com.ziguiw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ziguiw.domain.Order;
import com.ziguiw.domain.User;
import com.ziguiw.util.JdbcUtil;

public class UserDao {
	
	
	public User getById(int userId) throws Exception{
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user_base t where t.id = ?";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(userId);
				user.setPoints(rs.getInt("points"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return user;
	}
	
	
	public void update(User user) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update user_base t set t.points=? where t.id = ?";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getPoints());
			pstmt.setInt(2, user.getId());
			JdbcUtil.begin(conn);
			pstmt.executeUpdate();
			JdbcUtil.commit(conn);
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
	}
}
