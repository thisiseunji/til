# 메소드

- 입력을 가지고 어떤 일을 수행 한 후, 결과물을 내놓는 하나의 기능 단위
- 표현법 : 접근제한자 [예약어(생략가능)] 반환형 메소드명(매개변수) {수행코드 및 리턴(*반환타입이 void이면 생략한다.)}
- 메소드를 클래스 내부에 정의한 후 호출해서 사용한다.

> **void타입은 어떻게 호출부로 돌아갈까?**
> 

void 타입은 return 구문이 보이지 않을 뿐,  JVM에 의해 생성되고, return은 실행된다. 

**당연히 객체도 리턴 가능하다. (클래스가 자료형이 되겠징)

---

## static method

- static이 붙은 클래스 변수와 마찬가지로 메소드 또한 메모리의 static 영역에 프로그램의 시작과 동시에 로딩됨

## method overloading

- 한 클래스 내에 같은 메소드명의 메소드를 정의할 수 있는 방법
- 매개변수의 **자료형**의 **개수, 순서**가 다르게 작성되어있어야만 한다.
- 단 매개변수명, 접근제한자, 반환형은 메소드 오버로딩에 영향을 주지 않는다.

```java
public void test() {
		//메소드 오버로딩이 잘 되어있는 대표적인 메소드
		System.out.println(); // 매개변수에 뭐가 들어가도 괜찮다. 
		System.out.println("hi");
		System.out.println(1);
		System.out.println(1.9);
		System.out.println(true);
		
		System.out.printf("%s, %c", "사람", '정'); //(String, String, char 타입)
		System.out.printf("%d", 1); //(String, int 타입)
	}
```

- 오버로딩 오류 예시

```java
public void test(int a) {}
//	public void test(int b) {} 
	
	public void test(int a, String b) {}
	
	public void test(String b, int a) {}

// 매개변수명과는 상관 없이 자료형의 개수와 순서가 같아서 에러가 발생한다.
// 즉 매개변수 자료형의 개수와 순서가 다르게 작성되어야 오버로딩이 가능하다. 
	public void test(int a, int b) {}
//	public void test(int c, int d) {}

// 반환형이 다른것은 오버로딩의 조건과 상관 없음.
// 함수를 호출하는 시점으로 생각해야함. 반환형이 무엇이든, 호출이 똑같이 될 수 밖에 없다.
	public void test(int a, int b, String s) {}
//	public int test(int a, int b, String s) {}
// 마찬가지로 접근제한자만 다른 경우에도 오버로딩이 불가능하다. 
//	private void test(int a, int b, String s) {}
```

> 메소드의 호출 시점에서 생각해보면, 오버로딩 오류의 조건을 판단하기가 쉬워진다.
> 

**생성자 역시 마찬가지

---

[8_객체배열.pdf](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ab5745a5-e768-462b-8a59-4c44b7b61e20/8_%EA%B0%9D%EC%B2%B4%EB%B0%B0%EC%97%B4.pdf)

# 객체(인스턴스)배열

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7c6b58a3-ac02-4ca5-b110-761737b565a2/Untitled.png)

- 참조자료형 배열을 만들면, 배열의 칸칸에 직접 리터럴이 들어가지 않고, 주소지가 입력된다.

```java
Example arr = new Example[2];
arr[0] = new Example();
arr[1] = new Example();
```

- 파이썬의 신기한 global() ⇒ 변수명을 loop로 할당할 수 있다. 자바도 Reflection을 활용하면 할 수 있다는 말이 있는데, 권장하지는 않는다고 한다. (둘 모두 사용법을 찾아보았다.)
    - python
        
        [Design Your Life : 네이버 블로그](https://blog.naver.com/nomadgee/220857820094)
        
        ```python
        # 파이썬은 global() + format()을 이용해 변수명을 for문으로 할당할 수 있음
        
        for i in range(10):
            globals()['variable{}'.format(i)] = [x for x in range(3)]
        ```
        variable0 = [0, 1, 2]
        variable1 = [0, 1, 2]
        ...
        variable9 = [0, 1, 2]
        
        이런식으로 출력됨 신기하네
        ```
        ```
        
    - java
        
        [Java - Reflection 쉽고 빠르게 이해하기](https://codechacha.com/ko/reflection/)
        
        대략 reflection이 하는 일을 보면… 
        
        ```
        ...또는, 테스트 코드 작성을 위해 private 변수를 변경할 때 리플렉션을 사용할 수 있습니다. 3rd party 라이브러리를 사용하고 이것의 private 변수를 변경하고 싶을 때 리플렉션을 사용하면 라이브러리 코드 변경없이 값을 변경할 수 있습니다.
        
        Reflection은 다음과 같은 정보를 가져올 수 있습니다. 이 정보를 가져와서 객체를 생성하거나 메소드를 호출하거나 변수의 값을 변경할 수 있습니다.
        
        Class
        Constructor
        Method
        Field
        ```
        
------
* 서블릿이란?
[[Spring] 서블릿(Servlet)](http://jinjin98.tistory.com/97)
* 스프링 프로젝트 폴더 구조 
[스프링(Spring) 프로젝트의 폴더 구조](https://codevang.tistory.com/240)

- main의 매개변수가 들어가는 상황이 뭐가 있을까? 배치프로그램 같은고..?
    
    다른 main함수에서 main함수를 호출했는데, 자연스럽게 null을 입력해줬고, 잘 실행 됐다. 그런데 main메소드에서 매개변수가 필요한 경우가 있을지 궁금해졌다. 
    
    > main은 ‘엔트리 포인트’ 이다.
    > 
    
    main 메소드는 자바 클래스로 프로그램을 실행할 때, 가장 먼저 실행되는 부분이다.  따라서 소스코드로 main메소드가 받을 수 있는 파라미터의 내용을 정해줄 수 없다. 그렇기 때문에, 실행 시작 시점에 외부에서 인자로 파라미터를 받게 한다는 의미 인 것. ⇒ **실행시 옵션**이 필요할 때, 쓴다.
    
    실행시 옵션이란 예를 들어, 명령 프롬프트에서 `dir *.txt` 를 입력하면 모든 txt 확장자를 가진 파일의 목록이 출력되는데, 여기서 dir은 명령어, *.txt는 옵션에 해당한다.
    
    **자바 프로그램에서는 이런 옵션이 String[] args 라는 문자열 배열에 자동으로 들어간다.** 
    
    프로그래머는 배열에서 값을 받아서 쓰면 된다. 그럴 필요가 있다면!
    
    이렇게 매개변수를 받는 이유는 자바 언어의 철학이 그렇기 때문이다. 
    
    ```
    ...매개변수를 반드시 정의하고 나중에 호출할 때 안 쓰면 그만이라고 하는 쪽이,
    매개변수를 못 받는 main 을 정의하는 것을 허용하는 것보다 덜 헷갈린다고 
    자바의 창시자들이 생각했기 때문인 것이죠.
    ```
    
    [[Java] 왜 main()에 들어가는 인자는 오직 String 배열 뿐일까?](https://woonys.tistory.com/entry/Java-String-args%EB%8A%94-%EB%8C%80%EC%B2%B4-%EC%A0%95%EC%B2%B4%EA%B0%80-%EB%AD%98%EA%B9%8C)
    
    [Java/자바] main()의 String args[] 배열 용도, 사용법 예제](http://mwultong.blogspot.com/2006/11/java-main-string-args.html)