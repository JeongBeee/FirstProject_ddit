package Test;

import java.util.Scanner;

public class choiceExamTurn {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
		String[] examTurns = new String[3];
		examTurns[0] = "1. 23/03/15 오전 9시";
		examTurns[1] = "2. 23/06/04 오전 9시";
		examTurns[2] = "3. 23/07/23 오전 9시";
		System.out.println("------------------------------------------------------------");

		for (int i = 0; i < examTurns.length-1; i++) {
			System.out.print(examTurns[i] + " | ");
		}
		System.out.println(examTurns[2]);
		
		System.out.println("------------------------------------------------------------");
		System.out.print("응시할 시험 회차를 선택하세요 > ");
		int choiceExamTurn = Integer.parseInt(scanner.nextLine());

		//String confirmExamTurn = null;
	
		String confirmExamTurn = examTurns[choiceExamTurn - 1].substring(3);

		System.out.print("<" + confirmExamTurn + "> 시험을 선택하셨습니다.");
	}
}

