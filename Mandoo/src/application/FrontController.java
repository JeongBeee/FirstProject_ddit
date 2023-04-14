package application;

import java.util.List;
import java.util.Scanner;

import service.ExamineeService;
import view.RegisterView;
import vo.ExamineeVO;

public class FrontController {
	private ExamineeService service = new ExamineeService();
	RegisterView registerView = new RegisterView();
	Scanner scanner = new Scanner(System.in);

	public void process() throws Exception {
		registerView.welcome();
		while (true) {
			int signmenu = registerView.signMenu(scanner);
			if (signmenu == 1) { // 회원가입
				ExamineeVO signExaminee = registerView.insertSignInfo(scanner); // 회원가입 창 메서드를 불러서
				int insertExaminee = service.insertMyInfo(signExaminee);
				if (insertExaminee > 0) {
					System.out.println("회원가입이 완료되었습니다. 첫 화면으로 돌아갑니다.");
				} else {
					System.out.println("회원가입이 실패하였습니다. 다시 진행해주세요.");
					continue;
				}
			} else if (signmenu == 2) { // 로그인
				ExamineeVO loginExaminee = service.loginExaminee(registerView.login(scanner));
				if (loginExaminee != null) {
					System.out.println("로그인 성공");
					RegisterApplication.session = loginExaminee;
					registerStart();
				} else {
					System.out.println("로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
					System.out.println("-----------------------------------");
					FrontController main = new FrontController();
					main.process();
				}

			}
//		int modifyinfoMenu = registerView.modifyinfoMenu(scanner);
//		switch(modifyinfoMenu) {
//		case 1: //비밀번호
//			ExamineeVO examinees = registerView.modifySigninfoMenu(scanner);
//			
//		case 2: //전화번호
//		case 3: //이메일
//		case 4: //회원탈퇴			
			// case 2: // 로그인
			// ExamineeVO examinees = registerView.login(scanner);
			// int selectExaminee = service.login(examinees);
			// break;
//		}
		}
	}

	private void registerStart() {
		int choice = registerView.registerMenu(scanner);
		if (choice == 1) {
			registerView.registerSeq(scanner);
		} else if (choice == 2) {
			registerView.registerCheckMenu(scanner);
		} else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			
		}
	}
}
