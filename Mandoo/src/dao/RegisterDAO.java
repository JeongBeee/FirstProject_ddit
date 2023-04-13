package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import vo.RegisterVO;

public class RegisterDAO {
	public static void main(String[] args) {
		System.out.println();
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

		return null;
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

	public RegisterVO updateInfo(RegisterVO vo) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("변경할 정보 선택\n1. 시험장 | 2. 시험코드 | 3. 시험일시");
		int choice = Integer.parseInt(scanner.nextLine());
		String siteCode = null; // 시험장
		String examCode = null; // 과목
		String examDate = null; // 일시

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.33:XE", "mandoo", "mandoo");
		;

		if (choice == 1) {
			siteCode = scanner.nextLine();
			examCode = vo.getExamCode();
			examDate = vo.getSiteCode().substring(2);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE ");
			String sql = buffer.toString();
			
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getSiteCode());
			statement.setString(2, vo.getExamCode());
			statement.close();
			connection.close();

		} else if (choice == 2) {
			siteCode = vo.getSiteCode();
			examCode = scanner.nextLine();
			examDate = vo.getSiteCode().substring(2);

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE ");
			builder.append(" EXAMINEE ");
			builder.append(" SET ");
			builder.append(" TELNO = ? ");
			builder.append(" WHERE ");
			builder.append(" ID = ? ");
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getSiteCode());
			statement.setString(2, vo.getId());
			statement.close();
			connection.close();

		} else if (choice == 3) {
			siteCode = scanner.nextLine();
			examCode = vo.getExamCode();
			examDate = vo.getSiteCode().substring(2);
			
		}
		return new RegisterVO(siteCode, examCode, examDate);
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
