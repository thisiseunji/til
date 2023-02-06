# IO(문자 기반 스트림/보조 스트림, 직렬화)/네트워크 조금

```java
....
public FileWriter(String fileName) throws IOException {
        super(new FileOutputStream(fileName));
    }
// throws는 에러를 던지는 역할만 하는 것
// 조건별로 
```

```java
package char.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCharDao {
	/*
	 * 기준점
	 * 1. 프로그램 ---> 외부매체(파일)
	 * 출력 : 데이터를 내보내서 파일에 쓰겠다. 
	 */
	public void fileSave() {
		//1. 출력용 통로 만들기(없는 파일명을 제시해도 상관 없다. 파일이 우선 만들어지고 통로가 연결되기 때문)
		FileWriter fw = null; // 지역변수이므로 반드시 초기화 해줘야하고, 객체의 기본적인 초기화 값은 null이 된다.
		try {
			fw = new FileWriter("b_char.txt");
			
			//2. 파일로 데이터를 내보내기 : write() 메소드 이용
			fw.write("와! IO 재밌다..ㅎ\n");
			fw.write('A');
			fw.write(' ');
			
			char[] cArr = {'k', 'i', 'w', 'i'};
			fw.write(cArr); // 얘는 걍 써지던가?
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//3. 연결통로 끊기 (자원반납) => "반드시 실행" => finally 블럭에 작성
			//자원 반납 코드 작성 : close() 메소드 이용
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * 기준점
	 * 2. 프로그램 <--- 외부매체(파일)
	 * 입력 : 파일로부터 읽어들이겠다. 
	 */
	public void fileRead() {
		//0.변수 생성 및 초기화
		FileReader fr = null;
		//fileReader : 파일로부터 데이터를 2byte 단위로 입력받는 스트림
		// 1. 스트림 객체 생성(통로 열기)
		try {
			fr = new FileReader("b_char.txt");
			//입력용 스트림은 반드시 존재하는 파일이어야 한다. 
			
			// 2. 파일로부터 데이터 읽어들이기 : read() 메소드 이용
			//System.out.println(fr.read()); // read()는 한 글자 단위로 읽어들이니까... 여러번 반복해야한다. 
			
			//대입연산자와 관계연산자(!=) 우선순위가 관계연산자는 7번 대입연산자는 14번이므로 
			int tmp;
			while((tmp = fr.read()) != -1) { //읽어들일 글자가 없는 경우 -1을 리턴하는 성질을 이용한다.
				System.out.print((char)tmp);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
```

---

# 보조스트림

- 기반스트림(외부매체와 직접 연결되어있는 통로)의 부족한 기능을 확장시킬 수 있는 스트림

- 단독으로 사용이 불가(즉, 단독으로 객체 생성 불가)
    
     `보조스트림클래스명 객체명 = new 보조스트림클래스명(기반스트림 객체);`
    
- 속도 성능 향상 목적의 보조 스트림 : Bufferedxxx
    
    ⇒ 버퍼 공간을 제공해서 한 번에 데이터를 모아뒀다가 한꺼번에 입출력을 진행
    
- 글자 하나 단위가 아니라 한 줄 단위로 입출력이 일어나게 돕는다.(버퍼에 모아뒀다가 한 번에 처리)

### 보조스트림 이용시 주의사항

