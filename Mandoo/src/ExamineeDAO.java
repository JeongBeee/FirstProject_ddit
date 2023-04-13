
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class ExamineeDAO {
	
	Scanner scanner = new Scanner(System.in);
	
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
	
	public String updateInfo(ExamineeVO vo) {
		int choice = Integer.parseInt(scanner.nextLine());
		if(choice == 1) {
			String password = scanner.nextLine();
			String telNo = vo.getPassword();
			String email = vo.getPassword();
		}else if(choice == 2) {
			String password = vo.getPassword();
			String telNo = scanner.nextLine();
			String email = vo.getPassword();
		}else if(choice == 3) {
			String password = vo.getPassword();
			String telNo = vo.getTelNo();
			String email = scanner.nextLine();
			return ExamineeVO(password, telNo, email);
		}
		return ExamineeVO(password, telNo, email);
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