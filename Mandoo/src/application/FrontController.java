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

	public void goRegister() {
		view.registerMenu(scanner);
		RegisterApplication.rSession = view.registerSeq(scanner);
	}

}
