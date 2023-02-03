# 예외처리 (throws) / IO(file, 스트림 개념)
```java
public void method2() {
		System.out.print("정수 입력 (0 제외) : ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			
			System.out.println("나눗셈 연산 결과 : " + (10/num));
												// ArithmeticException 발생 가능
		} catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다."); //예외처리1
		} catch(InputMismatchException e) {
			System.out.println("정수형태를 입력해야 합니다."); //예외처리2
		} catch(RuntimeException e) {
			System.out.println("두루뭉술하게 오류를 잡을 수 밖에 없음. 물론 편하지만..."
					+ "내가 진짜 무슨 에러가 났는지 모르는데, 문제를 해결하기도 힘들쟈나.\n"
					+ "그것도 그거지만 작성하는 순서가 중요하다. 모든 런타임익셉션을 잡기 때문에 "
					+ "만일 RuntimeException류의 catch 블록을 이 캐치 블록 밑에 배치하면 도달하지 못 한다..");
		System.out.println("프로그램 종료");		
	}
```

- 발생 할 오류의 종류마다 catch블럭을 작성할 수 있다.
- 위부터 아래로 진행하면서 잡기 때문에 범위가 작은 에러를 위쪽에 기술해야 한다.
- 에러 발생 후, 에러처리를 하는 방법 보다는 조건 검사를 통해 예외를 만나지 않는 쪽으로 코드를 설계하는 것이 바람직하다.
- 예외 처리 구문이 필수가 아니기 때문에 “UncheckedException”으로 분류한다.

---

### Checked Exception

- 개발자가 반드시 예외처리 구문을 작성해야 하는 예외
- 예측 불가 한 곳에서 언제 발생할지 모르기 때문에 애초에 미리 예외 처리 구문을 작성해야 함
- 주로 IO Exception, 외부매체와의 데이터 교환? 과정에서 일어나는 에러가 이 종류의 예외에 속한다.

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

System.out.print("아무거나 입력해보세요 : ");
		try {
			String str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // BufferedReader로 입력을 받으면 io 예외가 발생할 수도 있음을 컴파일 오류로써 알려줌(빨간밑줄)
```

---

### throws

- 권장은 try ~catch
- throws는 떠넘기기, 그럼 누구에게 떠넘길 것인가? ⇒ **이 method를 호출한 곳에 떠넘기겠다.**

```java
public class CheckedException {
	public void method1() throws IOException{
		method2();
	}
	// CheckoedException 유발
	public void method2() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			
		System.out.print("아무거나 입력해보세요 : ");
		String str = br.readLine();
		System.out.println("문자열의 길이 : " + str.length());
	}
}

// main에서도 만약에 애러를 던지면 jvm이 e.printStackTrace()로 처리해준다. 
// 위 상황이 권장되는 상황은 아님

public class Run {

	public static void main(String[] args) { 

	CheckedException ce = new CheckedException();
			
		try {
			cd.method1();
		} catch (IOException e) {
			System.out.println("여기가 예외처리부");
			System.out.println("-----------");
		}

```

> 왜 에러처리가 의무화 될까?
> 

```java
// 애러를 유발하는 BufferedReader의 readLine() 의 정의부를 까보자!

public String readLine() throws IOException {
        return readLine(false);
}

// 얘도 나한테 떠넘기고 있음
```

> try ~ catch와 throws중 더 우선시 되는 것은?
> 

```java
public void method2() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			
		try {
				String str = br.readLine();
				System.out.println("문자열의 길이 : " + str.length());
		} catch(IOException e) {
	      e.printStackTrace();
		}
	}

//main

public class Run {

	public static void main(String[] args) { 

	CheckedException ce = new CheckedException();
	ce.method2(); // 에러 발생, 에러처리가 강제됨 왜? 정의부가 코드의 위에 있어서 우선시 되는 듯	

	}
}
```

---

# I/O입출력

- 컴퓨터 내/외부 장치와 프로그램 간의 데이터를 주고 받는 것
- 주체가 무엇인지에 따라 입/출력의 방향이 달라짐(프로그램 기준으로 사용자가 입력, 프로그램이 출력)
- 장치와의 입출력을 위해서는 하트웨어 장치에 대한 **직접 접근**이 필요
- 직접 접근을 위한 통로를 스트림(Stream / 자바에서는 클래스로 구현되어 있다.)이라고 한다
- 그 말인 즉, 연결을 위해서는? 입/출력 스트림 객체를 생성하면 된다.
    - 궁금한게, 입출력이 동시에만 안되는건가? 아니면 입력스트림 출력스트림이 역할에 따라 필요한건가? 시간이 문제인지 기능이 문제인지 - 이건 클래스 뜯어보면 되겠지 뭐

---

가장 낮은 난이도의, 대표적인 “파일” 관련된 기능을 먼저 살펴보자

### 파일

- 프로그램이 실행되면 프로세스(동적인 상태)로서 실행되고, 이는 휘발성 메모리에 올라간다.
- 프로그램이 종료되면 당연히, 데이터가 유지되지 않는다.
- java.io.File을 활용한다.(주의: **file객체를 생성한다고 해서 파일이 실제 만들어 지는 것은 아니다.**)
- 객체.createNewFile(); 을 통해 실제 파일을 생성할 수 있다. 뭔지 알겠지??? ㅇㅇ!!!!

```java
public class FileRun {

