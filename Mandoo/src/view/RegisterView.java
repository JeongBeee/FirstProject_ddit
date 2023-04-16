package view;

import java.awt.RenderingHints.Key;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
	private Map<String, String> choiceSite;
	private Map<String, String> choiceExam;
	private Map<String, String> choiceTurn;

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
		System.out.print("메뉴를 선택하세요 ▶︎︎ ");
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
		System.out.println("\n——————————————————— ✼ 회원가입 ✼ ————————————————————");
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
		System.out.println("\n———————————————————— ✼ 로그인 ✼ ————————————————————\n");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		System.out.println("\n—————————————————— ✼ 로 그 인 중 ✼ ——————————————————");
		return new ExamineeVO(id, password);
	}

	/**
	 * 로그인을 완료한 사용자가 접수를 하거나 접수내역을 조회하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 접수하기, 2: 접수내역 조회
	 */
	public String registerMenu(Scanner scanner) {
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n");
		System.out.println("\t      1. 접수하기 | 2. 마이페이지");
		System.out.print("메뉴를 선택하세요 ▶︎ ");
		return scanner.nextLine();
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
		System.out.print("| ");
		for (String key : createExamSiteMap().keySet()) {
			System.out.print(createExamSiteMap().get(key) + " | ");
		}
		System.out.println();
		System.out.print("수험장의 이름과 지역코드를 입력하세요(ex. 서울(SE)) ▶︎ ");
		String choiceSiteName = scanner.nextLine(); // 도시 입력
		String choiceExamSite = choiceExamSite(choiceSiteName); // 서울 -> SE 반환

		System.out.println("\n—————————————————— ✼ 과목 선택 ✼ ————————————————————");
		System.out.print("| ");
		for (String key : createExamCodeMap().keySet()) {
			System.out.print(createExamCodeMap().get(key) + " | ");
		}
		System.out.println();
		System.out.print("응시할 과목의 이름과 코드를 입력하세요 (ex. 토목기사(ECE)) ▶︎ ");
		String choiceExamName = scanner.nextLine(); // 과목을 입력하면
		String choiceExamCode = choiceExamCode(choiceExamName); // 해당 과목의 키값을 반환
		RegisterApplication.rSession.setExamCode(choiceExamCode); // 과목코드 설정

		System.out.println("\n———————————————— ✼ 응시 회차 선택 ✼ ——————————————————");
		System.out.print("| ");
		for (String key : createExamTurnMap().keySet()) {
			System.out.print(createExamTurnMap().get(key) + " | ");
		}
		System.out.println();
		System.out.print("응시할 회차를 입력하세요(ex. 1) ▶︎ ");
		String choiceExamTurn = scanner.nextLine(); // 회차를 입력하면? 그냥 얘가 키가되는거임!!
		RegisterApplication.rSession.setSiteCode(choiceExamSite.concat(choiceExamTurn)); // 과목코드 설정

		System.out.println("\n———————————————————— ✼ 결제 ✼ ——————————————————————");
		System.out.println("응시료는 19400원 입니다.");
		System.out.println("1. 신용카드 | 2. 계좌이체 | 3. 휴대폰 결제");
		System.out.print("결제 방식을 선택하세요.▶ ︎");
		scanner.nextInt();

		RegisterApplication.rSession.setId(RegisterApplication.eSession.getId()); // 아이디 설정
		RegisterVO registerExaminee = new RegisterVO(RegisterApplication.rSession.getId(),
				RegisterApplication.rSession.getExamCode(), RegisterApplication.rSession.getSiteCode());
		int insertRegister = rService.insertRegisterInfo(registerExaminee);
		if (insertRegister > 0) {
			System.out.println("접수가 완료되었습니다.");
		} else {
			System.out.println("접수를 실패하였습니다. 접수 페이지로 돌아갑니다.");
		}
	
	}

	public Map<String, String> createExamSiteMap() { // 지역코드 - 지역명을 연결하는 맵
		Map<String, String> examSiteMap = new LinkedHashMap<>();
		examSiteMap.put("SE", "서울(SE)");
		examSiteMap.put("BS", "부산(BS)");
		examSiteMap.put("IC", "인천(IC)");
		examSiteMap.put("DG", "대구(DG)");
		examSiteMap.put("DJ", "대전(DJ)");
		examSiteMap.put("GJ", "광주(GJ)");
		examSiteMap.put("US", "울산(US)");
		examSiteMap.put("GG", "경기도(GG)");
		examSiteMap.put("GW", "강원도(GW)");
		examSiteMap.put("CC", "충청도(CC)");
		examSiteMap.put("JL", "전라도(JL)");
		examSiteMap.put("GS", "경상도(GS)");
		examSiteMap.put("JJ", "제주도(JJ)");

		return examSiteMap;
	}

	public String choiceExamSite(String choiceSiteName) { // 사용자가 서울 입력하면 SE 반환함.

		choiceSite = new HashMap<>();
		String choiceSiteCode = getKey(createExamSiteMap(), choiceSiteName);
		choiceSite.put(choiceSiteCode, choiceSiteName); // 선택된 밸류값의 키값을 얻어서 choiceSIte의 키로,
		// choiceSiteName을 밸류값으로 가지는 맵 생성

		return choiceSiteCode; // 서울 -> SE 반환
	}

	/**
	 * 과목을 선택하는 뷰
	 * 
	 * @return 선택된 과목
	 */
	public Map<String, String> createExamCodeMap() {
		Map<String, String> examMap = new LinkedHashMap<>();
		examMap.put("ECE", "토목기사(ECE)");
		examMap.put("ELA", "조경기사(ELA)");
		examMap.put("EGS", "가스기사(EGS)");
		examMap.put("EEW", "전기공사기사(EEW)");
		examMap.put("EET", "전자기사(EET)");
		examMap.put("EWD", "용접기사(EWD)");
		examMap.put("ECI", "화공기사(ECI)");
		examMap.put("EAT", "건축기사(EAT)");
		examMap.put("EMM", "자동차정비기사(EMM)");
		examMap.put("EIP", "정보처리기사(EIP)");

		return examMap;
	}

	public String choiceExamCode(String choiceExamName) {
		choiceExam = new HashMap<>();
		String choiceExamCode = getKey(createExamCodeMap(), choiceExamName);
		choiceExam.put(choiceExamCode, choiceExamName);

		return choiceExamCode; // choiceExamName의 키값을 반환
	}

	/**
	 * 시험 회차를 선택하는 뷰
	 * 
	 * @return 선택된 회차
	 */
	public Map<String, String> createExamTurnMap() {
		choiceTurn = new LinkedHashMap<>();
		choiceTurn.put("1", "1회차(2023년 3월 15일 오전 9시)");
		choiceTurn.put("2", "2회차(2023년 6월  4일 오전 9시)");
		choiceTurn.put("3", "3회차(2023년 7월 23일 오전 9시)");
		return choiceTurn;
	}

	public String choiceExamTurn(String choiceExamTurn) {
		Map<String, String> choiceTurn = new HashMap<>();
		String choiceTurnKey = getKey(createExamTurnMap(), choiceExamTurn);
		choiceTurn.put(choiceTurnKey, choiceExamTurn);
		System.out.println(choiceTurnKey);

		return choiceTurnKey;
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
	public String registerCheckMenu(Scanner scanner) {
		System.out.println("\n—————————————————— ✼ 마이페이지 ✼ ———————————————————");
		System.out.println("1. 접수 내역 확인 | 2. 회원정보 및 접수내역 수정");
		System.out.print("메뉴를 선택하세요 ▶ ");
		return scanner.nextLine();
	}

	/**
	 * 접수된 시험을 확인하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @throws Exception
	 */
	public void showRegister() throws Exception { // 1. 접수 내역 확인 - 황금색
		System.out.println("\n——————————————————— ✼ 접수 내역 ✼ ———————————————————");
		List<RegisterVO> registerInfos = rService.selectRegisterInfos(RegisterApplication.getrSession().getId());
		for (RegisterVO registerVO : registerInfos) {
			System.out.println(registerVO);
		}

		if (RegisterApplication.rSession.getExamCode() == null) {
			System.out.println("접수한 시험이 없습니다.");
		}

		System.out.println();
		System.out.println("* 지역 코드: " + createExamSiteMap().values());
		System.out.println("* 수험장 코드: " + createExamCodeMap().values());
		System.out.println("* 회차별 일시: " + createExamTurnMap().values());

		System.out.println("———————————————————————————————————————————————————");
		System.out.println("\n마이페이지로 돌아갑니다.\n"); // registerCheckMenu로 돌아감
		System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	}

	/**
	 * 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 1: 회원 정보 / 2. 접수 정보
	 */
	public String modifyinfoMenu(Scanner scanner) { // 2. 회원정보 및 접수내역 수정
		System.out.println("\n——————————————————— ✼ 정보 수정 ✼ ———————————————————");
		System.out.println("1. 접수 취소 | 2. 회원 정보 수정");
		System.out.print("수정할 항목을 선택하세요 ▶︎ ");
		return scanner.nextLine();
	}

	/**
	 * 회원 정보 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 ExamineeVO 객체를 반환함
	 * @throws Exception
	 */
	public void modifySignInfoMenu(Scanner scanner) throws Exception { // 2-1 회원 정보 수정
		System.out.println("\n———————————————— ✼  회원 정보 수정 ✼ —————————————————");
		System.out.println("1. 비밀번호 | 2. 전화번호 | 3. 이메일 | 4. 회원 탈퇴");
		System.out.print("수정할 항목을 선택하세요 ▶ ");
		switch (scanner.nextLine()) {
		case "1":
			System.out.print("새로운 비밀번호를 입력하세요 ▶ ");
			RegisterApplication.eSession.setPassword(scanner.nextLine());
			int resetPassword = eService.updatePassword(RegisterApplication.eSession);
			if (resetPassword > 0) {
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
			}
			break;

		case "2":
			System.out.print("새로운 전화번호를 입력하세요 ▶ ");
			RegisterApplication.eSession.setTelNo(scanner.nextLine());
			int resetTelNo = eService.updateTelNO(RegisterApplication.eSession);
			if (resetTelNo > 0) {
				System.out.println("전화번호가 성공적으로 변경되었습니다.");
			}
			break;

		case "3":
			System.out.print("새로운 이메일을 입력하세요 ▶ ");
			RegisterApplication.eSession.setEmail(scanner.nextLine());
			int resetEmail = eService.updateEmail(RegisterApplication.eSession);
			if (resetEmail > 0) {
				System.out.println("이메일이 성공적으로 변경되었습니다.");
			}
			break;

		case "4":
			System.out.print("회원을 탈퇴하면 회원 정보와 접수 내역이 모두 삭제됩니다.");
			System.out.print("정말 탈퇴를 진행하시겠습니까? [y/n] ▶ ");
			if (scanner.nextLine().equalsIgnoreCase("y")) {
				rService.deleteOneOfRegisterInfo(RegisterApplication.rSession);
				eService.deleteMyInfo(RegisterApplication.eSession);
				System.out.println("탈퇴가 성공적으로 완료되었습니다.");
				signMenu(scanner);
				break;
			} else if (scanner.nextLine() == "n") {
				System.out.println("마이페이지로 이동합니다.");
				registerCheckMenu(scanner);
			}
			break;
		}
	}

	/**
	 * 접수 내역 중 수정할 항목을 선택하는 뷰
	 * 
	 * @param scanner 사용자의 입력을 받음
	 * @return 정보를 수정한 RegisterVO 객체를 반환함
	 * @throws Exception
	 */
	public void cancelExam(Scanner scanner) throws Exception { // 2-2 접수 정보 수정
		System.out.print("정말 모든 접수내역을 취소하시겠습니까? 취소하시면 접수를 다시 진행하셔야 합니다. [y/n] ▶ ");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			rService.deleteOneOfRegisterInfo(RegisterApplication.rSession);
			System.out.println("접수가 성공적으로 취소되었습니다.");
			registerCheckMenu(scanner);
		} else if (scanner.nextLine().equalsIgnoreCase("n")) {
			System.out.println("마이페이지로 이동합니다.");
			registerCheckMenu(scanner);
		}

	}
}