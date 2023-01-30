- getter를 만들 때, boolean 형식의 변수는 이름을 “is변수명”으로 한다.
    
    ```java
    private boolean allInOne;
    
    // getter
    public boolean isAllInOne() {return allInOne;}
    ```
    

⇒ 상속의 개념이 없으면 중복되는 코드가 많다. 

---

## 상속

- 다른 클래스가 가지고 있는 멤버(필드, 생성자, 메소드)들을 직접 만들지 않고 상속을 받음으로써 새 클래스가 자신의 멤버처럼 사용할 수 있는 기능
    
    **상속의 핵심은 **“부모클래스에 자식클래스의 공통적인 코드를 모은다. “**이다.
    
     ⇒ 즉, 부모클래스를 어떻게 만들지 고민이 된다면 **중복필드를 추출**해보면 도움이 된다.
    
- **상속은 단방향**, 부모는 자손 클래스의 코드를 받을 수 없음.

> 자식 클래스가 부모의 필드에 접근해서 값을 변화 시켰을 때, 다른 자식들이랑 그 변화가 공유되나? ⇒ 클래스 변수일 경우에는 공유되겠지만, 인스턴스 변수는 공유되지 않지.

```java
					// product를 상속하겠다. 상속은 필드, 함수 전부를 상속한다. 
public class Desktop extends Product{
	/*
	private String  brand;
	private String  pCode;
	private String  pName;
	private int     price;
	 */
	private boolean allInOne;
	
	//getter/setter 메소드도 모두 상속받음. 
	public void setAllInOne(boolean allInOne) {this.allInOne = allInOne;}
	public boolean isAllInOne() {return allInOne;}
	
	public Desktop() {}
	//자식의 매개변수 생성자
	//=> Desktop 객체를 생성하면 필드 5개짜리 객체가 생성됨.
	//String brand, String pCode, String pName, int price, boolean allInOne
	public Desktop(String brand, String pCode, String pName, int price, // 여기까지는 부모님 필드를 끌어다 쓰기 위해 받아오는 매개변수
			boolean allInOne) {
		// 매개변수 생성자 표기법
		// this.필드명 = 매개변수명;
		// this.brand = brand; // 내가 직접 기술한 필드는 아니기 때문에 this. 으로 접근 불가!
		
		// 부모클래스에 정의된 필드에 접근하려면
		// super. 으로 접근하면 됨. => super.은 해당 부모의 주소를 담고 있음. 
		// super.brand => 접근하고자 하는 필드가 private이므로 isNotVisible 애러 발생,
		
		// 해결방법1. 부모 클래스의 필드를 자식까지 접근 가능하도록 **protected**(상속, 같은패키지)로 바꿔준다.
		// 해결방법2. setter
		// 해결방법3. 부모클래스 생성자 활용 => 반드시!생성자의 가장 첫 줄에서 호출해야한다.
		
		super(brand, pCode, pName, price);
		this.allInOne = allInOne;

		// 상속관계에서 부모의 메소드와 선언부가 같은 함수를 만들어도 오류가 나지 않는다. => 오버라이딩
		public String information() {
		return super.information() + "isAllInOne : " + allInOne;
	}
	}
```

### 오버라이딩

- 부모클래스의 매소드를 물려받아 코드의 내용물만 내 입맛대로 재정의 하는 것

* 클래스다이어그램에서 상속 관계는 자식→부모의 방향으로 화살표를 표시한다.

- 동적바인딩 - 런타임 시점에 사용될 메서드가 결정된다.
    - 애초에 static은 클래스단위로 만들어지고, Override는 객체단위로 형성된다.
- static의 상속이 안되는 이유
    
    > [Static 상속?](https://wedul.site/457)
    static은 어차피 컴파일 시점에서 static 영역에 올려놓고 프로그램을 시작하니까(올려놓고 시작 한다는 것은 이미 컴파일 시점에 어떤 함수를 실행할 지가 결정이 되어있는 것? 말 그대로, 컴파일시에 어떤 함수를 실행할지가 모두 결정된다....?


### 오버라이딩 성립조건

1. 부모메소드의 메소드명과 동일해야한다.
2. 매개변수의 자료형, 갯수, 순서가 동일 (**매개변수명과는 무관**)
3. 반환형이 동일해야 한다.
4. 접근제한자는 부모메소드의 접근 제한자보다 제한 범위가 같거나, 그 범위가 커야한다.  
- 참고 : 오버로딩 성립 조건
    1. 메소드 명은 반드시 같아야 한다.
    2. 매개변수의 갯수나 자료형, 선언 순서가 반드시 달라야 한다.
    3. 반환 자료형, 접근 제한자는 달라도 된다. (오버로딩에 영향을 미치지 않는다.)
    
   
### @Override 어노테이션

- 생략 가능(명시를 하지 않아도 부모메소드와 형태가 같다면 오버라이딩이 된다)
- 어노테이션을 붙이는 이유?
    1. 메소드가 오버라이딩 됐다는 것을 알리고자 하는 목적
    2. 코드를 잘 못 기술했을 경우 오류로서 알려주기 때문에 코드를 다시 한 번 검토할 수 있도록 유도한다.
    3. 혹시라도 부모메소드가 수정되었을 경우, 자식메소드쪽에서 오류로 알려주기 때문에 코드를 다시 한 번 검토할 수 있게 유도한다.

### 상속의 특징

- **단일 상속**만 가능(부모클래스는 하나) //인터페이스는 다중 상속이 된다.
    
    => 혹여 필드명, 메소드명이 일치할 경우 자식 입장에서 어느 부모의 것을 가져다 써야 할지 헷갈리기 때문에
    
- 명시되어 있지는 않지만 모든 클래스는 모두 Object 클래스의 자손이다.
    
    ⇒ Object 클래스에 있는 메소드를 쓸 수 있음
    
    ⇒ Object 클래스에 있는 메소드가 마음에 안들면 오버라이딩을 통한 재정의가 가능하다.
    

### toString()

- 부모클래스인 Object 클래스에 있는 toString() 메소드
    
    => 해당 참조 타입의 풀클래스명 + @ + 해당 객체의 주소값의 16진수 형태를 리턴해줌.
    
- 출력문 안에 참조변수를 넣어 출력하고자 할 때, JVM에 의해 .toString() 메소드가 호출되었던 것.
- System.out.println(bk); //가 내용물만 나오면 편리
=> 자식클래스인 Book 클래스에서 toString(); 메소드를 재정의(오버라이딩)
해당 객체의 모든 필드값을 하나의 문자열로 연이어 리턴하는 형태로 재정의 해서 사용한다.

** 만약에 자식클래스에서 똑같은 필드를 만들면, 부모클래스의 필드는 무시된다.

---

- [어노테이션](https://velog.io/@jkijki12/annotation)
- **[# Java의 static 메소드는 왜 overriding이 안되나요?](https://cjlee38.github.io/post/language/java/2022-12-03-override-static-method/)