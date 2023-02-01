# 인터페이스

### 정의

- **상수 필드와 추상 메소드 만으로 이루어진 추상  클래스의 변형체**
    - 인터페이스가 아닌 추상 클래스는 보통 일반 필드 + 일반 메소드 + 추상 클래스로 이뤄져 있다.
    
    > 인터페이스의 필드는 무조건 상수 필드, 메소드는 무조건 추상 메소드
    > 

### 특징

1. 인터페이스는 다중 상속을 허용한다.
    - 모든 메소드가 추상 메소드이기 때문. 상속 받은 곳에서 재정의(override) 되고, 해당 자식 클래스를 통해  호출된다.
    - 상수 필드는 static이므로 interface.상수명 으로 명확히 지칭하기 때문
2. 일반적인 추상 클래스 보다 좀 더 강한 규칙성, 강제성을 가진다. 

### 표현법

```java
// 클래스와 마찬가지로, public 과 default 접근제한자만 사용가능

접근제한자 interface 인터페이스명 {
	// 필드부  => 상수필드만 정의

	// 메소드부 => 추상메소드만 정의 
}
```

## 추상클래스와 인터페이스

### 공통점

1. 객체 생성이 불가능하다.  ⇒ 참조 변수로써 사용이 가능하다. (다형성 활용 가능… 얘도 리모콘이 될 수 있다는 거네)
2. 상속 (구현) 받는  자식 클래스에 추상메소드를 오버라이딩(구현) 할 수 있도록 강제성을 부여한다. 

### 차이점

1. 추상클래스는 클래스 내에 일반필드(== 멤버변수 == 인스턴스변수), 일반 메소드를 작성 가능하고, 추상 메소드가 포함되었거나 abstract 키워드로 클래스를 정의할 수 있었음
    
    하지만 인터페이스는 모든 필드는 상수, 메소드는 추상 메소드로 정의됨
    
2. 존재하는 목적이 다르다.
    - 추상 클래스 : 추상클래스를 상속 받아서 기능을 이용할 뿐만 아니라 클래스를 확장시켜서 메소드를 재정의해서 쓸 목적으로 씀
    - 인터페이스 : 클래스의 기능(메소드, 함수) 구현을 강제하기 위해서 사용한다. (즉, 구현을 강제함으로써 협업시 구현객체의 같은 동작을 보장할 수 있다.)

### 인터페이스 문법

```java
// 인터페이스는 모두 상수필드만 있기 때문에, 
// public static final을 생략하고 
// 자료형 상수명 = 초기값; 만으로 상수 선언이 가능하다. 
// 묵시적으로 쓰지 않음
//	public static final int NUM = 10; //컴파일 시점에 생성, 프로그램 시작시(전)
		int NUM = 10;

// 추상메소드 역시 묵시적으로
// public abstract 를 생략하여 정의한다.
		void sleep();
		void eat();

```

- 궁금증 : 인터페이스와 상속 받은 부모가 같은 형식의 메소드를 갖고 있으면 구현 안 해도 되나? 안해도 됨, 아래와 같은 코드 오류 안남. 생각해보면 그저 당연하다..!
    
    ```java
    public interface Basic {
    	int NUM = 10;
    	
    	void eat();
    	void sleep();
    }
    ```
    
    ```java
    public abstract class Person {
    	public void eat() {};
    	public abstract void sleep();
    }
    ```
    
    ```java
    public class Baby extends Person implements Basic{
    ...
    	@Override
    	public void sleep() {
    		super.setHealth(super.getHealth()+3);
    	}
    }
    ```
    

### extends 와 implements 키워드

- 클래스와 클래스 간의 상속 관계 : 자식 클래스 extends 부모클래스명
- 클래스와 인터페이스 간의 구현 관계 : 구현 클래스 implements 인터페이스명
- 인터페이스와 인터페이스 간의 상속 관계 :  자식 인터페이스 extends 부모 인터페이스1, 부모 인터페이스2, …

- 클래스가 interface를 implement했다면, 무조건 구현하거나, abstract로 설정하여 자손 클래스에서 구현할 수 있도록 해줘야 한다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9fd13d9c-707c-4f67-b3e0-907cdd2a83ec/Untitled.png)

구현 클래스 다이어그램에서 extends는 실선, implement가 점선으로 표현됨

---

[11_기본API.pdf](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bdf814e6-1333-47d5-9102-8f1282dcd0ec/11_%EA%B8%B0%EB%B3%B8API.pdf)

