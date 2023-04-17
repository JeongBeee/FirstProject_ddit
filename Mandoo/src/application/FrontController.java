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
		view.welcome();
		String menu = view.signMenu(scanner);
		switch (menu) {
		case "1":
			try {
				ExamineeVO signExaminee = view.insertSignInfo(scanner); // 회원가입 창 메서드를 불러서
				int insertExaminee = eService.insertMyInfo(signExaminee);
				if (insertExaminee > 0) {
					System.out.println("\n📢 회원가입이 완료되었습니다. 처음으로 돌아갑니다.");
				}
			} catch (Exception e) {
				System.out.println("\n📢 잘못된 정보가 기입되었습니다. 다시 시도해주세요.");
			} finally {
				process();
			}

			break;
		case "2":
			ExamineeVO loginExaminee = eService.loginExaminee(view.login(scanner));
			if (loginExaminee != null) {
				System.out.println("\n📢 로그인이 완료되었습니다. 다음 페이지로 이동합니다.");
				RegisterApplication.eSession = loginExaminee;
				RegisterApplication.rSession.setId(loginExaminee.getId());
				System.out.println(
						"▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
				System.out.println(
						"                                                              1. 접수하기📝 | 2. 마이페이지👤");
				System.out.print("메뉴를 선택하세요 ▶︎ ");
				goRegister();
				break;
			} else {
				System.out.println("📢 로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
				process();
			}
			break;
		default:
			System.out.println("📢 잘못된 입력입니다. 처음으로 돌아갑니다.");
			process();
			break;
		}

	}

	public void goRegister() throws Exception {
		while (true) {
			String menu = view.registerMenu(scanner);
			switch (menu) {
			case "1":
				view.registerSeq(scanner);
				System.out.println(
						"▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
				System.out.println(
						"                                                              1. 접수하기📝 | 2. 마이페이지👤");
				System.out.print("메뉴를 선택하세요 ▶︎ ");
				continue;
			case "2":
				goMyPage();
				break;
		}

		}
	}

	private void goMyPage() throws Exception {
		String menu = view.registerCheckMenu(scanner);
		switch (menu) {
		case "1":
			view.showRegister();
			goMyPage();
			break;
		case "2":
			goUpdatePage();
			break;
		}
	}

	private void goUpdatePage() throws Exception {
		String menu = view.modifyinfoMenu(scanner);
		switch (menu) {
		case "1":
			view.cancelExam(scanner);
			goRegister();
			break;
		case "2":
			view.modifySignInfoMenu(scanner);
			break;
		}
	}

}