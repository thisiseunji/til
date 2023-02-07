## TCP

- Transmission Control Protocol의 약자
- 서버, 클라이언트 간의 1:1 소켓 통신 방식 중 하나
- 데이터를 교환하기 이전에 항상 서버, 클라이언트에 연결이 되어있어야만 한다.
    
    ⇒ 항상 서버가 먼저 실행되어, 요청을 받을 수 있는 대기 상태에 있어야 한다.
    
- 데이터를 잘 받았는지에 대한 확인 과정이 매번 데이터를 보낼 때 마다 일어난다.
    
    ⇒ 신뢰성이 있는 데이터 전송 방식


### 소켓 Socket

- 프로세스(실행 중인 프로그램)간의 통신을 담당
- Socket 객체에서는 통신을 위한 기반스트림인 Input/OutputStream을 제공하고 있음(1byte)
- 기반스트림만으로는 제한이 있기 때문에, 보조스트림을 덧붙여 성능을 향상 시킬 것

### ServerSocket

- 포트번호와 연결(바인딩)되어 외부의 연결 요청을 기다리다가 수락해주는 역할

** 소켓은 user어플리케이션이랑 TCP통신 사이의 인터페이스 라고 했으니까. 포트번호와 바인딩 되어야 하는게 당연히 필요

- 수락하는 순간, 서버 측에 소켓 객체가 생성됨.

**프로그램 실습 진행 단계**


1. 서버가 항상 대기하는 상태로 만들어 준다 → ServerSocket 객체(대기 상태로 만들어주는 역할)
2. 클라이언트가 서버에 접속을 시도 → Socket 객체를 통해 요청을 보낸다.(접속 요청을 보내는 객체)
3. 서버가 클라이언트의 요청을 수락
4. 수락하는 순간 연결이 일어난 것이고(Socket 생성), 스트림을 열 수 있다. 
5. 데이터를 전송한 후
6. 자원 반납(스트림, 소켓)

### 서버측 프로그램 코딩 순서

1. 포트 번호 지정(서버 측에서 몇 번 포트로 통로를 열 것 인지를 지정하는 것, 서버 컴퓨터에는 고유한 IP번호가 있으니 IP번호를 설정해줄 필요는 없다)
2. ServerSocket 객체를 생성 - 매개변수로 포트 번호를 넘긴다. ⇒ 포트 결합(binding)
    
    ** 이 단계부터 연결을 언제든지 받을 수 있는 대기 상태가 된다.
    
3. 클라이언트로부터 연결 요청이 올 경우, 요청을 수락한 후, 해당 클라이언트의 요청을 수락하는 순간 서버 측 소켓 객체가 생성됨(ServerSocket 객체가 수락 메소드의 결과물로 리턴)
4. 3단계에서 만들어진 소켓 객체를 통해 입출력 스트림을 생성
5. 소켓 객체가 내장하고 있는 스트림이 1byte통로이기 때문에, 보조스트림을 추가하여 성능 개선
    
    ⇒ 1바이트, 2바이트 보조스트림과 기반스트림을 호환시켜주는 보조스트림을 추가로 사용할 수도 있음.
    
6. 스트림을 통해 읽고 쓰기(데이터 입/출력 진행)
7. 자원반납

### 클라이언트 프로그램 코딩 순서

1. 요청하고자 하는 서버의 IP주소, Port번호를 알 것
2. 서버에 연결 요청을 보내는 구문 작성(요청하고자 하는 서버의 IP, Port번호를 언급해야함)

** 연결이 잘 됐을 경우와 거부 당했을 경우를 고려하여 처리한다. 3번 부터는 Socket 객체가 잘 생성된 경우

1. 서버와 통신할 수 있는 입출력 스트림 생성
2. 보조스트림을 추가하여 성능 개선
3. 스트림을 통해 읽고 쓰기(데이터 입/출력 진행)
4. 자원반납