[String, Math 클래스 메소드 정리.pdf](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7515b784-f32d-4eef-a3d6-6071018f6c43/String_Math_%ED%81%B4%EB%9E%98%EC%8A%A4_%EB%A9%94%EC%86%8C%EB%93%9C_%EC%A0%95%EB%A6%AC.pdf)

# 기본 API

- 모든 클래스에는 ‘java.lang’ 패키지가 import된 상태(표기는 생략되어 있음)

## String

- 참조 자료형 ⇒ 객체로 취급받는다.
    
    ```java
    String s = new String("값"); // 생성자를 통해 문자열 담기
    
    String s1 = "값";
    ```
    
- .equals() : Object의 equals를 오버라이딩 했다. 원래 Object.equals()는 객체의 주소 값을 비교하지만, String 클래스의 경우, 만일 String형태의 매개변수가 들어올 경우, value를 비교하는 함수로 기능한다.
    
    ```java
    public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof String) {
                String anotherString = (String)anObject;
                int n = value.length;
                if (n == anotherString.value.length) {
                    char v1[] = value;
                    char v2[] = anotherString.value;
                    int i = 0;
                    while (n-- != 0) {
                        if (v1[i] != v2[i])
                            return false;
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }
    ```
    
- .hashCode(): Object의 hashCode()는 참조변수의 주소값을 10진수로 만들어반환한다. 하지만 String에서는 실제 담긴 문자열의 값을  기반으로  hashCode()를 리턴한다.
    
    ```java
    public int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char val[] = value;
    
                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }
    ```
    
    - 객체의 진짜 주소 값을 확인하고 싶다면?
        
        ```java
        	String str1 = new String("Hello"); // 생성자를 통해 문자열 담기
        	String str2 = "Hello";
        
        	System.identityHashCode(참조변수);
        
        	System.out.println(System.identityHashCode(str1));
        	// 리터럴은 같은 주소를 참조하나?
        	System.out.println(System.identityHashCode(str2));
        	System.out.println(System.identityHashCode("Hello"));
        
        /*출력 :
        1829164700
        2018699554
        2018699554
        같게나오넹? => 상수풀에 있기 때문
        */
        ```
        
        > 리터럴 제시 시, 실제 리터럴 값은 heap 내부의 StringPool(상수풀) 영역에 등록됨
        > 
        

### StringPool

- 상수풀에는 같은 내용물을 올릴 수 없음.
    
    ```java
    	String str1 = "Hello";
    	String str2 = "Hello";
    
    	System.out.println(str1==str2);
    
    	//true가 나온다.
    ```
    

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ab7d4553-67ae-4f8a-99ba-38893a9f5499/Untitled.png)

⇒ 마치 얕은 복사의 개념과 비슷

