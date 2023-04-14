package Test;

import java.util.Scanner;

import service.ExamineeService;
import service.RegisterService;
import view.RegisterView;
import vo.ExamineeVO;

public class Test {
	public static void main(String[] args) throws Exception {
		RegisterView view = new RegisterView();
		ExamineeService eService = new ExamineeService();
		RegisterService rService = new RegisterService();
		Scanner scanner = new Scanner(System.in);
		view.welcome();
		int menu = view.signMenu(scanner);
		ExamineeVO examinee = null;
		switch (menu) {
		case 1:
			examinee = view.insertSignInfo(scanner);
			int insertExaminee = eService.insertMyInfo(examinee);
			break;
		}
	}

}
