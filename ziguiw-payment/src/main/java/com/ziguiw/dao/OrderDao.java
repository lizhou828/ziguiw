package com.ziguiw.dao;

import com.ziguiw.domain.Order;
import com.ziguiw.payment.exception.DataAccessException;
import com.ziguiw.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderDao extends BaseDao {

	public void save(final Order order) {
		excute(new JdbcCallback() {

			public Object exec(Connection conn) {
				PreparedStatement pstmt;
                String sql = "insert into t_order(paydate,createdate,state,orderno,userId) values(?,?,?,?,?)";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setTimestamp(1,
							new java.sql.Timestamp(new Date().getTime()));
					pstmt.setDate(2, new java.sql.Date(new Date().getTime()));

					pstmt.setInt(3, order.getIsPay());
					pstmt.setString(4, order.getOrder_no());
					pstmt.setInt(5, 1);
					pstmt.execute();
					return null;
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
			}
		});

	}

	public Order getByOrderNo(String order_no) throws Exception {
		Order order = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_order t where t.orderno = ?";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("userId");
				int isPay = rs.getInt("state");
				int amount = rs.getInt("price");
				int type = rs.getInt("type");
				order = new Order();
				order.setUserId(userId);
				order.setIsPay(isPay);
				order.setAmount(amount);
				order.setType(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return order;
	}

	public void update(Order order) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_order t set t.state= ?,t.paydate=? where orderno = ?";
		try {
			conn = JdbcUtil.getMySQLConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getIsPay());
			pstmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setString(3, order.getOrder_no());
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
}
