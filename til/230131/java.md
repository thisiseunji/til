# (Polymorphism)/ 동적 바인딩(오버라이딩으로 보는)/ 추상클래스


- OOP(Object Oriented Programing)의 3대 특징 중 하나
    1. 캡슐화
    2. 상속
    3. 다형성(여러개의 형태를 갖는다.)

# 다형성(Polymorphism)

- 상속을 이용한 기술로, 부모 타입으로부터 파생된 여러 가지 타입의 자식객체를 부모 클래스 타입 하나로 다룰 수 있는 기술
    
    ⇒ **상속관계에서의 부모, 자식 타입 간의 “형변환”이 가능**함을 의미한다. 
    

```java
		System.out.println("부모 타입 레퍼런스로 자식 객체를 다루는 경우"); // 업케스팅
		Parent p1 = **/* (Parent) */**new Child1(1,2,3);
		p1.printParent();
		// double -> int 가 되는 자동형변환과 같은 원리.
		// => Child1 객체가 Parent 형으로 "자동형변환" 이 되고있다!
		// p2라는 레퍼런스로는 Parent의 멤버에만 접근 가능, but Child1을 담을 수는 있음.

		((Child1)p2).printChild1(); // 다운캐스팅
```

1. **UpCasting** : 자식타입 => 부모타입으로 자동형변환(캐스팅연산자 생략 가능)
2. **DownCasting** : 부모타입 => 자식타입으로 강제/명시적 형변환(캐스팅언산자 생략 불가능)

## 다형성을 쓰는 이유:

1. 부모타입의 배열로 자식타입을 모두 다룰 수 있다. ⇒ **객체배열을 편리하게 사용**하기 위해서

```java
// 다형성 개념 적용 없이 객체 배열을 이용한다면...
		
		Child1[] arr1 = new Child1[2];
		arr1[0] = new Child1(1, 2, 4);
		arr1[1] = new Child1(2, 1, 5);
		
		Child2[] arr2 = new Child2[2];
		arr2[0] = new Child2(5, 7, 2);
		arr2[1] = new Child2(2, 3, 5);
		
		//단 , 다형성이 적용되면 부모타입레퍼런스를 통해 자식객체를 모두 담을 수 있음.
		System.out.println("===다형성을 접목한 객체배열===");
		
		Parent[] arr = new Parent[3];
		arr[0] = new Child1(1, 2, 4);
		arr[1] = new Child1(2, 1, 5);
		arr[2] = new Child2(5, 7, 2);
		arr[3] = new Child2(2, 3, 5); // 다형성의 업캐스팅!
```

** 객체 간 형변환은 **상속관계**에서만 가능

```java
		((Child1)arr[3]).printChild1(); 
		// Child1 타입이었던 arr[0]을 Child2로 강제형변환 시 에러 발생
		// => ClassCastException : 클래스 형변환이 잘 못 됐을 경우 에러 발생
```

### instanceof 와 Override

- 실제로 이 그릇에 담긴 인스턴스는 어떤 클래스의 인스턴스인지를 알 수 있는 연산자
- [표현법] `객체명 instanceof 클래스명`
    
    ⇒ 이 객체를 이 클래스로 **자동형변환 할 수 있는가?**에 대한 대답
    
    1. 부모객체명 instanceof 부모클래스명 == true
    2. 자식객체명 instanceof 부모클래스명 == true 
    3. 부모객체명 instanceof 자식클래스명 == false
    4. 자식객체명 instanceof 자식클래스명 == true
    
    ⇒ instanceof를 사용하고 싶지 않을 경우, “Override 된 메소드는 자식의 메소드를 부모보다 먼저 참조한다”는 Override의 성질을 이용한다. ⇒ 이게 왜 그러냐면, **배열의 인덱스 하나하나는 레퍼런스 이지 실제 객체가 아니기 때문임, 동적할당은 “실제 객체”를 기준으로 할당 됨** 
    

