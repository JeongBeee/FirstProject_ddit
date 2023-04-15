package application;

import java.util.List;
import java.util.Scanner;

import javax.imageio.spi.RegisterableService;

import service.ExamineeService;
import service.RegisterService;
import view.RegisterView;
import vo.ExamineeVO;
import vo.RegisterVO;

/**
 * 
 * @author mandoone
 * @since 2023/4/15 10:05 업데이트
 */
public class FrontController {
	private ExamineeService eService = new ExamineeService();
	private RegisterService rService = new RegisterService();
	private RegisterView view = new RegisterView();
	private Scanner scanner = new Scanner(System.in);

	public void process() throws Exception {
		String choiceSign = view.signMenu(scanner);
		switch (choiceSign) {
		case "1":
			try {
				ExamineeVO signExaminee = view.insertSignInfo(scanner); // 회원가입 창 메서드를 불러서
				int insertExaminee = eService.insertMyInfo(signExaminee);
				if (insertExaminee > 0) {
					System.out.println("\n회원가입이 완료되었습니다. 처음으로 돌아갑니다.");
				}
			} catch (Exception e) {
				System.out.println("\n잘못된 정보가 기입되었습니다. 다시 시도해주세요.");
			} finally {
				process();
			}

			break;
		case "2":
			ExamineeVO loginExaminee = eService.loginExaminee(view.login(scanner));
			if (loginExaminee != null) {
				System.out.println("\n로그인이 완료되었습니다. 마이페이지로 이동합니다.");
				RegisterApplication.eSession = loginExaminee;
				goRegister();
				break;
			} else {
				System.out.println("로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
				process();
			}
			break;
		default:
			System.out.println("잘못된 입력입니다. 처음으로 돌아갑니다.");
			process();
			break;
		}

	}

	public void goRegister() throws Exception {
		int registerMenu = view.registerMenu(scanner);
		switch (registerMenu) {
		case 1:
			view.registerSeq(scanner);
			goRegister();
			break;
		case 2:
			goMyPage();
			break;
		}

	}

	private void goMyPage() {
		view.registerCheckMenu(scanner);
		switch (scanner.nextLine()) {
		case "1":
			view.showRegister();
			view.registerCheckMenu(scanner);
			break;

		case "2":
			goUpdatePage();
			break;
		}
	}

	private void goUpdatePage() {
		view.modifyinfoMenu(scanner);
		switch (scanner.nextLine()) {
		case "1":

			break;
		case "2":
			break;
		}
	}

}