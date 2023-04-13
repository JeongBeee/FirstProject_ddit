package Test;

import java.util.Scanner;

public class choiceExamSite {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
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
				
			//	System.out.print(siteNames[9] + " | ");
			//	System.out.print(siteNames[10] + " | ");
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
			}
		}



