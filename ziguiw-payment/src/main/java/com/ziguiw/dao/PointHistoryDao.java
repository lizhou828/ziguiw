package com.ziguiw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.ziguiw.domain.PointHistory;
import com.ziguiw.util.JdbcUtil;

public class PointHistoryDao {
	
	
	public void save(PointHistory ph) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into point_history(user_id,points_change,change_type,state,flag,change_time,id) values(?,?,?,?,?,?,?)";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ph.getUserId());
			pstmt.setInt(2, ph.getPoint_chang());
			pstmt.setInt(3, ph.getType());
			pstmt.setInt(4, ph.getState());
			pstmt.setInt(5, ph.getFlag());
			pstmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setLong(7, this.getAutoId("point_history_seq"));
			JdbcUtil.begin(conn);
			
			pstmt.executeUpdate();
			JdbcUtil.commit(conn);
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public Long getAutoId(String name) throws Exception{
		Long id =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select " + name + ".nextval from dual";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
			  id = rs.getLong("nextval");
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
			JdbcUtil.close(conn);
		}
		
	}
}
