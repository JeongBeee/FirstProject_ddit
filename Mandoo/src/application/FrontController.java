package application;

import java.util.List;
import java.util.Scanner;

import service.ExamineeService;
import view.RegisterView;
import vo.ExamineeVO;

public class FrontController {
	private ExamineeService service = new ExamineeService();
	Scanner scanner = new Scanner(System.in);
	  
	public void process() throws Exception {
		
    RegisterView registerView = new RegisterView();
	registerView.welcome();
    while(true) {
		int signmenu = registerView.signmenu(scanner);
		switch(signmenu) {
		case 1: // 회원가입
			ExamineeVO examinees = registerView.insertSignInfo(scanner); // 회원가입 창 메서드를 불러서 
			int insertExaminee = service.insertMyInfo(examinees);
			break;
		//case 2: // 로그인
		//	ExamineeVO examinees = registerView.login(scanner);
		//	int selectExaminee = service.selectMyInfo(examinees);
		//	break;
		}
    }
	}
}
