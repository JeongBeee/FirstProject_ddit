package view;

import java.util.Map;
import java.util.Scanner;

import org.omg.CORBA.portable.ApplicationException;

import application.FrontController;
import application.RegisterApplication;
import dao.ExamineeDAO;
import vo.ExamSiteVO;
import vo.ExamVO;
import vo.ExamineeVO;
import vo.RegisterVO;

/**
 * 
 * @author mandoone
 * @since 2023/4/15 10:05 업데이트
 */
public class RegisterView {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * 프로그램 시작 시 등장하는 뷰
	 */
	public void welcome() {
		System.out.println("기사 시험 졉수를 시작합니다.");
		System.out.println("q버튼 입력 시 프로그램이 종료됩니다.");
	}

	/**
	 * 회원가입 / 로그인 선택 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 회원가입, 2: 로그인
	 */
	public String signMenu(Scanner scanner) {
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
		System.out.println("\t\t1. 회원가입 | 2. 로그인");
		System.out.print("메뉴를 선택하세요 > ");
		return scanner.nextLine();
	}

	/**
	 * 회원가입 시 정보를 기입하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 각 정보를 입력 받아 새로운 ExamineeVO 객체를 생성함
	 * @throws Exception
	 */
	public ExamineeVO insertSignInfo(Scanner scanner) throws Exception { // 1. 회원가입
		System.out.println("회원정보를 입력하세요.");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		System.out.print("이름: ");
		String name = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		System.out.print("전화번호: ");
		String telNo = scanner.nextLine();
		System.out.print("이메일: ");
		String email = scanner.nextLine();
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
		return new ExamineeVO(id, name, password, telNo, email);

	}

	/**
	 * 로그인 시 정보를 기입하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 로그인된 사용자의 id, password를 이용해 ExamineeVO 객체를 생성함
	 */
	public ExamineeVO login(Scanner scanner) {
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
		System.out.println("로그인으로 이동합니다.");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		System.out.println();
		return new ExamineeVO(id, password);
	}

