package application;

import vo.ExamineeVO;

public class RegisterApplication {
	public static ExamineeVO session = new ExamineeVO();

	public static void main(String[] args) {
		FrontController main = new FrontController();
		main.process();
	}
}