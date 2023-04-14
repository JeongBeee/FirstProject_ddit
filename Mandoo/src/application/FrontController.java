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
			if (insertExaminee > 0) {
				System.out.println("회원가입이 완료되었습니다. 첫 화면으로 돌아갑니다.");
			} else {
				System.out.println("회원가입이 실패하였습니다. 다시 진행해주세요.");
			}
			break;
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> main
		case 2: // 로그인
			ExamineeVO examinees = registerView.login(scanner);
			int selectExaminee = service.selectMyInfo(examinees);
			break;
		}
		int modifyinfoMenu = registerView.modifyinfoMenu(scanner);
		switch(modifyinfoMenu) {
		case 1: //비밀번호
			ExamineeVO examinees = registerView.modifySigninfoMenu(scanner);
			
		case 2: //전화번호
		case 3: //이메일
		case 4: //회원탈퇴			
<<<<<<< HEAD
=======
>>>>>>> parent of c8675e0 (FrontController 수정)
=======
>>>>>>> main
		//case 2: // 로그인
		//	ExamineeVO examinees = registerView.login(scanner);
		//	int selectExaminee = service.login(examinees);
		//	break;
>>>>>>> origin/main
		}
    }
	}
}
