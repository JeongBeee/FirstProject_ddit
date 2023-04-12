package Examinee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class ExamineeDAO {
	
	Scanner scanner = new
	
	public int insertMyInfo(ExamineeVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:XE";
		String user = "mandoo";
		String password = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
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
	
	public ExamineeVO updateInfo() {
		int choice = Integer.parseInt()
		
	}

	public int deleteMyInfo(ExamineeVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.33:XE";
		String user = "mandoo";
		String password = "mandoo";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getPassword());
		int count = statement.executeUpdate();
		statement.close();
		connection.close();
		return count;
	}
}