- 기반스트림과 보조스트림의 사이즈는 일치 해야함
- 입력용 기반스트림에는 입력용 보조스트림만 붙인다.(출력도 마찬가지⇒ 기반//보조 스트림의 입력 방향이 같아야 한다.)

### 보조스트림의 활용

- 자원 생성 및 반납 순서를 주의~!
    
    (생성 시 기반-보조 / 반납 시 보조-기반 순)
    

```java
package buffered.model.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedDao {
	//BufferedReader / BufferedWriter
	// => 입력 또는 출력 시 속도를 향상시켜줌
	
	//출력
	public void fileSave() {
		// 기반스트림, 보조스트림의 사이즈와 입력/출력 방향이 일치해야한다. 
		// FileWriter + BufferedWriter 합체
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter("c_buffer.txt");
			bw = new BufferedWriter(fw);
			bw.write("이거 정말 신기하구만."); //는 버퍼에 쓰는 것임.
			bw.newLine();
			bw.write("반가워요 이렇게 하면 되나요?\n");
			bw.write("그런가요?");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close(); // 기반스트림보다 먼저 닫아줘야 한다. 
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

/*
이거 정말 신기하구만.
반가워요 이렇게 하면 되나요?
그런가요?
*/
```

- close()를 어떤 경우에는 꼭 해야 하는지 이해할 수 가 없는데..? 외부 매체랑 연결되면 close()해야 하는데 지금 fileWriter는 또 안 해도 되는 건가? 참조 변수가 없어서 괜찮은 건가?
- 자바는 jdk7 버전부터 사용 가능한 try-with-resources라는 문법이 있다.

[Java - Try-with-resources로 자원 쉽게 해제하기](https://codechacha.com/ko/java-try-with-resources/)

```java
public void fileSave() {
// 스트림 객체 생성부를 try(여기)에 적어주면 된다.
	try (BufferedWriter bw =  new BufferedWriter(new FileWriter("c_buffer.txt"))) {
			bw.write("이거 정말 신기하구만."); //는 버퍼에 쓰는 것임.
			bw.newLine();
			bw.write("반가워요 이렇게 하면 되나요?\n");
			bw.write("그런가요?");
	} catch (IOException e) {
			e.printStackTrace(); 
	}
}

/*
모든 객체가 가능한 것은 아니고
...AutoCloseable을 구현한 객체만 close()가 호출된다.
AutoCloseable은 인터페이스이며 자바7부터 지원한다.
내가 만든 클래스에도 적용할 수 있는데, 해당 인터페이스의 close()를 구현하면 된다.
*/
```

---

### 객체 단위로 입출력이 가능한 보조 스트림

- 객체 단위로 입출력 할 수 있는 보조 스트림
- 사이즈와 입력 방향이 일치하면 된다.

스트림을 통해 객체가 지나갈 수 있도록 보조 스트림이 데이터의 형태를 변경하는? 역할을 해준다. 

- 데이터와 관련된 클래스들을…
    
    모델에다가 저장하는데, VO는 데이터 자체.(아마도 객체들)
    
    DAO도 데이터에 접근해서 어떤 역할을 하는 거니까 model에 저장한다. 
    

… 

```java
//ObjectDao를 이렇게 구현하고 

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import object.model.vo.Phone;

public class ObjectDao {
	// ObjectOutputStream / ObjectInputStream (1byte 같다.)
	
	public void fileSave() {
		Phone ph = new Phone("아이폰", 1500000);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("파일명.확장자 입력: ");
		String fileName = sc.nextLine();
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
			// 객체 단위를 파일에 출력하고자 할 때, 필요한 보조스트림
			// 이름이 보조스트림은 (데이터의 종류,혹은 연결 방법)에(~를 통해) 연결하겠다 하겠다 인것같고
			// 기반스트림은  (외부매체의 종류)에 연결하겠다 인 것 같다. 
			oos.writeObject(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

```java
//Phone

public class Phone {
	private String name;
	private int price;
	
	public Phone() {}
	
	public Phone(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", price=" + price + "]";
	}
}
```

```java
//ObjectRun으로 호출

import object.model.dao.ObjectDao;

public class ObjectRun {

	public static void main(String[] args) {
		ObjectDao od = new ObjectDao();
		od.fileSave();
	}
}
```

⇒ 직렬화에 관련한 에러 발생


### 직렬화

- 객체를 파일로 저장하거나, 읽어올 때, 혹은 다른 서버에서 생성한 객체를 받을 때, 필요한 것이 `Serializable` 인터페이스!
| Serializable 인터페이스를 구현하면 JVM에서 해당 객체는 저장하거나 다른 서버로 전송할 수 있도록 해준다.(객체는 너무 커… 바이트 스트림으로 지나가기에는…)
- 자바 직렬화란 자바 시스템 내부에서 사용되는 객체, 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 byte형태로 데이터 변환하는 기술과, 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)를 아울러 얘기한다.

…

```java
A라는 서버에서 B라는 서버로 SerialDTO라는 클래스의 객체를 
전송한다고 가정하겠습니다. 전송하는 A 서버에 SerialDTO라는 클래스가 
있어야 하고, 전송받는 B 서버에는 SerialDTO라는 클래스가 있어야만 합니다.
그래야만 그 클래스의 객체임을 알고 데이터를 받을 수 있습니다.

그런데 만약 A 서버가 갖고 있는 SerialDTO에는 변수가 3개 있고, 
B 서버의 SerialDTO에는 변수가 4개 있는 상황이 발생하면 어떻게 될까요?
이러면 자바에서는 제대로 처리를 못하게 됩니다. 
따라서 각 서버가 쉽게 해당 객체가 같은지 다른지를 확인할 수 있도록 
하기 위해서는 serialVersionUID로 관리를 해주어야만 합니다.

즉 클래스 이름이 같더라도 이 ID가 다르면 다른 클래스라고 인식합니다. 
게다가, 같은 UID라고 할지라도, 변수의 개수나 타입 등이 다르면 이 경우도 
다른 클래스로 인식합니다.
```

** 객체를 주고 받는 프로그램이 모두 주고

### 직렬화 방법

- 직렬화 선언을 해준다. ⇒ Serializable 인터페이스를 구현하겠다.

```java
//...phone 객체
public class Phone implements Serializable{ //직렬화선언
	private String name;
	private int price;
//...
```

```java
// Serializable 인터페이스
public interface Serializable {
}

//비어있어서 특별히 구현할게 없어. 어떤 원리로 사용되는지는 공부해보자 위의 글을 참고하자
```

- 객체 단위로 기록되어있어 깨져 보이지만, 사실은 잘 전송 됐다는 것을 확인하기 위해 파일을 읽어오겠다.

```java
public void fileRead() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phone.txt"))) {
			Phone ph = (Phone)ois.readObject(); 
			// 이걸 어떻게 읽지? 어떤식으로 출력해? 
			// 부모 타입을 자신에게 담아야 하는 상황이라면 => 다운캐스팅 해야함
			System.out.println(ph); // toString()이 호출 되겠다.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) { 
		// 직렬화 될 때, 내가 형변환 할 수 있는 클래스를 안 가지고 있을 때,
		// 혹은 객체의 실제 클래스와 다를때????? 
		//근데 이 에러가 발생할 일이 있나???? => 라이브러리 쓸 때 있음.
		// 런타임 익셉션 종류에 해당한다.
			e.printStackTrace();
		}
	}
```

### 객체 배열 단위로 입출력 하기

```java
public class ObjectsDao {
	//프로그램 ----> 파일 : 출력
	public void fileSave(String fileName) {
		// 테스트용 객체 배열 생성
		Phone[] arr = new Phone[3];
		arr[0] = new Phone("아이폰", 1500000);
		arr[1] = new Phone("갤럭시", 1300000);
		arr[2] = new Phone("플립폰", 2000000);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			for(int i=0; i < arr.length; i++) {
				oos.writeObject(arr[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

// 파일에 저장된 객체의 수를 초과하여 read()를 호출했을 경우,
// EOFException을 발생시킨다. (IOException 클래스의 자식이다.)
// 따라서 파일의 끝을 만날때까지 read()를 호출하고, 
// 해당 에러가 발생할 경우, 처리사항을 지정해주면 되겠다.
 
	public void fileRead() {
		int cnt = 0;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phones.txt"))){
			while(true) {
				System.out.println(ois.readObject());
				cnt++;
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("총 " + cnt +"개의 객체 정보가 반환되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
}

/*
Phone [name=아이폰, price=1500000]
Phone [name=갤럭시, price=1300000]
Phone [name=플립폰, price=2000000]
총 3개의 객체 정보가 반환되었습니다.
*/
```

---

# 네트워크

[15_네트워크(Network).pdf](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b0cfe982-0851-489e-91d4-cc745ada6ce4/15_%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC(Network).pdf)

- 컴퓨터와 컴퓨터 사이의 데이터 입출력
- 서버와, 클라이언트(역할에 따라)가 존재(넓게 보면 pc, 좁게 보면 프로그램)
- 요청/응답을 통해 데이터 교환이 일어남
- 서버는 다수일 수 있고, 다수의 서버를 구분할 수 있는 식별자로서 IP주소가 존재한다.  ⇒ ipV4주소 알아내기(아래 사진참고)
    
- 컴퓨터에는 여러 프로그램이 동시에 실행되고 있기 때문에 접근하려는 프로그램을 식별하는 포트 번호도 필요하다.
- ip주소와 포트번호는 모두 유동적인 값
- 단 IP주소의 값을 바꿀 때, **같은 네트워크 범위(운영하는 사람이 지정한 범위, 네트워크를 효율적으로 운영하기 위해 관리자가 설정한 기준) 안**에서, 포트번호는 같은 컴퓨터 내부에서 중복이 일어나면 안된다.
- 클라이언트 입장에서 서버에 요청하기 위해서는, 서버의 IP주소와 Port번호를 알아야 함.
- 항상 서버는 자기 혼자서 일을 처리할 수 없음. 클라이언트 요청이 들어와야 일을 할 수 있는 구조. (고로, 서버 입장의 컴퓨터는 항상 대기 상태여야 한다.)
- 자바 클래스를 통해 ip주소를 알아낼 수 있다.
    
- InetAddress : 네트워크 정보(IP주소 관련)를 확인할 수 있는 클래스

```java
작성법 : 좀 특이하다. new로 instance를 생성하는게 아니고, 
static 메소드를 이용해 인스턴스를 반환해준다. 

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Run {
	
	public static void main(String[] args) {
		//InetAddress : 네트워크 정보(IP주소 관련)를 확인할 수 있는 클래
		//InetAddress를 리턴해주는데, 
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			// localhost : 지역 호스트 => 내 PC를 지징
			// getLocalHost() 메소드 : 내 PC에 대한 정보를 반환해주는 메소드 
			System.out.println(localhost);
			System.out.println("내 PC명 : " + localhost.getHostName());//호스트의 이름 반환
			System.out.println("내 IP 주소 : " + localhost.getHostAddress());//호스트의 IP주소 반환
			
			System.out.println("--------------내 컴퓨터 정보 지점 끝-------------");
			
			// 다른 사람의 컴퓨터 정보도 알아낼 수 있음
			// 내가 알고있는 도메인 주소를 통해서 그 서버컴퓨터와 관련된 정보도 얻어낼 수 있다.
			// 도메인 주소 : 사용자가 웹사이트에 접속할 떄, 매번 해당 서버의 ip주소를 외우기 어렵기 때문에
			// 사용자가 외우기 쉬운 형태로 사용자에게 제공되는 주소
			// 주소창에 "www.google.com"를 검색하면
			// DNS 서버에 표형식으로 매칭이 되어있고, => 도메인 주소와 매칭된 IP주소로 이동
			// getByName() 메소드 : 도메인 주소라는 고유한 주소값을 통해서 해당 서버의 정보를 얻어내서 반환
			
			InetAddress googleHost = InetAddress.getByName("www.google.com");
			
			System.out.println("구글의 서버명 : " + googleHost.getHostName());
			System.out.println("구글의 IP주소 : " + googleHost.getHostAddress());
			
			// 구글의 서버는 여러개이다. 분산구조로 이뤄져 있다.
			// 요청이 한 곳에만 몰빵 될 경우 서버에 과부하가 걸릴 수 있기 때문 => 디도스 공격이 바로 이 수법!
			System.out.println("--------------구글 정보 지점 끝-------------");
			
			// 도메인 주소를 이용해서 그 서버 관련한 정보들을 배열로 받을 수 있음
			//InetAddress의 한 객체가 서버 1대라고 생각하면 된다.
			InetAddress[] naverHost = InetAddress.getAllByName("www.naver.com");
			
			System.out.println("네이버 호스트 서버의 갯수 : " + naverHost.length);
			for(InetAddress n : naverHost) {
				System.out.println("네이버의 서버명 : " + n.getHostName());
				System.out.println("네이버 IP주소 : " + n.getHostAddress());
			}
			
			System.out.println("--------------네이버 정보 지점 끝-------------");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

/*

내 PC명 : 
내 IP 주소 : 
--------------내 컴퓨터 정보 지점 끝-------------
구글의 서버명 : www.google.com
구글의 IP주소 : 216.58.220.100
--------------구글 정보 지점 끝-------------
네이버 호스트 서버의 갯수 : 2
네이버의 서버명 : www.naver.com
네이버 IP주소 : 223.130.195.200 //유동ip 개념인 것 같다. 어떻게 식별자가 될 수 있는거지?
네이버의 서버명 : www.naver.com
네이버 IP주소 : 223.130.195.95
--------------네이버 정보 지점 끝-------------
*/
```

---

응답 결과가 항상 돌아옴. 현재 구동 중인 서버가 있다면..! ⇒ 요청과 응답을 통한 통신

웹에서의 통신 방식은 : HTTP 프로토콜 통신(HTTPS : 보안 절차가 추가된 버전)

- 순수 자바만으로 서버와 클라이언트 간 간단한 통신을 진행해본다.
- 이 때 데이터를 입출력하고자 한다면, 서버와 클라이언트 사이의 스트림이 필요함.
- 파일 통신과의 다른 점 : 외부매체로서 다른 컴퓨터를 지정하겠다. ⇒ 보조스트림의 개념은 아니고, **소켓(일종의 인터페이스인가???)**이 각각 있어야 한다.
- 소켓은 실행중인 프로그램(즉 프로세스)간 통신을 담당하는 인터페이스?
- 즉 스트림을 연결하기 위한 대문. user 단계에 필요해

소켓 프로그래밍의 방식으로는 (TCP/UDP 방식이 있는데)

코드를 잘 짤 필요는 없지만, 개념은 확실히 알고 있어야 함

- TCP : 데이터 전송 속도는 느리나, 정확, 안정적으로 전달할 수 있음
- UDP : 신속하지만 신뢰성이 없는 데이터가 전송될 가능성이 큰 데이터 전송방식

TCP는 주로 신뢰성이 요구되는 프로그램에서 사용하는 방식 예를 들어, 웹사이트, 이메일, 파일 등 - 완전 전달되지 않으면 깨져 버리니까.- TCP 이용한다!

UDP 는 주로 데이터를 빠른 속도로 전송하고자 할대, 무조건 빨리 넘겨야 할 때, 실시간 스트리밍(OTT)같은 서비스 등 
