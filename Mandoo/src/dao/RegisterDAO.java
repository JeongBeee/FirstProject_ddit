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

public class RegisterDAO {
	public static void main(String[] args) throws Exception {
		RegisterDAO dao = new RegisterDAO();
		List<RegisterVO> registerLists = dao.selectRegisterInfos();
		for (RegisterVO vo : registerLists) {
			System.out.println(vo);
		}
	}

	/**
	 * 특정 아이디의 모든 접수 정보를 출력하는 메서드.
	 * 
	 * @return 해당 아이디의 접수정보(RegisterVO)를 List형태로 출력
	 * @throws Exception
	 */
	public List<RegisterVO> selectRegisterInfos() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");

		Statement statement = connection.createStatement();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ID, EXAMCODE, SITECODE FROM REGISTER");
		String sql = buffer.toString();
		ResultSet resultSet = statement.executeQuery(sql);

		List<RegisterVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("ID");
			String examCode = resultSet.getString("EXAMCODE");
			String siteCode = resultSet.getString("SITECODE");

			list.add(new RegisterVO(id, examCode, siteCode));
		}

		return list;
	}

	public RegisterVO selectRegisterInfo(String searchId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:xe", "mandoo",
				"mandoo");
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

	public int updateInfo(RegisterVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:1521:XE", "mandoo",
				"mandoo");
		;

		String siteCode = null; // 시험장
		String examCode = null; // 과목
		String examDate = null; // 일시
		int count = 0;

		System.out.print("변경할 항목을 입력하세요. \n1. 시험장 | 2. 시험과목 | 3. 응시 일시 >> ");
		int choice = Integer.parseInt(new Scanner(System.in).nextLine());
		if (choice == 1) {
			siteCode = new Scanner(System.in).nextLine();
			examCode = vo.getExamCode();
			examDate = vo.getSiteCode().substring(2); // GW2

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"UPDATE REGISTER SET SITECODE = ? || SUBSTR(SITECODE, 3) WHERE SUBSTR(SITECODE, 3, 1) = ? AND ID = ?");
			String sql = buffer.toString();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, siteCode.substring(0, 1));
			preparedStatement.setString(2, vo.getSiteCode().substring(2));
			preparedStatement.setString(3, vo.getId());

			count = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} else if (choice == 2) {
			siteCode = vo.getSiteCode();
			examCode = new Scanner(System.in).nextLine();

			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE REGISTER SET EXAMCODE = ? WHERE ID = ?");
			String sql = buffer.toString();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, examCode);
			preparedStatement.setString(2, vo.getId());

			count = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} else if (choice == 3) {
			siteCode = vo.getSiteCode();
			examCode = "EEW";

			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE REGISTER SET EXAMCODE = ? WHERE ID = ?");
			String sql = buffer.toString();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, examCode);
			preparedStatement.setString(2, vo.getId());

			count = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		}
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
