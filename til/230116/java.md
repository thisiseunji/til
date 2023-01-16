![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b60a5121-37d3-4ea1-9851-f630dd617e59/Untitled.png)

- 출력문 안에 수식 작성시 (우선순위에 상관 없이)소괄호 적어줄 것

### 증감연산자

**정의 :** 한 번에 값을 1증가시키거나 또는 한 번에 값을 1 감소시키는 단항연산자

[종류]

- ++ : 값을 1 증가시키는 연산자
    - ++num => 전위연산자, 선증감 후처리
    - num++ => 후위연산자, 선처리 후증감
- -- : 값을 1 감소시키는 연산자
    - --num => 전위연산자, 선증감 후처리
    - num-- => 후위연산자, 선처리 후증감

> **후위연산 계산 꿀 팁 : 없는 샘 치고 다른 연산을 모두 끝낸 후, 후위연산자 연산을 해주면 된다.** 
→ 심지어 어느정도냐면 (a++) 이런식으로 묶인 괄호, (++e + h) 이런식의 괄호도 영향 없이 모든 연산이 끝난 후, 마지막에 후위연산 처리해주면 된다. (Quiz참고)
> 

```java
// 수식이 처리된 후 a에서 i까지의 값은?

public class Quiz {
	public void quiz() {
		int a = 5;
		int b = 10;
		int c = (++a) + b;
		int d = c / a;
		int e = c % a;
		int f = e++;
		int g = (--b) + (d--);
		int h = 2;
		int i = a++ + b /(--c/f) *(g-- - d) % (++e +h);

		System.out.println("a : "+ a);
		System.out.println("b : "+ b);
		System.out.println("c : "+ c);
		System.out.println("d : "+ d);
		System.out.println("e : "+ e);
		System.out.println("f : "+ f);
		System.out.println("g : "+ g);
		System.out.println("h : "+ h);
		System.out.println("i : "+ i);		
	}
}
```

- Quiz 답:
    
    a : 7
    b : 9
    c : 15
    d : 1
    e : 6
    f : 4
    g : 10
    h : 2
    i : 12
    

---

- 복합대입 연산자 +=는 문자열 접합에도 쓰인다.
- 3항연산자의 중첩사용 가능

```java
public void method4() {
		//3항연산자의 중첩사용 양수, 음수, 0 구분하기 
		System.out.print("정수값 입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.printf("%d은(는) %s\n", num, (num > 0)? "양수입니다.": (num==0)? "0입니다.":"음수입니다.");
	}
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/032c5479-b319-49dd-94b7-46c30e65cdd2/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/19880c87-dc88-4efb-8a57-153a612f7a9d/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2a155851-77f0-4335-8965-c793c2831b23/Untitled.png)

### 자바 문법 심화 :

- 배열 길이 늘리기 :

```java
import java.util.Arrays;

public class prog {
	public static void main(String[] args) {
		int[] arr1 = new int[]{1, 2, 3, 4, 5};
		int newLen = 10;
		
		// 1. System 클래스의 arraycopy() 메소드 : 가장 효율이 좋다. 
		int[] arr2 = new int[newLen];
		System.arraycopy(arr1, 0, arr2, 0, arr1.length); 
		//복사할 대상, 시작인덱스, 복사될공간, 시작위치, 시작인덱스부터 몇 개 복사할건지
		
		for (int i = 0; i < arr2.length; i++) {
			System.out.print(arr2[i] + " ");
		}
		System.out.println();

	// 2. Arrays 클래스의 copyOf() 메소드
		int[] arr3 = Arrays.copyOf(arr1, 10);
		
		for (int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();

	// 3. Object 클래스의 clone() 메소드 : 길이를 늘리지 못 한다. 
		int[] arr4 = (int[])arr1.clone();
		
		for (int i = 0; i < arr4.length; i++) {
			System.out.print(arr4[i] + " ");
		}
		System.out.println();

	// 4. for 문과 인덱스를 이용한 복사
		int[] arr5 = new int[newLen];
		
		for (int i = 0; i < arr1.length; i++) {
			arr5[i] = arr1[i];
		}
		
		for (int i = 0; i < arr5.length; i++) {
			System.out.print(arr5[i] + " ");
		}
	}
}
```

출처 : tcpschool.com

- **Enhanced for 문**
    
    > for (타입 변수 이름:  배열이나 컬렉션 이름) {
           배열의 길이만큼 반복적으로 실행하고자 하는 명령문;
    }
    > 
    
    ```java
    int[] arr = new int[]{1,2,3,4,5};
    
    for (int e : arr) {
    	System.out.print(e + ", ");
    }
    //실행결과 1, 2, 3, 4, 5
    ```
    
    주의할 것은 만약 for문 안에서 int e의 값을 변화시켜도 원본은 변화되지 않는다는 것이다. 
    
    → 참조할 때만 사용하는 것이 좋다. 
    
    python의 enumerate() 같은 역할을 하는 것 같다. 
    

- 객체지향 프로그래밍
    - 모든 데이터를 객체(object)로 취급
    - class : 객체를 정의하는 틀
        - 객체의 상태를 나타내느 field와 객체의 행동을 나타내는 method로 구성된다.
        - field는 클래스에 포함된 변수를 의미하고, method는 특정 작업을 수행하기 위한 명령문의 집합이다.
    - instance : 클래스의 사용을 위해서는 해당 클래스 타입의 객체를 선언해야하는데, 이과정을 클래스의 인스턴스 화라고 하고, 선언된 해당 클래스 타입의 객체를 instance라고 한다. 즉, **인스턴스는 메모리에 할당된 객체를** 의미한다.
        - 생성된 인스턴스는 독립된 메모리 공간에 저장된 **자신만의 필드(변수)**를 가질 수 있다.
    
    - 메소드 오버로딩 : 매개변수의 개수나 타입을 다르게하여 같은 이름의 또 다른 메소드를 작성하는 것
        - 메소드에 사용되는 이름을 절약할 수 있고, 메소드 호출시, 매개변수의 타입이나 개수에 크게 신경 쓰지 않고 호출할 수 있게 된다.

---

### 클래스

- 클래스는 멤버로 속성을 표현하는 필드, 기능을 표현하는 메소드를 가진다.
- 클래스 생성된 객체의 필드를 초기화 해주는 특별한 메소드인 **생성자(constructor)**를 가진다.

```java
class BMWCar {
	static String company; //클래스 변수 : 클래스 영역에 위치한 변수 중 static 키워드를 갖는 변수
	private String modelName; // 인스턴스 변수 : 클래스 영역에 위치한 변수 중 static 키워드를 갖지 않은 변수
	private int modelYear;

// 메소드나, 생성자, 초기화 블록 내에 위치한 변수를 지역변수(local variable)이라고 한다. 
	Car(String modelName, int modelYear) { // 생성자
		this.modelName = modelName;
		this.modelYear = modelYear;
	}
	
	public String getModel(String colorChoice) {
		String color = colorChoice //지역변수
		return this.modelYear+"년식"+ this.modelName+color;
	}
}
```

- **클래스 변수와 인스턴스 변수는** 초기화를 하지 않아도, 변수의 타입에 맞게 **자동으로 초기화된다.**
- 지역변수는 초기화하지 않으면 컴파일 오류가 발생한다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/663419fe-29c1-41ed-a5b0-84d52681bfcc/Untitled.png)

- 클래스 변수는 모든 인스턴스가 값을 공유한다

이어서 공부할 것

[코딩교육 티씨피스쿨](http://www.tcpschool.com/java/java_member_method)