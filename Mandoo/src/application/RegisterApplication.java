package application;

import vo.ExamineeVO;
import vo.RegisterVO;

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
}
