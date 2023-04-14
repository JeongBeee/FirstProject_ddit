package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vo.ExamineeVO;

public class ExamineeDAO {
	public static void main(String[] args) throws Exception {
		ExamineeDAO dao = new ExamineeDAO();
		System.out.println(dao.loginExaminee(new ExamineeVO("ksm", "1q2w3e4r")));
	}

	Scanner scanner = new Scanner(System.in);

	/**
	 * 회원 가입 메소드 생성자 함수 ExamineeVO(String, String, String, String, String)를 사용해 필드에
	 * 값을 저장하고 저장된 값을 getter로 불러와 DB에 값을 insert 하는 쿼리문 실행
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMyInfo(ExamineeVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:1521:XE";
		String user = "mandoo";
		String password = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(" examinee ");
		builder.append(" ( ");
		builder.append(" ID, ");
		builder.append(" NAME, ");
		builder.append(" PASSWORD, ");
		builder.append(" TELNO, ");
		builder.append(" EMAIL ");
		builder.append(" ) ");
		builder.append(" VALUES ");
		builder.append(" ( ");
		builder.append(" ?, ");
		builder.append(" ?, ");
		builder.append(" ?, ");
		builder.append(" ?, ");
		builder.append(" ? ");
		builder.append(" ) ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getName());
		statement.setString(3, vo.getPassword());
		statement.setString(4, vo.getTelNo());
		statement.setString(5, vo.getEmail());

		int count = statement.executeUpdate();
		statement.close();
		connection.close();
		return count;
	}

	/**
	 * 회원 정보 업데이트 메소드
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateInfo(ExamineeVO vo) throws Exception {
		int choice = Integer.parseInt(scanner.nextLine());
		int count = 0;
		String password = null;
		String telNo = null;
		String email = null;

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:1521:XE";
		String user = "mandoo";
		String dbPassword = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, dbPassword);

		if (choice == 1) {
			password = scanner.nextLine();
			telNo = vo.getTelNo();
			email = vo.getEmail();

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE ");
			builder.append(" EXAMINEE ");
			builder.append(" SET ");
			builder.append(" PASSWAORD = ? ");
			builder.append(" WHERE ");
			builder.append(" ID = ? ");
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getPassword());
			statement.setString(2, vo.getId());
			statement.setString(2, vo.getId());

			statement.close();
			connection.close();
			count = statement.executeUpdate();

		} else if (choice == 2) {
			password = vo.getPassword();
			telNo = scanner.nextLine();
			email = vo.getEmail();

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE ");
			builder.append(" EXAMINEE ");
			builder.append(" SET ");
			builder.append(" TELNO = ? ");
			builder.append(" WHERE ");
			builder.append(" ID = ? ");
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getTelNo());
			statement.setString(2, vo.getId());
			statement.close();
			connection.close();
			count = statement.executeUpdate();

		} else if (choice == 3) {
			password = vo.getPassword();
			telNo = vo.getTelNo();
			email = scanner.nextLine();

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE ");
			builder.append(" EXAMINEE ");
			builder.append(" SET ");
			builder.append(" EMAIL = ? ");
			builder.append(" WHERE ");
			builder.append(" ID = ? ");
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getEmail());
			statement.setString(2, vo.getId());
			statement.close();
			connection.close();
			count = statement.executeUpdate();

		}
		return count;
	}

	/**
	 * 회원 정보를 삭제하는 메소드
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMyInfo(ExamineeVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:1521:XE";
		String user = "mandoo";
		String password = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append("EXAMINEE ");
		builder.append("WHERE ");
		builder.append("ID = ? ");
		builder.append("AND ");
		builder.append("PASSWORD = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getPassword());
		int count = statement.executeUpdate();
		statement.close();
		connection.close();
		return count;
	}

	public ExamineeVO loginExaminee(ExamineeVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:1521:XE";
		String user = "mandoo";
		String password1 = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, password1);
		StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append(" * ");
        builder.append("FROM ");
        builder.append("EXAMINEE ");
        builder.append("WHERE ");
        builder.append("ID = ? ");
        builder.append("AND ");
        builder.append("PASSWORD = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getPassword());
		ResultSet resultSet = statement.executeQuery();
		ExamineeVO loginInfo = null;
		if (resultSet.next()) {
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String password = resultSet.getString("password");
			String telNo = resultSet.getString("telNo");
			String email = resultSet.getString("email");
			loginInfo = new ExamineeVO(id, password, name, telNo, email);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return loginInfo;
	}

//	public List<ExamineeVO> selectExaminee() throws Exception {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		String url = "jdbc:oracle:thin:@192.168.142.33:1521:XE";
//		String user = "mandoo";
//		String password1 = "mandoo";
//		Connection connection = DriverManager.getConnection(url, user, password1);
//		Statement statement = connection.createStatement();
//		StringBuilder builder = new StringBuilder();
//		builder.append("SELECT ");
//		builder.append("ID, ");
//		builder.append("PASSWORD, ");
//		builder.append("FROM ");
//		builder.append("EXAMINEE ");
//		String sql = builder.toString();
//		ResultSet resultSet = statement.executeQuery(sql);
//		List<ExamineeVO> list = new ArrayList<>();
//		while (resultSet.next()) {
//			String id = resultSet.getString("ID");
//			String password = resultSet.getString("PASSWORD");
//			list.add(new ExamineeVO(id, password));
//		}
//		return list;
//	}
}