package view;

import java.awt.RenderingHints.Key;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.omg.CORBA.portable.ApplicationException;
import org.omg.CosNaming._BindingIteratorImplBase;

import application.FrontController;
import application.RegisterApplication;
import dao.ExamineeDAO;
import service.ExamineeService;
import service.RegisterService;
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
	private RegisterService rService = new RegisterService();
	private ExamineeService eService = new ExamineeService();
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
		System.out.println("\n——————————————————— ✼ 회원가입 ✼ ———————————————————");
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
		System.out.println("\n——————————————————— ✼ 가 입 중 ✼ ———————————————————");
		return new ExamineeVO(id, name, password, telNo, email);

	}

	/**
	 * 로그인 시 정보를 기입하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 로그인된 사용자의 id, password를 이용해 ExamineeVO 객체를 생성함
	 */
	public ExamineeVO login(Scanner scanner) {
		System.out.println("\n——————————————————— ✼ 로그인 ✼ ————————————————————");
		System.out.println("로그인으로 이동합니다.");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		System.out.println("\n————————————————— ✼ 로 그 인 중 ✼ —————————————————");
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
		System.out.println("\t      1. 접수하기 | 2. 마이페이지");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 로그인을 완료한 사용자가 접수할 수험장 / 과목 / 회차를 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 입력된 정보로 RegisterVO 객체를 생성함.
	 * @throws Exception
	 */
	public void registerSeq(Scanner scanner) throws Exception {
		System.out.println("\n—————————————————— ✼ 수험장 선택 ✼ ——————————————————");
		String choiceExamSite = choiceExamSite(); // 지역명

		System.out.println("\n—————————————————— ✼ 과목 선택 ✼ ————————————————————");
		String choiceExamCode = choiceExam(); // 시험코드
		RegisterApplication.rSession.setExamCode(choiceExamCode);

		System.out.println("\n———————————————— ✼ 응시 회차 선택 ✼ ——————————————————");
		String choiceExamTurn = choiceExamTurn();
		RegisterApplication.rSession.setSiteCode(choiceExamSite.concat(choiceExamTurn));

		System.out.println("\n———————————————————— ✼ 결제 ✼ ——————————————————————");
		System.out.println("응시료는 19400원 입니다.");
		System.out.println("1. 신용카드 | 2. 계좌이체 | 3. 휴대폰 결제");
		System.out.print("결제 방식을 선택하세요.▶ ︎");
		scanner.nextInt();
		RegisterApplication.rSession.setId(RegisterApplication.eSession.getId());
		RegisterVO registerExaminee = new RegisterVO(RegisterApplication.rSession.getId(),
				RegisterApplication.rSession.getExamCode(), RegisterApplication.rSession.getSiteCode());
		int insertRegister = rService.insertRegisterInfo(registerExaminee);
		if (insertRegister > 0) {
			System.out.println("접수가 완료되었습니다.");
		} else {
			System.out.println("접수를 실패하였습니다. 접수 페이지로 돌아갑니다.");
			registerMenu(scanner);
		}
	}

	/**
	 * 수험장 지역을 선택하는 뷰
	 * 
	 * @return 선택된 지역
	 */
	public String choiceExamSite() {
		Map<String, String> examSiteMap = new LinkedHashMap<>();
		examSiteMap.put("SE", "서울");
		examSiteMap.put("BS", "부산");
		examSiteMap.put("IC", "인천");
		examSiteMap.put("DG", "대구");
		examSiteMap.put("DJ", "대전");
		examSiteMap.put("GJ", "광주");
		examSiteMap.put("US", "울산");
		examSiteMap.put("GG", "경기도");
		examSiteMap.put("GW", "강원도");
		examSiteMap.put("CC", "충청도");
		examSiteMap.put("JL", "전라도");
		examSiteMap.put("GS", "경상도");
		examSiteMap.put("JJ", "제주도");

		for (String key : examSiteMap.keySet()) {
			System.out.print(examSiteMap.get(key) + " | ");
		}

		System.out.println("응시 장소를 입력하세요 ▶ ︎");
		String choiceSite = scanner.nextLine(); // 시험이름 입력
		System.out.println("<" + choiceSite + "> 시험을 선택하셨습니다.");

		return getKey(examSiteMap, choiceSite);
	}

	/**
	 * 과목을 선택하는 뷰
	 * 
	 * @return 선택된 과목
	 */
	public String choiceExam() {
		Map<String, String> examMap = new LinkedHashMap<>();
		examMap.put("ECE", "토목기사");
		examMap.put("ELA", "조경기사");
		examMap.put("EGS", "가스기사");
		examMap.put("EEW", "전기공사기사");
		examMap.put("EET", "전자기사");
		examMap.put("EWD", "용접기사");
		examMap.put("ECI", "화공기사");
		examMap.put("EAT", "건축기사");
		examMap.put("EMM", "자동차정비기사");
		examMap.put("EIP", "정보처리기사");

		for (String key : examMap.keySet()) {
			System.out.print(examMap.get(key) + " | ");
		}

		System.out.println("응시할 시험의 과목을 입력하세요 ▶ ︎");
		String choiceExam = scanner.nextLine(); // 시험이름 입력
		System.out.println("<" + choiceExam + "> 시험을 선택하셨습니다.");

		return getKey(examMap, choiceExam);
	}

	/**
	 * 시험 회차를 선택하는 뷰
	 * 
	 * @return 선택된 회차
	 */
	public String choiceExamTurn() {
		Map<String, String> examTurnMap = new LinkedHashMap<>();
		examTurnMap.put("1", "1회차(2023년 3월 15일 오전 9시)");
		examTurnMap.put("2", "2회차(2023년 6월  4일 오전 9시)");
		examTurnMap.put("3", "3회차(2023년 7월 23일 오전 9시)");

		for (String key : examTurnMap.keySet()) {
			System.out.print(examTurnMap.get(key) + " | ");
		}

		System.out.println("응시할 회차의 번호를 입력하세요 ▶ ︎");
		String choiceTurn = scanner.nextLine(); // 시험이름 입력
		System.out.println("<" + examTurnMap.get(choiceTurn) + "> 시험을 선택하셨습니다.");

		return choiceTurn;
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}

	/**
	 * 접수한 사용자가 접수내역을 확인 / 정보 수정을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 접수 내역 확인 / 2. 회원정보 및 접수내역 수정
	 */
	public int registerCheckMenu(Scanner scanner) {
		System.out.println("\n————————————————— ✼ 마이페이지 ✼ ——————————————————");
		System.out.println("1. 접수 내역 확인 | 2. 회원정보 및 접수내역 수정");
		System.out.print("메뉴를 선택하세요 > ");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 접수된 시험을 확인하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 */
	public void showRegister() { // 1. 접수 내역 확인 - 황금색
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		// 접수한 시험은 정보처리기사 2회차(23/04/15), 수험장은 서울입니다.
		System.out.println("마이페이지로 돌아갑니다."); // registerCheckMenu로 돌아감
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	}

	/**
	 * 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 회원 정보 / 2. 접수 정보
	 */
	public int modifyinfoMenu(Scanner scanner) { // 2. 회원정보 및 접수내역 수정
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		System.out.println("수정할 항목을 선택하세요.");
		System.out.println("1. 회원 정보 | 2. 접수 정보");
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		return Integer.parseInt(scanner.nextLine());
	}

	/**
	 * 회원 정보 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 ExamineeVO 객체를 반환함
	 * @throws Exception
	 */
	public void modifySignInfoMenu(Scanner scanner) throws Exception { // 2-1 회원 정보 수정
		System.out.println("1. 비밀번호 | 2. 전화번호 | 3. 이메일 | 4. 회원 탈퇴");
		switch (scanner.nextLine()) {
		case "1":
			System.out.println("새로운 비밀번호를 입력하세요");
			RegisterApplication.eSession.setPassword(scanner.nextLine());
			eService.updatePassword(RegisterApplication.eSession);
			break;

		case "2":
			System.out.println("새로운 전화번호를 입력하세요");
			RegisterApplication.eSession.setTelNo(scanner.nextLine());
			System.out.println(RegisterApplication.eSession.getTelNo());
			eService.updateTelNO(RegisterApplication.eSession);
			break;

		case "3":
			System.out.println("새로운 이메일을 입력하세요");
			RegisterApplication.eSession.setEmail(scanner.nextLine());
			eService.updateEmail(RegisterApplication.eSession);
			break;

		case "4":
			System.out.println("정말 회원 정보 삭제하시겠습니까? [y/n]");
			if (scanner.nextLine().equalsIgnoreCase("y")) {
				eService.deleteMyInfo(RegisterApplication.eSession);
			} else if (scanner.nextLine() == "n") {

			}
			break;
		}
	}

	/**
	 * 접수 내역 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 RegisterVO 객체를 반환함
	 */
//	public RegisterVO modifyExamInfoMenu(Scanner scanner) { // 2-2 접수 정보 수정
//		System.out.println("1. 시험장 | 2. 과목 | 3. 응시회차 | 4. 접수 취소");
//		// 1번 2번 3번 입력 어떻게 받는지?
//		System.out.println("새 시험장을 선택하세요. : ");
//		String sitecode = scanner.nextLine();
//		System.out.println("새 시험과목을 선택하세요. : ");
//		String examcode = scanner.nextLine();
//		System.out.println("새 응시회차를 선택하세요. : ");
//		String sitecode = scanner.nextLine();
//		// 같은 문제
//		return new RegisterVO(password, telNo, email);
//	}

}