```java
//위 코드에 이어서

for(int i=0; i < arr.length; i++) {
			// 각 인덱스의 레퍼런스가 실제로 참고하고 있는 자식클래스로 다운캐스팅 후, 메소드를 호출해야함
			// instanceof 연산자를 사용하는 경우
			if(arr[i] instanceof Child1) {
				((Child1)arr[i]).printChild1();
				continue;
			}
			((Child2)arr[i]).printChild2();
		}
		
		// instanceof 연산자를 이용하면 오류 없이 제대로 된 다운캐스이 일어나게 로직을 구현할 수 있음.
		// 하지만 => 굳이 자식타입으로 형변환하지 않아도 자식 메소드를 알아서 찾아서 호출하게끔 유도할 수 있는 다른 방법이 있다.
		// 오버라이딩 *자식메소드가 부모의 메소드보다 우선권을 갖는 성질을 이용한다.
		**=> 오버라이딩은 "실제 객체"를 기준으로 메소드를 동적할당 한다는 것을 기억해.** 
		for(int i=0; i <arr.length; i++) {
			arr[i].print();
			//실질적으로 참조하고 있는 자식클래스의 오버라이딩된 메소드를 찾아가서 실행됨
		}
```

1. method에서 자식클래스를 활용할 때, 부모 클래스를 return 타입이나, 매개변수로 활용하면 같은 기능을 하는 메소드의 갯수가 줄어들 수 있음(매개변수의 경우 자동으로 업캐스팅 되고, 리턴타입의 경우, 리턴은 해당 “객체” 타입으로 됨.(만일 부모 레퍼런스로 받으면, 필요할 경우 다운캐스팅 해줘야함.)
    - 다형성을 적용하지 않은 경우, 똑같은 함수를 자식별로 만들어야 함
        
        ```java
        
        //다형성을 사용하기 전
        public class ElectronicController1 {
        	// 용산 전자상가에 새로 차린 가게라고 생각하고 가게에서 해야하는 일(기능)을 구현
        	
        	// 제품 재고 확보 => 필드(변수)로 표현, * 컨트롤러 클래스는 기능 위주지만 필요에 따라 필드가 존재할 수 있음
        	private Desktop  desk;
        	private NoteBook note;
        	private Tablet   tab;
        	
        	// 상자에 제품을 담는다(담는 것은 행위, => 메소드로 표현 한다면...)
        	public void insert(Desktop d) { //오버로딩 : 적재한다.
        		desk = d;
        	}
        	public void insert(NoteBook n) {
        		note = n;
        	}
        	public void insert(Tablet t) {
        		tab = t;
        	}
        	
        	// 물건을 손님에게 보여주는 용도의 메소드
        	// 매개변수가 똑같아서 오버로딩이 불가능하다. 
        	public Desktop selectDestop() {
        		return desk;
        	}
        	
        	public NoteBook selectNoteBook() {
        		return note;
        	}
        	
        	public Tablet selectTablet() {
        		return tab;
        	}
        }
        ```
        
        ```java
        
        public class ElectronicRun {
        
        	public static void main(String[] args) {
        		
        		//다형성을 적용하지 않은 경우 
        		ElectronicController1 ec = new ElectronicController1();
        		
        		//각 제품을 상자에 담기
        		//매개변수로 들어간 변수는, method 첫째 줄 실행 전에 지역변수로 생성된다.
        		ec.insert(new Desktop("삼성", "신상데스크탑", 1500000, "Geforce 1070"));
        		ec.insert(new NoteBook("엘지", "그램", 1300000, 4));
        		ec.insert(new Tablet("애플", "아이패드", 500000, true));
        		
        		//각 제품을 손님에게 보여주기
        		Desktop d = ec.selectDestop();
        		NoteBook n = ec.selectNoteBook();
        		Tablet t = ec.selectTablet();
        
        		System.out.println(d/*.toString 이 생략된 꼴*/);
        		System.out.println(n);
        		System.out.println(t);
        	}
        }
        ```
        
    - 다형성을 적용한 경우
        
        ```java
        public class ElectronicController2 {
        	
        	// 전자제품이면 모두 보관할 수 있는 3칸짜리 창고 만들기
        	private Electronic[] elec = new Electronic[3];
        	
        	// 제품을 종류에 상관 없이 창고에 보관하는 기능 
        	public void insert(Electronic any, int index) {
        		elec[index] = any;
        	}
        	
        	// 제품 종류에 상관 없이 창고로부터 꺼내오는 기능
        	// 객체 자체가 나온다. Electronic
        	public Electronic select(int index) {
        		return elec[index];
        	}
        	
        	// 제품 종류에 상관 없이 창고 채로 꺼내는 기능
        	public Electronic[] select() {
        		return elec;
        	}
        }
        ```
        
        ```java
        System.out.println();
        		System.out.println("다형성을 적용한 경우 ");
        		
        		ElectronicController2 ec2 = new ElectronicController2();
        		ec2.insert(new Desktop("삼성", "신상데스크탑", 1500000, "Geforce 1070"), 0);
        		ec2.insert(new NoteBook("엘지", "그램", 1300000, 4),1);
        		ec2.insert(new Tablet("애플", "아이패드", 500000, true), 2);
        		
        		Desktop d  = (Desktop)ec2.select(0);
        		NoteBook n = (NoteBook)ec2.select(1);
        		Tablet t   = (Tablet)ec2.select(2);
        		
        		// 나오는 객체의 반환'형'은 Electronic이다. 그렇다면 아래 코드가 가능한 이유는 무엇일까?
        		// 오버라이딩이 된 method가 있으면 JVM은 실제 객체를 기준으로, 어떤 함수를 반환할지 결정한다.
        		// 결정시, 자식함수(즉 이경우는 자식객체의 toString 함수)가 우선시된다.
        		// **왜냐면, 이게 배열 자체에 값이 들어있는게 아니라 레퍼런스니까!** 
        		
        		System.out.println(ec2.select(0)); //
        		
        		for(Electronic e : ec2.select()) { // 얘는 참조변수가 아닌가봐.
        			System.out.println(e); //동적할당으로인해 자식들의 toString이 출력됨
        		}
        ```
        

---

> **자식 클래스가 어차피 오버라이딩 할 메소드를 부모가 굳이 구현 할 필요가 있을까? 
⇒ 추상 클래스와 인터페이스**

# 추상 클래스 와 인터페이스

## 추상 클래스

- 추상클래스는 클래스 선언부에 abstract 키워드를 쓴 클래스를 말한다.
    - 언제 추상 클래스로 지정할까?
        1. 클래스가 아직 구체적이지 않은 **덜 구현된 상태일 경우 (?관행적으로 그런가?)**
        2. 현재 이 클래스를 **객체 생성이 불가능하게 막고 싶은 경우** 
    - 만약에 추상메소드가 없는 추상 클래스를 상속 받으면 어떻게 될까?
        
        자식 클래스는 더 이상 추상 클래스가 아니므로 객체 생성하여 사용이 가능
        
    
    ** 단 참조자료형 변수로는 활용 가능함**(다형성 적용 가능!!! 리모콘으로 선택 가능)**
    
- 추상메소드를 하나라도 포함한 클래스는 무조건 추상 클래스가 된다.  (일반메소드+일반필드+ 추상클래스)

## 추상 메소드

### 추상 메소드 학습의 진짜 목적은 협업에 있다.

```java
public abstract class Sports {
		...
		public abstract void rule();
}
```

- 추상클래스를 상속받은 자식 클래스에서는 추상클래스를 **반드시 구현**(하거나 구현하지 않으려면 자식도 abstract 클래스가 되거나)해야 한다.
    
    ```java
    public class Basketball extends Sports{
    	
    	@Override
    	public void rule() {
    		System.out.println("손으로 공을 던져 링에 넣으면 득점");
    	}
    }
    ```
    
- 사용 목적 :
    - **강제 오버라이딩 :** 추상 메소드는 자식클래스에서 구현했을 때, 완성된다.
    - 메소드 사용의 **통일성 확보** 목적(예를 들어, 스포츠에는 무조건 룰이 있어야 한다. 처럼)
    - 표준화된 **메소드의 틀**을 제공할 목적(rule()로 호출)

> 추상클래스 부모 타입의 레퍼런스로,  자식이 구현한 추상 메소드를 호출할 수 있을까?
⇒ 된다. 선언부가 있잖아!

```java
public class BasicRun {

	public static void main(String[] args) {
		
		Sports s = new Basketball();
		s.rule(); // 동적바인딩을 통해 실제 객체의 함수를 찾아간다. 
	}
}
// 출력 결과 : 손으로 공을 던져 링에 넣으면 득점
```

**[동적바인딩](https://defo9d.tistory.com/entry/%EC%A0%95%EC%A0%81-%EB%B0%94%EC%9D%B8%EB%94%A9Static-Binding%EA%B3%BC-%EB%8F%99%EC%A0%81-%EB%B0%94%EC%9D%B8%EB%94%A9Dynamic-Binding)

---

# 인터페이스

- **모든 메소드가 추상 메소드**인 ****클래스 ⇒ 추상클래스에 비해 강제성이 짙어진다.

---

[[자료구조](https://meoru-tech.tistory.com/25)] // 동적 메모리 할당