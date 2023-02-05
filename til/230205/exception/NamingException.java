//사용자 정의 에러 구현하기
// 1. 에러 정의
package exception;

public class NamingException extends Exception{
	public NamingException() {}
	public NamingException(String msg) {
		super(msg);
	}
}
