//사용자 정의 에러 구현하기
/*
BufferedReader로 변수명을 입력받아
NamingTest를 통해 정의한 변수명 조건에 어긋나면 에러 발생
*/ 
package exception.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.NamingException;
import exception.NamingTest;

public class NamingMain {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("변수명으로  쓸  문자열을  쓰시오  : ");
		
		try {
			String user = br.readLine();
			NamingTest np = new NamingTest();
			
			boolean reserved = np.isReservedWord(user);
			boolean special = np.isSpecialWord(user);
			boolean numFirst = np.isNumFirst(user);
			
			if (!(reserved && special && numFirst)) {
				System.out.print(user+"는(은) 변수명으로 사용 가능합니다.");
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace(); 
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}