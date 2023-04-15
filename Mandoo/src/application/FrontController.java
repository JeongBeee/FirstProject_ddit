package application;

import java.util.List;
import java.util.Scanner;

import service.ExamineeService;
import view.RegisterView;
import vo.ExamineeVO;
import vo.RegisterVO;

/**
 * 
 * @author mandoone
 * @since 2023/4/15 10:05 업데이트
 */
public class FrontController {
	private ExamineeService service = new ExamineeService();
	RegisterView view = new RegisterView();
	Scanner scanner = new Scanner(System.in);

	public void process() throws Exception {
		while (true) {
			String choiceSign = view.signMenu(scanner);
			switch (choiceSign) {
			case "1":
				ExamineeVO signExaminee = view.insertSignInfo(scanner); // 회원가입 창 메서드를 불러서
				int insertExaminee = service.insertMyInfo(signExaminee);
				if (insertExaminee > 0) {
					System.out.println("회원가입이 완료되었습니다. 첫 화면으로 돌아갑니다.");
				} else {
					System.out.println("회원가입이 실패하였습니다. 다시 진행해주세요.");
					continue;
				}
				break;
			case "2":
				ExamineeVO loginExaminee = service.loginExaminee(view.login(scanner));
				if (loginExaminee != null) {
					System.out.println("로그인 성공");
					RegisterApplication.eSession = loginExaminee;
					goRegister();
					break;
				} else {
					System.out.println("로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
					FrontController main = new FrontController();
					main.process();
				}
				break;
			}
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> main
	
	private void registerStart() { // 접수하기
		int registermenu = registerView.registerMenu(scanner);
		if (registermenu == 1) {
			registerView.registerSeq(scanner);		
			RegisterApplication.session =  ;
		} else if (registermenu == 2) {
			registerView.registerCheckMenu(scanner);
		} else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");	
			registerStart();
		}
	}
	
	private void showRegister() { // 접수하기
		int registermenu = registerView.registerMenu(scanner);
		if (registermenu == 1) {
			registerView.registerSeq(scanner);			
		} else if (registermenu == 2) {
			registerView.registerCheckMenu(scanner);
		} else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");	
			registerStart();
		}
	}

}
/*private void registerStart() { // 접수하기
	int choice = registerView.registerMenu(scanner);
	if (choice == 1) {
		registerView.registerSeq(scanner);
		
	} else if (choice == 2) {
		registerView.registerCheckMenu(scanner);
	} else {
		System.out.println("잘못된 입력입니다. 다시 입력하세요.");
	}
}*/

// 접수 내역 확인
// 회원정보 및 접수내역 수정

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
<<<<<<< HEAD
=======

	public void goRegister() {
		view.registerMenu(scanner);
		RegisterApplication.rSession = view.registerSeq(scanner);
	}

}
>>>>>>> origin/heyjin
=======
>>>>>>> main