	/**
	 * 로그인을 완료한 사용자가 접수를 하거나 접수내역을 조회하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 접수하기, 2: 접수내역 조회
	 */
	public int registerMenu(Scanner scanner) {
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
		System.out.println("\t      1. 접수하기 | 2. 접수내역 조회");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 로그인을 완료한 사용자가 접수할 수험장 / 과목 / 회차를 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 입력된 정보로 RegisterVO 객체를 생성함.
	 */
	public RegisterVO registerSeq(Scanner scanner) {
		System.out.println("\n—————————————————— ✼ 수험장 선택 ✼ ——————————————————");
		String choiceExamSite = choiceExamSite(); // 지역명

		System.out.println("\n—————————————————— ✼ 과목 선택 ✼ ————————————————————");
		String choiceExam = choiceExam(); // 시험코드

		System.out.println("\n———————————————— ✼ 응시 회차 선택 ✼ ——————————————————");
		String choiceExamTurn = choiceExamTurn();

		System.out.println("✓ 접수한 시험은 " + choiceExam + " " + choiceExamTurn + ", 수험장은 " + choiceExamSite + "입니다.");
		System.out.println("\n———————————————————— ✼ 결제 ✼ ——————————————————————");
		System.out.println("✼ 결제 ✼");
		System.out.println("응시료는 19400원 입니다.");
		System.out.println("1. 신용카드 | 2. 계좌이체 | 3. 휴대폰 결제");
		System.out.print("결제 방식을 선택하세요.▶ ︎");
		int choosePayment = scanner.nextInt();
		System.out.println();
		System.out.println("접수가 완료되었습니다.");
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		return new RegisterVO(new RegisterApplication().geteSession().getId(), choiceExam,
				choiceExamSite.concat(choiceExamTurn));
	}

	/**
	 * 수험장 지역을 선택하는 뷰
	 * 
	 * @return 선택된 지역
	 */
	public String choiceExamSite() {
		String[] siteNames = new String[12];
		siteNames[0] = "1. 서울";
		siteNames[1] = "2. 대전";
		siteNames[2] = "3. 대구";
		siteNames[3] = "4. 부산";
		siteNames[4] = "5. 울산";
		siteNames[5] = "6. 광주";
		siteNames[6] = "7. 경상";
		siteNames[7] = "8. 경기";
		siteNames[8] = "9. 전라";
		siteNames[9] = "10. 강원";
		siteNames[10] = "11. 충청";
		siteNames[11] = "12. 제주";

		for (int i = 0; i < siteNames.length - 1; i++) {
			System.out.print(siteNames[i] + " | ");
		}
		System.out.println(siteNames[11]);

		System.out.print("수험장을 선택하세요.▶︎ ");
		int choiceSite = Integer.parseInt(scanner.nextLine());

		String confirmSite = null;
		if (choiceSite >= 10) {
			confirmSite = siteNames[choiceSite - 1].substring(4);
		} else {
			confirmSite = siteNames[choiceSite - 1].substring(3);
		}
		System.out.print("\n<" + confirmSite + "> 수험장을 선택하셨습니다. \n");
		return confirmSite;
	}

	/**
	 * 과목을 선택하는 뷰
	 * 
	 * @return 선택된 과목
	 */
	public String examCode() { 
		Map<String, String> examMap = 
		
	}

	/**
	 * 시험 회차를 선택하는 뷰
	 * 
	 * @return 선택된 회차
	 */
	public String choiceExamTurn() {
		String[] examTurns = new String[3];
		examTurns[0] = "1. 1회차(2023년 3월 15일 오전 9시)";
		examTurns[1] = "2. 2회차(2023년 6월 4일 오전 9시)";
		examTurns[2] = "3. 3회차(2023년 7월 23일 오전 9시)";

		for (int i = 0; i < examTurns.length - 1; i++) {
			System.out.print(examTurns[i] + " | ");
		}
		System.out.println(examTurns[2]);

		System.out.print("응시할 시험 회차를 선택하세요.▶ ");
		int choiceExamTurn = Integer.parseInt(scanner.nextLine());

		String confirmExamTurn = examTurns[choiceExamTurn - 1].substring(3);

		System.out.print("\n<" + confirmExamTurn + "> 시험을 선택하셨습니다. \n\n");
		return confirmExamTurn;
	}

	/**
	 * 접수한 사용자가 접수내역을 확인 / 정보 수정을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 접수 내역 확인 / 2. 회원정보 및 접수내역 수정
	 */
	public int registerCheckMenu(Scanner scanner) {
		System.out.println("---------------------------------------------------");
		System.out.println("마이페이지");
		System.out.println("1. 접수 내역 확인 | 2. 회원정보 및 접수내역 수정");
		System.out.println("---------------------------------------------------");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 접수된 시험을 확인하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 */
	public void showRegister(Scanner scanner) { // 1. 접수 내역 확인 - 황금색
		System.out.println("---------------------------------------------------");
		// 접수한 시험은 정보처리기사 2회차(23/04/15), 수험장은 서울입니다.
		System.out.println("마이페이지로 돌아갑니다."); // registerCheckMenu로 돌아감
		registerCheckMenu(scanner);
		System.out.println("---------------------------------------------------");
	}

	/**
	 * 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 회원 정보 / 2. 접수 정보
	 */
	public int modifyinfoMenu(Scanner scanner) { // 2. 회원정보 및 접수내역 수정
		System.out.println("---------------------------------------------------");
		System.out.println("수정할 항목을 선택하세요.");
		System.out.println("1. 회원 정보 | 2. 접수 정보");
		System.out.println("---------------------------------------------------");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 회원 정보 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 ExamineeVO 객체를 반환함
	 */
	public ExamineeVO modifySignInfoMenu(Scanner scanner) { // 2-1 회원 정보 수정
		System.out.println("1. 비밀번호 | 2. 전화번호 | 3. 이메일 | 4. 회원 탈퇴");
		System.out.println("새 비밀번호를 입력하세요. : ");
		String password = scanner.nextLine();
		System.out.println("새 전화번호를 입력하세요. : ");
		String telNo = scanner.nextLine();
		System.out.println("새 이메일을 입력하세요. : ");
		String email = scanner.nextLine();
		return new ExamineeVO(password, telNo, email);
	}

	/**
	 * 접수 내역 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 RegisterVO 객체를 반환함
	 */
	public RegisterVO modifyExamInfoMenu(Scanner scanner) { // 2-2 접수 정보 수정
		System.out.println("1. 시험장 | 2. 과목 | 3. 응시회차 | 4. 접수 취소");
		// 1번 2번 3번 입력 어떻게 받는지?
		System.out.println("새 비밀번호를 입력하세요. : ");
		String password = scanner.nextLine();
		System.out.println("새 전화번호를 입력하세요. : ");
		String telNo = scanner.nextLine();
		System.out.println("새 이메일을 입력하세요. : ");
		String email = scanner.nextLine();
		// 같은 문제
		return new RegisterVO(password, telNo, email);
	}

}