- Q. 그럼 str1의 값이 변경되면 str2도 함께 변경되는가?
    
    ```java
    str1 = new String("hello"); //얘도 마찬가지 아닐까? 결국 생성한 객체를 상수풀에 
    		str2 = str1;
    		
    		System.out.println();
    		System.out.println(str1);
    		System.out.println(str2);
    		str1 = "ee"; // 새로 생성되고, 
    		System.out.println(str1);
    		System.out.println(str2);
    ```
    
    ⇒ 아님. String 인스턴스에 저장된 문자열의 내용은 변경이 불가능함. (불변)
    
    변경시마다 새로운 스트링이 생겨나는 것
    
    ![https://user-images.githubusercontent.com/40616436/72347403-73859380-371b-11ea-8c96-c302a59c5ef3.png](https://user-images.githubusercontent.com/40616436/72347403-73859380-371b-11ea-8c96-c302a59c5ef3.png)
    
    참고 : ****[[Java 기초] String 객체를 알아보자](https://it-mesung.tistory.com/46)****
    

…string 이어서

- .concat() : 문자열에 매개변수(문자열) 더하기
    
    ```java
    String str1 = "Hell World";
    String str2 = str1.concat("!!!");
    
    // Hell World!!!
    ```
    
- .length() : 문자열의 길이 반환
- .substring(시작인덱스, 끝 인덱스) : 시작 인덱스부터, 끝 인덱스까지 반환. (원본 문자열을 변형시키는 것이 아님)
    
    ```java
    String str2 = "Hell World!!!";
    
    System.out.println(str2.substring(5));
    
    //World!!! => 시작인덱스부터 출력
    
    System.out.println(str2.substring(5, 10));
    
    //World => 끝 인덱스는 포함하지 않음
    ```
    
- .replace(’원래문자’, ‘대체할 문자’) : 해당 문자를 특정 문자로 대체
    
    ```java
    String str2 = "Hell World!!!";
    System.out.println(str2.replace('l','c'));
    Hecc Worcd!!!
    ```
    
- .trim() : 공백 없애기
    
    ```java
    String str3 = "       j a v a         ";
    System.out.println(str3.trim());
    //j a v a
    
    //replace()를 이용하면 모든 공백 제거할 수 있겠지
    // =>다만 char ch ='';로 초기화가 안되니까 String을 이용하면 되겠다. 
    
    System.out.println(str3.replace(" ", ""));
    java
    ```
    
- .toUpperCase() / .toLowerCase() : 대/소문자 변경
    
    ```java
    //...스캐너 사용시
    char a = sc.nextLine().toUpperCase().charAt(0)
    // => 연속적인 함수호출 : 메소드 체이닝
    ```
    
- .toCharArray() : String을 char[]로 변경
    
    ```java
    String str2 = "Hell World!!!";
    char[] arr= str2.toCharArray();
    
    System.out.println(Arrays.toString(arr));
    
    // [H, e, l, l,  , W, o, r, l, d, !, !, !]
    ```
    
- String.valueOf(문자 배열) : char[]을 String으로 변경 ⇒ static
    
    ```java
    char[] arr= str2.toCharArray();
    
    System.out.println(String.valueOf(arr));
    
    //Hell World!!!
    ```
    

### StringTokenizer 클래스(로직을 익혀둬라)

- 컴퓨터에서는 종종 Token을 단어라는 뜻으로 쓴다.
- String을 String[](스트링 배열)로 변경하여 관리하고자 할 때 사용한다.
- 구분자를 제시해서 해당 문자열을 분리시키는 기능을 한다. ⇒ .split()메소드와 비슷한 역할

```java
//.split("나눌 기준이 될 문자열")

String str = "Java,Oracle,JDBC,HTML,Server,Spring";
		
String[] arr = str.split(",");
		
for(String s : arr) { // for each문이라고 부르는가봉가
	System.out.println(s);
}

/*
Java
Oracle
JDBC
HTML
Server
Spring
*/
```

> **.split()과의 차이점**
> 
- • String 클래스의 split()과 비슷하지만, 배열이 아닌 **인스턴스 객체를 만들어 저장**

```java
String str = "Java,Oracle,JDBC,HTML,Server,Spring";
StringTokenizer stn = new StringTokenizer(str, ",");
System.out.println("분리된 문자열 갯수 : " + stn.countTokens());
//현재 StringTokenizer객체에 담긴 토큰의 갯수를 알아낼 수 있음

System.out.println(stn.nextToken()); // Java
//현재 들어있는 토큰 중, 첫번째 단어를 빼준다. (예를 들면 0번째 인덱스를 빼주는 것)
//...반복하면 Oracle,JDBC,HTML,Server,Spring 순으로 나오겠지?

System.out.println(stn.nextToken());
System.out.println(stn.nextToken());
System.out.println(stn.nextToken());
System.out.println(stn.nextToken());
System.out.println(stn.nextToken());

System.out.println("분리된 문자열 갯수 : " + stn.countTokens());
//		System.out.println(stn.nextToken());
// 더이상 뽑아 올 토큰이 없으면 java.util.NoSuchElementException 오류가 발생한다.

// 반복문 활용 당연가능
stn = new StringTokenizer(str, ",");

while(stn.hasMoreTokens()) {
			System.out.println(stn.nextToken());
}
```

---

# Wrapper Class

- 기본자료형의 값을 **객체 형식으로 포장**해주는 클래스를 래퍼 클래스라고 함
- 형변환, 비교연산을 하기 위한 목적으로 필요하다.
- Wrapper Class 는 [Call by Value](https://jminc00.tistory.com/8)로 작동한다.
    - 자바의 작동방식
        
        ***결론적으로 말하자면 자바는 Call by Value 로 작동한다.***
        
        ***클래스, 배열은 Call by Reference처럼 작동하는걸로 보여질 뿐이다.***
        
        ***나머지 Primitive type과 String type의 변수들은 모두 Call by Value 로 작동한다 .***
        
        자세한 내용은 링크를 읽어보자
        

기본형의 데이터를 Wrapper Class형의 자료로 변환하는 것을 “Boxing”이라고 한다. 

- 래퍼 클래스 용례

```java
public static void main(String[] args) {
			/*
			 * wrapper 클래스
			 * => 기본자료형의 값을 객체 형식으로 포장해주는 클래스를 래퍼 클래스라고 함
			 */
		//동등비교하고자 할 때,
		int n1 = 10;
		int n2 = 10;
		
		Integer i1 = new Integer(n1);
		Integer i2 = (Integer)n2; // 이것이 박싱이다. 
		
		System.out.println(i1);
		System.out.println(i2);
		//Integer Class에 toString이 "값"을 출력하도록 오버라이딩 되어있다. 
		System.out.println(i1==i2); // false
		System.out.println(i1.equals(i2)); //Integer Class에

		System.out.println(i1.hashCode()); // 10 => 내용물 기준으로 십진수화
		
		System.out.println(i2.compareTo(i1)); ///a가 b보다 크면 1, b가 더 크면 -1 같으면 0을 리턴.
		
		// 객체를 생성하지 않고 곧바로 대입하면 AutoBoxing이 되서 그냥 들어가버림.
		Integer i3 = 10;
		System.out.println(i3); // 10
		
//		Integer i4 = "123"; //AutoBoxing 불가능
//		Integer i4 = (Integer)"123"; //이것도 불가능
		Integer i4 = new Integer("123"); //Integer형의 
		
		System.out.println("======================");
		
		//Wrapper => 기본자료형
		int n3 = i3.intValue(); //원래는 이렇게 넣어야 하는데
		int n4 = i4.intValue();
		
		System.out.println(n3 < n4 ); //true
		
		int n5 = i1; //언박싱, 알아서 오토 언박싱 된다. 
		int n6 = i2;
		
		System.out.println(n5==n6); //true
	}

```

- 기본자료형 <----->String
    
    ```java
    		String s1 = "10";
    		String s2 = "15.3";
    
    		//String to 기본자료형 : parsing
    		//변환 할 래퍼클래스.parseXXX(변환할 문자열);
    		int i = Integer.parseInt(s1) // 기본형 int가 된다.
    		double d = Double.parseDouble(s2);
    		
    		System.out.println(i+d); // 25.3
    		
    		//기본자료형 to String 
    		//String.valueOf(변환할 기본자료형 값); : String
    		String sI = String.valueOf(i);
    		String sD = String.valueOf(d);
    		
    		System.out.println(sI+sD); // 1015.3
    
    //출력 
    //25.3
    //1015.3  //String 차입
    ```
    

---

## java.util.Date 클래스

- 완성도가 높지는 않음
- 날짜와 시간에 대한 정보를 담을 수 있는 클래스
- 용례를 보자
    
    ```java
    Date today = new Date(); // 객체를 생성한 시간을 찍어줌
    		System.out.println("기본생성자 : " + today);
    		
    		//원하는 날짜 출력하기
    		//매개변수 생성자를 통해 객체 생성하기
    		Date date1 = new Date(2020, 2, 2);
    		System.out.println("매개변수 생성자  : " + date1);
    
    /*
    기본생성자 : Wed Feb 01 18:13:02 KST 2023
    매개변수 생성자  : Tue Mar 02 00:00:00 KST 3920 => 이거 뭐지..
    depricated
    */
    ```
    
    ```java
    		Date date1 = new Date(2020, 2, 2);
    		System.out.println("매개변수 생성자  : " + date1);
    // 매개변수 생성자  : Tue Mar 02 00:00:00 KST 3920 => 이거 뭐지
    ```
    
    이렇게 나오는 이유?

    ```java
    public Date(int year, int month, int date, int hrs, int min, int sec) {
        int y = year + 1900;
        // month is 0-based. So we have to normalize month to support Long.MAX_VALUE.
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CalendarUtils.floorDivide(month, 12);
            month = CalendarUtils.mod(month, 12);
        }
        BaseCalendar cal = getCalendarSystem(y);
        cdate = (BaseCalendar.Date) cal.newCalendarDate(TimeZone.getDefaultRef());
        cdate.setNormalizedDate(y, month + 1, date).setTimeOfDay(hrs, min, sec, 0);
        getTimeImpl();
        cdate = null;
    }

//제대로 나오려면 년도 -1900, 월-1 로 넣어줘야함
// int hrs, int min, int sec 이것도 넣으려면 넣어 

//이런식으로 호출하면 잘 나온다.
Date date1 = new Date(2020-1900, 2-1, 2);
System.out.println("매개변수 생성자  : " + date1);
    
    ```