	public static void main(String[] args) {
		// 파일만드는 과정 및 메소드
		// java.io.file 클래스를 통해 작업
		
		// 주의 : file 객체를 만들었다고 해서 파일이 실제 만들어지는 것은 아님.
		
		
		try {
			// 별도의 경로  지정 없이, 파일명(test.txt.)만 제시해서 생성해보기
			// => 현재 작업중인 프로젝트 폴더 내부에 생성됨, (상대경로에 따라 생성)
			File file1 = new File("test.txt");
			file1.createNewFile();
			//워크스페이스\프로젝트\test.txt가 생성되었다.
			//이클립스는 폴더구조가 바뀌면 실시간 반영이 안 되기 때문에, Refresh로 새로고침 해준다.
			
			// 경로지정을 한 파일명(C(루트파일):\\aaa\\test.txt)를 제시해서 생성해보기
			// ('\'는 이스케이프 문자로, \n은 개행문자, \t는 텝을 뜻한다. 이스케이프 문자는 단독으로 활용할 수 없다. 
			// 그래서 \역슬레시를 하나 더 추가해서 사용해준다 '\\'이렇게)
			//=> 윈도우 컴은 보통 역슬레시로 표현, 없는 경로를 지정하면 지정된 경로를 찾을 수 없다는 에러로그를 출력하고 프로그램이 종료된다. 
			File file2 = new File("C:\\aaa\\test.txt");
//			file2.createNewFile();
//			
			// 3. 자바코드로 폴더를 생성 한 후, 그 안에 파일을 생성하는 방법
			// => 이거 동시에는 안되나? 안되네..?
			File bbbFolder = new File("C:\\bbb");
			bbbFolder.mkdir(); //make directory
			
			// 3. 자바코드로 폴더를 생성 한 후, 그 안에 파일을 생성하는 방법
			File bbbFolder = new File("C:\\bbb");
			bbbFolder.mkdir(); //make directory
			
			File file3 = new File("C:\\bbb\\test.txt");
			file3.createNewFile();
			
			// 4. 별도의 경로 지정 없이 폴더 생성 후 파일 생성하기 
			// => 현재 작업중인 이 프로젝트 폴더 안에 바로 생성
			File folder = new File("test");
			folder.mkdir();
			
			File file = new File("test\\test.txt"); //상대경로 
			file.createNewFile();
			
			System.out.println(folder.isFile()); // false
			System.out.println("file 이름 : " + file.getName()); //test.txt
			System.out.println("상위폴더 : " + file.getParent()); //부모파일 경로 여기는 test
			System.out.println("파일용량 : " + file.length()); //파일용량 아무 내용도 없으니 0이 되겠지.
			System.out.println("절대경로 : " + file.getAbsolutePath()); //C:\01_Java-workspace\12_IO\test\test.txt
			
			file1.delete(); // 파일 삭제

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("프로그램 종료");
	}

}
```

[자바 NIO](https://brunch.co.kr/@myner/47)

*이미 존재하는 파일이라면, 새로 생성되지 않고 넘어간다.

### I/O 입출력

- Input, Output 입출력을 나타내는 단어
- 프로그램 상의 데이터를 외부매체(모니터, 스피커, 파일 등)으로 출력하거나
- 입력장치(키보드, 마우스, 마이크 등) 으로 입력받는 과정을 진행하고자 한다면, 반드시 “통로”를 만들어야 한다.
- 통로를 Stream 이라고 한다.(일방임)

### 스트림의 특징

- 입력이면 입력, 출력이면 출력 ⇒ 입력용, 출력용 스트림이 따로 존재한다. (역할이나 기능에 따라서 )
- 즉 동시에 입출력을 하고 싶다면 하나의 스트림으로는 안된다.
- 선입선출(FIFO) - “큐(Que)” 구조(반대로는 선입후출, Stack구조)
- 시간지연문제가 발생할 수 있다.

> 스트림은 클래스로써 제공되고 있음
> 

### 스트림의 구분 기준

- 통로의 **사이즈**
    1. 바이트 스트림 - 한 번에 1byte만 이동할 수 있는 좁은 통로
        
        ⇒ 입력(xxxInputStream) / 출력 (xxxOutputStaream)
        
    2. 문자 스트림 - 2byte(char만큼)짜리가 이용할 수 있는 넓은 통로
        
        ⇒ 입력(xxxReader) / 출력(xxxWriter)
        
    - 둘의 차이는 표현 가능한 데이터의 범위(1바이트는 -128~127까지만 들어갈 수 있다.)
- 외부 매체와의 **직접 연결 여부**(통로에 연결하는 또 다른 통로인가)
    1. 기반 스트림 : 외부 매체와 직접적으로 연결되는 메인 통로
    2. 보조 스트림 : 기반 스트림 만으로 부족한 성능을 향상시키기 위한  통로
        - 외부매체와 직접 연결이 일어나지 않으며, 기반스트림을 보조하는 역할
        - 단독으로 사용 불가능 하다. (BufferedReader가 매개변수로 InputStreamReader의 객체를 필요로 하는 것 처럼)
        - **속도 향상, 자료형에 맞게 변환, 객체 단위로 데이터 입출력을 돕는 다거나,** …(자료형은 어떻게 알지)
            - 보조스트림은 단독으로 절대 사용 불가하기 때문에 반드시 외부 매체와 직접적으로 연결되는 기반 스트림을 필수적으로 생성 한 후에 생성해야 한다.

> **DAO는 무슨 뜻일까?**
> 
> 
> 기능을 담는 역할의 클래스는 controller
> 
> 정보를 담는 역할의 클래스는 vo 패키지에 저장하였다. 
> 
> Data Access Object의 줄임말로 데이터가 저장된 공간에 직접 접근해서 데이터를 입출력 하는 용도의 클래스를 모아두는 패키지를 말한다. 
> 

- **FileOutputStream**
    
    ```java
    public void fileSave() {
    		//인풋 아웃풋 기준은 프로그램에서 xx로(파일, 등) 처리하면 된다. 
    		try {
    			// FileOutputStream : "파일"로 데이터를 1byte 단위로 출력하는 스트림
    
    			// 1. 스트림(통로) 생성 => 어떤 파일과 스트림을 연결할 것인지 매개변수로 넘겨줘야함.
    			// 만일 파일이 존재하지 않으면, 파일 생성 후 스트림을 연결한다.(파일이 존재하면 스트림을 연결한다.)
    			FileOutputStream fout = new FileOutputStream("a_byte.txt");
    			// 생성자의 종류는 여러개 있는데, boolean append (는 이어쓰기, false가 기본값)를 포함하고 있는 생성자도 존재한다. 
    			// 만일 생성자의 두 번째 매개변수로 true를 작성하지 않거나 false를 넘긴다면, 그 경우에는 덮어쓰기가 된다. 
    			
    			// 2. 연결통로로 데이터를 출력 : write() 메소드 사용
    			// => 1바이트 범위의 값만 스트림을 통해 이동할 수 있다. 
    			// 단, 파일에 기록되기를 해당 숫자의 고유한 문자(아스키코드표)가 기록된다.
    			// 음수는 불가!(아스키코드에 없기 때문)
    			fout.write(97); //a
    			fout.write('b');
    			
    		// } catch (FileNotFoundException | IOException e) { // 이렇게도 쓸 수 있지만,
    		// "The exception FileNotFoundException is already caught by the alternative IOException" 
    		// 컴파일 애러가 발생한다. 
    
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) { 
    			// 얘가 근데 벌써 처리가 되면, 자잘한 애들은... 구체적으로 못 잡잖아
    			e.printStackTrace();
    		}
    
    			//3. 스트림을 다 이용했으면 자원 반납할 것(반드시)
    			fout.close(); 
    ```
    
    - 만일 main 함수를 통해 fileSave()를 호출하면, 파일이 생성되면서, “ab”가 기록됨
    - 하지만 재호출 하더라도 덮어쓰기가 되어 “ab”만 존재한다.
    - 근데, FileOutputStream() 생성자를 호출할 때, 매개변수가 2개인(파일 경로+이름, 이어쓰기 여부(false가 기본값)) 생성자를 호출하면서 이어쓰기 여부를 true로 지정해주면 이어쓰기가 된다.
        
         **⇒ 로그 기록 등 기록을 누적 해야할 경우에는 꼭 필요한 설정 !!!!**
        
    - write 메소드는 오버라이딩 되어있어, byte 배열을 받는다.
    - 3번째 byte배열에 든 값은 n번 index부터 m개를 기록하겠다는 뜻이다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e33bb72-74de-41ad-bc1e-76c668f96327/Untitled.png)
        
    - byteStream은 범위를 벗어나면 오버플로우에 의해 다른 문자가 들어간다.
- 스트림을 꼭 닫아줘야할까?
    
    > **입력 중 에러가 발생하더라도 스트림은 꼭 닫아줘야한다.**
    > 
    
    그래서 close() 구문을 finally에 기록 해야 한다. 그러면 try 구역 안에서 객체를 finally에서 변수를 사용할 수 없기 때문에 메소드 범위의 지역변수로 선언해주고, finally 블록 안에서는 .close()의 에러가 처리되지 않았기 때문에 다시 try ~ catch로 에러를 처리 해줘야 한다.
    
    > **GC가 있는데 왜 스트림을 닫아줘야 하냐고 물어본다면…**
    > 
    
    [OKKY - java io close() 이거 왜하는지 아시는분](https://okky.kr/articles/138656)
    
    여기 약간 사람들이 비난하는뎅…. 그냥 까라면 까 말고
    
    선생님이 말씀하신 바로는, 스트림(Stream)은 채팅 등 동시에 여러 개가 열릴 가능성 많은 자원이고, 그래서 관례상 꼭 자원을 반납하는 절차를 갖는다. 그리고, 스트림은 보조스트림이 있듯이 스트림 객체끼리 종속적일 가능성이 크다. 그러니까 반납해 주는 게 좋겠지????
    
    [OKKY - Close() 메소드에 대해서..](https://okky.kr/articles/401102)
    
- **FileInputStream**
    
    ```java
    public void fileRead() {
    		FileInputStream fin = null;
    		
    		try {
    			fin = new FileInputStream("a_byte.txt");
    			// 입력용 스트림의 경우, 없는 파일 읽어오려고 하면 에러가 발생함(당연)
    //			System.out.println((char)fin.read());
    			// .read();
    			// => 1바이트 단위로 글자 하나씩 읽어옴 7개의 글자가 저장되어 있으면, 7번 호출해야함.
    			// => 파일의 끝을 만나면 fin.read()를 하면 -1을 리턴하는 것을 이용한다.
    			int tmp = 0;
    			while((tmp = fin.read()) != -1) { //대입구문도 수식이니까 조건 구문에 넣을 수 있어 오호~!
    				System.out.print((char)tmp);
    			}
    			
    		} catch(FileNotFoundException e) {
    			e.printStackTrace();
    		} catch(IOException e) {
    			e.printStackTrace();
    		} finally { //예외가 발생 되거나 안되거나 그냥 
    			try {
    				fin.close();
    			} catch(IOException e) {
    				
    			}
    		}
    	}
    ```
    

### 참고 : 입출력 코드 공통 흐름

1. 스트림 객체를 담을 수 있는 변수 생성, null로 초기화
2. try 블럭 안에서 객체 생성
3. 프로그램 코드 작성
4. finally 블럭 안에서 다 쓴 스트림 자원 반납하기 

---

## 정리

바이트 기반 스트림이란?

- 기반 스트림 (외부매체와의 직접 연결 여부): 외부매체와 직접적으로 연결되는 메인 통로
- 바이트 스트림 (통로의 크기) : 1 byte 단위로만 입출력 할 수 있는 좁은 통로
- “바이트 기반 스트림” 이란 : 외부 매체를 지정하고 그 외부매체와 직접적으로 연결되는 1byte단위의 좁은 통로를 만들겠다.
- xxxInputStream / xxxOutputStream)
    - xxxInputStream : xxx라는 매체로부터 데이터를 입력 받는(가져오는) 1byte 짜리 통로 → 외부 매체로부터 데이터를 가지고 오겠다, 읽어 들이겠다.
    - xxxOutputStream xxx라는 매체로 데이터를 출력하는(내보내는) 1byte 짜리 통로 → 외부 매체로 데이터를 내보내겠다, 쓰겠다.