[TCP/IP Socket 통신 프로그래밍 (Java Sample 소스코드)](https://smilek1225.tistory.com/11)

1. 소켓 요청을 막을 수는 없나? accept에 조건 지정하면 되는데 어떻게 하지?

---

```java
//server

package tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//서버측 프로그램
public class ServerProgram {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		// 사용자에게 입력받은 내용을 클라이언트에 전송
		ServerSocket server = null;
		BufferedReader br   = null;
		PrintWriter pw      = null;
		// 1. 서버측 프로그램이 몇 번 포트를 사용할 것인지 지정
		int port = 3000;
		try {
			// 2. ServerSocket 객체  생성 및 포트 결합 - 바인딩
			//=> 항상 대기를 하다가, 클라이언트의 연결 요청을 수락해줄 용도의 객체
			//=> 일반 소켓과는 다르다.(스트림을 생성하는 것은 일반소켓이 하는 일)
			server = new ServerSocket(port);
			//----클라이언트의 연결 요청을 받아줄 수 있음----
			// 클라이언트로부터 접속 요청이 올 때 까지 대기상태임을 콘솔에 출력
			System.out.println("클라이언트의 요청을 기다리고 있습니다.");
			
			//3. 연결요청이 오면 요청 수락 후, 해당 클라이언트와 통신할 수 있는 서버측 Socket 객체 생성
			// => 수락용 메소드를 통해 서버측 Socket 객체를 생성한다. => 조건을 통해 소켓통신을 막을 서버도 지정할 수 있겠다.? 과연 그럴 수 있는가?
			Socket socket = server.accept(); 
			//근데 뭐에 대한 연결을 연건지를 어떻게 알지?
			
			//socket 객체를 통해, 누가 나에게 연결요청을 했는지를 알아낼 수 있다.
			// => 소켓에서 제공하는 메소드로 'InetAddress'객체를 얻어낼 수 있다. 
			// InetAddress : 컴퓨터 하나의 정보를 담고 있는 
			System.out.println(socket.getInetAddress().getHostAddress() + "가 연결을 요청함.");
								//연결  신청을 허용 한 하나의 클라이언트의 컴퓨터 정보들 중, IP주소
			
			// 4. 클라이언트와 통신할 수 있는 입출력 스트림 생성
			// 5. 보조스트림을 추가하여 성능 개선
			// 채팅 프로그램 생성을 위해서는 입/출력 스트림을 동시에 생성해야한다.
			
			// 입력용 스트림 (서버프로그램 입장에서, 클라이언트로부터 전달된 값을 한 줄 단위로 읽어들이기 위한 스트림)
			// socket.getInputStream() : 사이즈 1byte
			// 속도향상, 한글 전달을 위한 입력용 보조스트림 => BufferedReader : 사이즈 2byte
			// => 둘은 사이즈 때문에 호환이 안된다. 호환을 위한 보조스트림을 추가로 사용해야 한다. 
			// => 보조스트림 InputStreamReader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 출력용 스트림(클라이언트에게 데이터를 내보내기 위한 스트림)
			// PrintWriter : 애초에 호환성이 좋게 설계되어 1,2byte 기반스트림과 모두 연결될 수 있는 출력용 보조스트림
			pw = new PrintWriter(socket.getOutputStream());
			
			// 클라이언트오 지속적으로 데이터를 추고받을 수 있도록
			// 반복문을 통해 채팅 프로그램 구현
			while(true) {
				// 6. 스트림을 통해 읽고 쓰기
				// 클라이언트로부터 전달된 메시지가 있을 경우, 서버측에서 읽어들이기(input)
				String message = br.readLine(); // 얘는 여러개 올 수 도 있다면, 반복문 안에서 또 반복문으로 구현하면 되겠지?
				System.out.println("클라이언트로부터 받은 메세지 : " + message);
				
				// 클라이언트에게 전달 (output)
				System.out.print("클라이언트에게 보낼 내용 : ");
				String sendMessage = sc.nextLine();
				pw.println(sendMessage); // 클라이언트에게 한 줄 단위로 출력
				
				pw.flush();
				// 현재 스트림에 있는 데이터를 강제로 내보내는 메소드(하나가 안 가면 쭉 밀어버림)
				// 자원반납이 일어나는 순간, 자동으로 flush가 일어났지만, 혹시 모르니 flush()를 명시적으로 사용 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 8. 생성된 순서의 역순으로 반납
			// 반납할 객체 기준 : 내가 직접 생성한 객체이면서 변수에 담아준 객체
			try {
				sc.close(); 
				pw.close();
				br.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
```

```java
//client

package tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProgram {
	
	public static void main(String[] args) {
		// 1. 요청하고자 하는 서버의 IP주소ㅎ와 포트번호 지정
		// 요청하고자 하는 서버의 IP주소 : 192.168.40.59
		// 127.0.0.1 루프백 ip (자기 자신의 ip주소를 지칭) == localhost
		// 요청하고자 하는 서버의 port번호

		String serverIP = "000.000.00.00"; //127.0.0.1 루프백 ip 자기 자신의 ip주소를 지칭하는 애 == localhost
		int port        = 3000;
		
		Socket socket     = null;
		BufferedReader br = null;
		PrintWriter pw    = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			// 2. 서버에 연결 요청 보내기 => socket 객체 생성 구문으로 연결 요청 보내기
			// 매개변수로 요청하고자 하는 서버의 IP주소와 포트번호를 넘기면서 요청을 보냄
			socket = new Socket(serverIP, port);
			// 만약 socket에 있는 값이 null이 아니라면, 연결이 잘 된 것
			
			
			System.out.println(socket);
			
			if (socket != null) {
				System.out.println("서버와 연결 성공!");
				
				//3. 서버와 통신할 수 있는 입출력 스트림 생성
				//4. 보조스트림을 추가하여 성능 개선
				//입력용
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//출력용
				pw = new PrintWriter(socket.getOutputStream());
				
				while(true) {
					//서버에게 데이터 전달할 수 있는 구문
					System.out.print("서버에 보낼 내용 : ");
					String sendMessage = sc.nextLine();
					pw.println(sendMessage);
					
					pw.flush();
					
					// 반대로 서버로부터 전달된  메시지를 읽어들이기
					String message = br.readLine();
					System.out.print("서버로부터 온 메시지 : " + message);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	
			try {
				pw.close();
				br.close();
				socket.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
```

- 위와 같은 코드로 스스로, 다른 컴퓨터와 통신을 각 한 번 씩  진행하였다.
- localhost로 통신 : 잘된다.
- 다른 IP와 통신 : 내가 서버 측을 담당할 때는 작동이 잘 되지만 내가 클라이언트를 담당하면 클라이언트의 socket 객체 생성 단계에서 연결이 진행되지 않고Connection timed out: connect을 이유로, java.net.ConnectException이 발생하였다.
    
- 방화벽에 문제가 있을 가능성이 있다는 얘기를 듣고
- 서버 컴퓨터의 방화벽을 우선 해제해서 시도해보니 서버 연결이 가능했다.
- 특정 포트의 TCP통신을 허용하는 인바운드 규칙을 추가하여서 연결에 성공했다.

---

에러 해결하고 ‘스레드’ 공부해서 적용해봐. 

소켓통신이라면 이것도 가능한 건가?.? - 당연 되지 않을까?

[Java TCP/IP 통신 (2)](https://mititch.tistory.com/47)

- 여러 클라이언트와 동시에 통신할 수 있도록 해봥 - 근데 그럼 클라이언트 당 최대 최소 사용하는 메모리도 할당이 되는거낙?.???? 궁금
- 한 김에 웹소켓이랑 다른 점??? 그런것도 좀 알아봐 -웹소켓은 어떤식으로 구현되는지.