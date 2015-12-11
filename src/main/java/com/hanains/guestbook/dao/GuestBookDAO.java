package com.hanains.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.guestbook.vo.GuestBookVo;

@Repository
public class GuestBookDAO {

	private static final String ORCLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String CONNECT_DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_ID = "webdb";
	private static final String DB_PASSWORD = "webdb";
	
	private static final String INSERT_GUESTBOOK_QUERY = "insert into GUESTBOOK values( GUESTBOOK_SEQ.nextval,? ,? , ?, SYSDATE )";
	private static final String DELETE_GUESTBOOK_QUERY = "delete from GUESTBOOK  where no = ? and password=?";
	private static final String SELECT_GUESTBOOK_QUERY = "select no, name, password, message, to_char(reg_date,'YYYY-MM-DD HH:MI:SS') from GUESTBOOK order by no desc";
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// Load Driver(Class Dynamic Loading)
			Class.forName(ORCLE_DRIVER);

			// Connect DB
			String url = CONNECT_DB_URL;
			conn = DriverManager.getConnection(url, DB_ID, DB_PASSWORD);

		} catch (ClassNotFoundException e) {
			System.out.println("[error] Fail Diver loading :" + e);
		}

		return conn;
	}

	public List<GuestBookVo> getList() {

		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Create Statement
			stmt = conn.createStatement();

			// Execute SQL
			rs = stmt.executeQuery(SELECT_GUESTBOOK_QUERY); // select문만 executeQuery, 이 외에는
											// executeUpdate
			while (rs.next()) {
				int index = 1;
				Long no = rs.getLong(index++);
				String name = rs.getString(index++);
				String password = rs.getString(index++);
				String message = rs.getString(index++);
				String date = rs.getString(index);

				GuestBookVo vo = new GuestBookVo(no, name, password, message,
						date);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void delete(GuestBookVo vo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(DELETE_GUESTBOOK_QUERY);

			// binding
			int index = 1;
			pstmt.setLong(index++, vo.getNo());
			pstmt.setString(index, vo.getPassword());

			// Execute SQL
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(GuestBookVo vo) {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(INSERT_GUESTBOOK_QUERY);

			// binding
			int index = 1;
			pstmt.setString(index++, vo.getName());
			pstmt.setString(index++, vo.getPassword());
			pstmt.setString(index, vo.getMessage());

			// Execute SQL
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
