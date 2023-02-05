//사용자 정의 에러 구현하기
//2. 에러발생조건을 정의함(에러 메시지 전달)
/*
 * isReservedWord : 정해진 예약어와 주어진 문자열이 똑같은 경우 에러발생
 * isSpecialWord : 영문, 숫자, '$', '_'를 제외한 문자가 입력 될 경우 에러 발생
 * isNumFirst : 맨 첫 글자로 숫자가 주어질 경우 에러 발생 
 */
package exception;

import java.util.regex.Pattern;

public class NamingTest {
	String[] reservedWord = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", 
			"continue",  "default",  "do",  "double",  "else",  "enum",  "extends",  "false",  "final",  "finally",  "float",  "for",  "if", 
			"goto",  "implements",  "import",  "instanceof",  "int",  "interface",  "long",  "native",  "new",  "null",  "package", 
			"private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", 
			"throw", "throws", "transient", "true", "try", "void", "volatile", "while"};
	
	public NamingTest() {}
	
	// true면 에러가 발생
	public boolean isReservedWord(String user) throws NamingException {
		for(String word : reservedWord) {
			if (word.equals(user)) {
				throw new NamingException("변수 명명 규칙에 어긋납니다.(예약어 사용 불가능)");
			}
		}
		return false;
	}
	
	public boolean isSpecialWord(String user) throws NamingException {
		String pattern = "^([\\w$_]+)$";
		if (!Pattern.matches(pattern, user)) {
			throw new NamingException("변수  명명  규칙에 어긋납니다.(영문자와 '_', '$',  숫자  사용  가능)");
		}
		return false;
	}
	
	public boolean isNumFirst(String user)throws NamingException {
		char ch = user.charAt(0);
		if((48<=ch && ch<=57)) {
			throw new NamingException("변수  명명  규칙에 어긋납니다.(맨 앞 숫자 불가능)");
		}
		return false;
	}
}