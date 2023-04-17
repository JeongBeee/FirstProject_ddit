package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vo.RegisterVO;

/**
 * 
 * @author leehyejin
 * @since 2023/4/15 10:05 업데이트
 */
public class RegisterDAO {
	public static void main(String[] args) throws Exception {
		RegisterDAO dao = new RegisterDAO();
		dao.updateSiteCode(dao.selectRegisterInfo("ksm"));
	}

	/**
	 *   특정 아이디의 모든 접수 정보를 출력하는 메서드.
	 * 
	 * @return 해당 아이디의 접수정보(RegisterVO)를 List형태로 출력
	 * @throws Exception
	 */
	public List<RegisterVO> selectRegisterInfos(String searchId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo", "mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");

		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM REGISTER WHERE ID = ?");
		String sql = buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, searchId);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<RegisterVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("ID");
			String examCode = resultSet.getString("EXAMCODE");
			String siteCode = resultSet.getString("SITECODE");

			list.add(new RegisterVO(id, examCode, siteCode));
		}

		return list;
	}

	/**
	 * 몰라?
	 * 
	 * @param searchId
	 * @return
	 * @throws Exception
	 */
	public RegisterVO selectRegisterInfo(String searchId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM REGISTER WHERE ID = ? AND EXAMCODE = ?");
		String sql = buffer.toString();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, searchId);
		ResultSet resultSet = preparedStatement.executeQuery();

		RegisterVO vo = null;
		if (resultSet.next()) {
			String id = resultSet.getString("ID");
			String examCode = resultSet.getString("EXAMCODE");
			String siteCode = resultSet.getString("SITECODE");
			vo = new RegisterVO(id, examCode, siteCode);
		}
		close(resultSet, preparedStatement, connection);
		return vo;
	}

	/**
	 * 회원가입을 마친 사용자의 접수 정보를 테이블에 삽입하는 메서드.
	 * 
	 * @param vo 회원가입을 마친 사용자의 접수 정보
	 * @return 삽입된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int insertRegisterInfo(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO REGISTER VALUES (?, ?, ?)");
		String sql = buffer.toString();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, vo.getId());
		preparedStatement.setString(2, vo.getExamCode());
		preparedStatement.setString(3, vo.getSiteCode());

		int count = preparedStatement.executeUpdate();
		RegisterDAO.close(null, preparedStatement, connection);
		return count;
	}

	/**
	 * 사용자가 접수한 시험의 과목을 변경하는 메서드.
	 * 
	 * @param vo 시험을 접수한 사용자.
	 * @return 업데이트된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int updateExamCode(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE REGISTER SET EXAMCODE = ? WHERE ID = ?");
		String sql = buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, vo.getExamCode());
		preparedStatement.setString(2, vo.getId());

		int count = preparedStatement.executeUpdate();
		close(null, preparedStatement, connection);

		return count;
	}

	/**
	 * 사용자가 접수한 시험의 지역을 변경하는 메서드.
	 * 
	 * @param vo 시험을 접수한 사용자.
	 * @return 업데이트된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int updateSiteCode(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");

		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"UPDATE REGISTER SET SITECODE = (? || SUBSTR(SITECODE, 3)) WHERE SUBSTR(SITECODE, 3, 1) = ? AND ID = ?");
		String sql = buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, vo.getSiteCode().substring(0, 1));
		preparedStatement.setString(2, vo.getSiteCode().substring(2));
		preparedStatement.setString(3, vo.getId());

		int count = preparedStatement.executeUpdate();
		close(null, preparedStatement, connection);

		return count;
	}

	/**
	 * 사용자가 접수한 시험의 회차를 변경하는 메서드.
	 * 
	 * @param vo 시험을 접수한 사용자.
	 * @return 업데이트된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int updateExamDate(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"UPDATE REGISTER SET SITECODE = (SUBSTR(SITECODE, 1, 2) ||  ?) WHERE SUBSTR(SITECODE, 1, 2) = ? AND ID = ?");
		String sql = buffer.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "1");
		preparedStatement.setString(2, vo.getSiteCode().substring(0, 2));
		preparedStatement.setString(3, vo.getId());

		int count = preparedStatement.executeUpdate();
		close(null, preparedStatement, connection);

		return count;

	}

	/**
	 * 시험을 접수한 사용자의 모든 접수 내역을 삭제하는 메서드.
	 * 
	 * @param vo 접수 내역이 존재하는 사용자의 아이디: RegisterVO(String id)
	 * @return 삭제된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int deleteOneOfRegisterInfo(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM REGISTER WHERE ID = ?");
		String sql = buffer.toString();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, vo.getId());

		int count = preparedStatement.executeUpdate();
		RegisterDAO.close(null, preparedStatement, connection);
		return count;
	}

	/**
	 * 사용자가 접수한 특정 시험의 내역을 삭제하는 메서드.
	 * 
	 * @param vo 사용자의 접수 내역: RegisterVO(String id, String examCode, String siteCode)
	 * @return 삭제된 행의 개수를 반환함.
	 * @throws Exception
	 */
	public int deleteRegisterInfo(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
//		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mandoo", "mandoo");
//
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM REGISTER WHERE ID = ? AND EXAMCODE = ? AND SITECODE = ?");
		String sql = buffer.toString();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, vo.getId());
		preparedStatement.setString(2, vo.getExamCode());
		preparedStatement.setString(3, vo.getSiteCode());

		int count = preparedStatement.executeUpdate();
		RegisterDAO.close(null, preparedStatement, connection);
		return count;
	}

	/**
	 * ResultSet, PreparedStatement, Connection의 각 객체들을 종료하는 메서드.
	 * 
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection
	 */
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		try {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
		}
	}
}
