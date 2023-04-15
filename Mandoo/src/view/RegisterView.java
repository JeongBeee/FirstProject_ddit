package view;

import java.util.Scanner;

import application.FrontController;
import application.RegisterApplication;
import dao.ExamineeDAO;
import vo.ExamSiteVO;
import vo.ExamVO;
import vo.ExamineeVO;
import vo.RegisterVO;

public class RegisterView {
	private Scanner scanner = new Scanner(System.in);

	public void welcome() {
		System.out.println("기사 시험 졉수를 시작합니다.");
		System.out.println("q버튼 입력 시 프로그램이 종료됩니다.");
	}

	public int signMenu(Scanner scanner) {
		System.out.println("-----------------------------------");
		System.out.println("        1. 회원가입 | 2. 로그인         ");
		System.out.println("-----------------------------------");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	public ExamineeVO insertSignInfo(Scanner scanner) throws Exception { // 1. 회원가입
		System.out.println("-----------------------------------");
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
		return new ExamineeVO(id, name, password, telNo, email);

	}

	public ExamineeVO login(Scanner scanner) { // 2. 로그인
		System.out.println("-----------------------------------");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		return new ExamineeVO(id, password);
	}

	public int registerMenu(Scanner scanner) {
		System.out.println("-----------------------------------");
		System.out.println("1. 접수하기 | 2. 접수내역 조회");
		System.out.println("-----------------------------------");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	public RegisterVO registerSeq(Scanner scanner) { // 1. 접수하기
		System.out.println("---------------------------------------------");
		System.out.println("수험장을 선택하세요.");
		String choiceExamSite = choiceExamSite(); // 지역명
		System.out.println("------------------------------------------------------------");
		System.out.println("시험 과목을 선택하세요.");
		String choiceExam = choiceExam(); // 시험코드
		System.out.println("------------------------------------------------------------");
		System.out.println("응시할 시험 회차를 선택하세요.");
		String choiceExamTurn = choiceExamTurn();
		System.out.println("------------------------------------------------------------");
		// System.out.println("계속 진행하시겠습니까? [y/n]"); 
		// String answer = scanner.nextLine();
		System.out.println("------------------------------------------------------------");
		System.out.println("응시료는 19400원 입니다.");
		System.out.println("결제 방식을 선택하세요.");
		System.out.println("1. 신용카드 | 2. 계좌이체 | 3. 휴대폰 결제");
		System.out.println("------------------------------------------------------------");
		int choosePayment = scanner.nextInt();
		System.out.println("접수가 완료되었습니다.");
		System.out.println("------------------------------------------------------------");
		// String id =
		// login에서 저장한 id를 불러와서 registerVO에 저장하려면
		return new RegisterVO(RegisterApplication.session.getId(), choiceExam, choiceExamSite.concat(choiceExamTurn));
	}

	
///////응시회차 선택 메서드
	public String choiceExamTurn() {
		String[] examTurns = new String[3];
		examTurns[0] = "1. 23/03/15 오전 9시";
		examTurns[1] = "2. 23/06/04 오전 9시";
		examTurns[2] = "3. 23/07/23 오전 9시";
		System.out.println("------------------------------------------------------------");

		for (int i = 0; i < examTurns.length - 1; i++) {
			System.out.print(examTurns[i] + " | ");
		}
		System.out.println(examTurns[2]);

		System.out.println("------------------------------------------------------------");
		System.out.print("응시할 시험 회차를 선택하세요 > ");
		int choiceExamTurn = Integer.parseInt(scanner.nextLine());

		// String confirmExamTurn = null;

		String confirmExamTurn = examTurns[choiceExamTurn - 1].substring(3);

		System.out.print("<" + confirmExamTurn + "> 시험을 선택하셨습니다.");
		return confirmExamTurn;
	}
	
///////수험장 선택 메서드
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

		// System.out.print(siteNames[9] + " | ");
		// System.out.print(siteNames[10] + " | ");
		System.out.println(siteNames[11]);

		System.out.print("수험장을 선택하세요 > ");
		int choiceSite = Integer.parseInt(scanner.nextLine());

		String confirmSite = null;
		if (choiceSite >= 10) {
			confirmSite = siteNames[choiceSite - 1].substring(4);
		} else {
			confirmSite = siteNames[choiceSite - 1].substring(3);
		}
		System.out.print(confirmSite + " 수험장을 선택하셨습니다.");
		return confirmSite;
	}

///////시험과목 선택 메서드	
	public String choiceExam() {
		String[] examNames = new String[10];
		examNames[0] = "1. 토목기사";
		examNames[1] = "2. 조경기사";
		examNames[2] = "3. 가스기사";
		examNames[3] = "4. 전기공사기사";
		examNames[4] = "5. 전자기사";
		examNames[5] = "6. 용접기사";
		examNames[6] = "7. 화공기사";
		examNames[7] = "8. 건축기사";
		examNames[8] = "9. 자동차정비기사";
		examNames[9] = "10. 정보처리기사";

		for (int i = 0; i < examNames.length - 1; i++) {
			System.out.print(examNames[i] + " | ");
		}

		System.out.println(examNames[9]);

		System.out.print("응시할 시험 코드를 입력하세요 > ");
		int choiceExam = Integer.parseInt(scanner.nextLine());

		String confirmExam = null;
		if (choiceExam == 10) {
			confirmExam = examNames[choiceExam - 1].substring(4);
		} else {
			confirmExam = examNames[choiceExam - 1].substring(3);
		}
		System.out.print(confirmExam + "를 선택하셨습니다.");
		return confirmExam;
	}

	public int registerCheckMenu(Scanner scanner) {
		System.out.println("-----------------------------------");
		System.out.println("1. 접수 내역 확인 | 2. 회원정보 및 접수내역 수정");
		System.out.println("-----------------------------------");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

//	public int showRegister(Scanner scanner) { // 1. 접수 내역 확인 - 황금색
//		System.out.println("-----------------------------------");
////		System.out.println(id + siteName + examName + examDate); // 일단
//		System.out.println("초기화면으로 돌아가시겠습니까? [y/n]");
//		String choose = scanner.nextLine();
//		System.out.println("-----------------------------------");
//// int 맞는지, 접수내역 여러개 조회되도록 어떻게 할지
//	}

	public int modifyinfoMenu(Scanner scanner) { // 2. 회원정보 및 접수내역 수정
		System.out.println("-----------------------------------");
		System.out.println("수정할 항목을 선택하세요.");
		System.out.println("1. 회원 정보 | 2. 접수 정보");
		System.out.println("-----------------------------------");
		return Integer.parseInt(scanner.nextLine());
	}

	public ExamineeVO modifySigninfoMenu(Scanner scanner) { // 2-1 회원 정보 수정
		System.out.println("1. 비밀번호 | 2. 전화번호 | 3. 이메일 | 4. 회원 탈퇴");
		// 1번 2번 3번 입력 어떻게 받는지?
		System.out.println("새 비밀번호를 입력하세요. : ");
		String password = scanner.nextLine();
		System.out.println("새 전화번호를 입력하세요. : ");
		String telNo = scanner.nextLine();
		System.out.println("새 이메일을 입력하세요. : ");
		String email = scanner.nextLine();
		return new ExamineeVO(password, telNo, email);
	}

//	public ResgisterVO modifyExaminfoMenu(Scanner scanner) { // 2-2 접수 정보 수정
//		System.out.println("1. 시험장 | 2. 과목 | 3. 응시회차 | 4. 접수 취소");
//		// 1번 2번 3번 입력 어떻게 받는지?
//		System.out.println("새 비밀번호를 입력하세요. : ");
//		String password = scanner.nextLine();
//		System.out.println("새 전화번호를 입력하세요. : ");
//		String telNo = scanner.nextLine();
//		System.out.println("새 이메일을 입력하세요. : ");
//		String email = scanner.nextLine();
//      System.out.println(id , sitename, examname, examdate "접수를 취소하시겠습니가? [y/n]");
//		// 같은 문제
//		return new RegisterVO(password, telNo, email);
//
//	public ResgisterVO modifyExaminfoMenu(Scanner scanner) { // 3. 접수 취소
//		System.out.println("1. 시험장 | 2. 과목 | 3. 응시회차");
//		// 1번 2번 3번 입력 어떻게 받는지?
//		System.out.println("새 비밀번호를 입력하세요. : ");
//		String password = scanner.nextLine();
//		System.out.println("새 전화번호를 입력하세요. : ");
//		String telNo = scanner.nextLine();
//		System.out.println("새 이메일을 입력하세요. : ");
//		String email = scanner.nextLine();
//		// 같은 문제
//		return new RegisterVO(password, telNo, email);
//
//	}

}
