package application;

import java.util.Scanner;

import vo.ExamineeVO;
import vo.RegisterVO;

/**
 * 
 * @author mandoone
 * @since 2023/4/15 10:05 업데이트
 */
public class RegisterApplication {
	public static ExamineeVO eSession = new ExamineeVO();
	public static RegisterVO rSession = new RegisterVO();

	public static void main(String[] args) throws Exception {
		FrontController main = new FrontController();
		main.process();
	}

	public static ExamineeVO geteSession() {
		return eSession;
	}

	public static RegisterVO getrSession() {
		return rSession;
	}
}

// 1. 탈퇴하면 회원가입페이지로 이동 
// 2. 접수 정보 수정
// 3. 마이페이지로 돌아가서 입력을 두번 해야 넘어가는거 수정