package com.kim.variable;

import java.util.Scanner;

//사용자가 키보드로 입력한 값을 변수에 담는 방법
public class B_keyboardInput {
	
	public void inputTest1() {
		
		/*
		 * 자바에서 이미 제공하고 있는 java.util.Scanner 클래스가 있음
		 * Scanner 클래스 안에는 사용자로부터 키보드로 입력받을 수 있게 해주는 메소드들이 작성되어 있다. 
		 */
		
		Scanner sc = new Scanner(System.in);
		// System.in: 입력받은 값을 바이트 단위로 읽어들이겠다. 
		// 		 	  참고로 출력시 System.out을 썼었음.
		
		// 사용자의 인적사항(이름, 나이, 키) 입력받기
		
		//입력 받고자 하는 내용을 먼저 출력해서 정확한 입력을 유도할 것
		System.out.print("이름이 뭐에요? ");
		// 이름 : ㅇㅇㅇ -> String이니까 String으로 받아야겠지
		// 사용자가 입력한 값을 문자열로 받아오는 메소드 : next(), nextLine() 

		//next() : 사용자가 입력한 값 중 공백이 있을 경우,그 공백 이전까지만 입력을 받는다.
		//nextLine() : 사용자가 입력한 값 중 개행 이전의 공백을 전부 입력받는다. 사용자의 입력에 공백이 있을 경우 안전하게 nextLine()을 쓰자.
		String name = sc.nextLine();
		
		System.out.print("나이는요? ");
		//nextInt() : 사용자의 입력값을 int형으로 받아와라
		int age = sc.nextInt(); 
		
		System.out.print("키는요? ");
		float height = sc.nextFloat(); 
		
		//버퍼에 남아있는 개행문 2개
		
		System.out.print("성별은?");
		String gender = sc.nextLine();
		//버퍼를 전부 비워주니까 2개를 다 비워줘~! 시원 
		
		System.out.print("버퍼테스트1");
		String q1 = sc.nextLine();
		
		System.out.print("버퍼테스트2");
		String q2 = sc.nextLine();
		
		System.out.printf("%s님은  %d세 이고, 키는 %.1f입니다 %s이다. %s이다..  %s이다...", name, age, height, gender, q1, q2);
		
		sc.close();
			
	}
	
	public void inputTest2() {
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.print("이름이 뭐에요? ");
//		String name = sc.nextLine();
//		
//		System.out.print("나이는요? ");
//		int age = sc.nextInt();
//		
//		System.out.print("성별은요? ");
//		String gender = sc.nextLine();
//		
//		System.out.print("키는요? ");
//		float height = sc.nextFloat();
//		
//		System.out.printf("%s님은  %d세 %s성이고, 키는 %.1f입니다.", name, age, gender, height);
//		sc.close();
		
		/*
		 * 위와 같이 입력시 애러 발생.
		 * 50 번째 줄의 nextInt()가 개행문자를 제거하지 않고 남겨두기 때문에
		 * 성별을 입력받을 때, 바로 개행문이 입력되어서 ""이 입력되고 다음으로 넘어감
		 * next() 계열은 개행문 전까지만 처리하기 떄문에
		 * 처리되지 않은 개행문이 성별을 입력받는 스캐너에서 처리되어 성별을 빈 칸으로 만든다.
		 * 개행문 처리 여부가 이런 차이를 만드는 것.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름이 뭐에요? ");
		String name = sc.nextLine();
		
		System.out.print("나이는요? ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("성별은요? ");
		String gender = sc.nextLine();
		
		System.out.print("키는요? ");
		float height = sc.nextFloat();
		sc.nextLine();
		
		System.out.printf("%s님은  %d세 %s성이고, 키는 %.1f입니다.", name, age, gender, height);
		sc.close();
	}
	
}