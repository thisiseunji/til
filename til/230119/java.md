## 반복문, 

- 표현법

```java
초기식;
while(조건식) {
	실행구문;
	증감식;
}
```

```java
public class B_While {
	public void method1() {
		int i; // 초기식
		for(i = 0; i < 3; i++) {
			System.out.println("안녕");
		}

		while (i < 6) { // 조건식
			System.out.println("그래 안녕"); //=>실행문
			i++; //=>증감식
		}
	}
}

//후위 증감식을 유용하게 이용하는 코드
 public void method2() {
		int i = 1;
		while(i<=5) {
			System.out.print(**i++** +" "); 
			//후위연산은 제일 마지막에 실행되기 때문에 
			//출력문이 실행 된 후, i++구문이 실행된다. (증감식의 역할을 함.)
		}
		//1번 while문이 끝났을 때, i는 6
		while(0 < i) {
			System.out.print(i-- + " ");
		}
	} //method2 영역 끝
	
	public void method3() {
		/*
		 * 1-10까지 누적합
		 */
		
		int sum = 0;
		int i = 1;
		while(i <= 10) {
			sum += i++;
			//
		}
		System.out.println(sum);
	}
```

### do- while

- [표현법]

```python
do {
	반복적으로 실행 할 코드
} while(조건식);

// ! => 최소 1번은 무조건 실행 하도록하는거네
```

.. 대강 이런식으로 쓰면 좋겠다.  

```python
public void method6() {
		int[] array = new int[3];
		int num = 0;
		int cnt = 0;
		do {
			System.out.print("10보다 큰 수를 입력하세요 : ");
			num = sc.nextInt();
			sc.nextLine();
			cnt++;
		} while(num <= 10);
		if (cnt == 1) {
			System.out.println("말을 너무 잘 들으시는군요.");
		} else {
			System.out.println("처음부터 10보다 큰 수를 입력하면 좋잖아요");
		}
```

---

### 분기문 (break, continue, 굳이 쓰자면 return도 있겠다.)

- **break** : switch의 브레이크와 다르다. 하지만 여튼… **반복문을 빠져나가는 break;**
    - 반복문을 보조하는 분기문 중에서 “가장 가까운 반복문”을 빠져나가는 구문
    - **break; 구문을 만나는 순간 해당 break;가 속해있는 반복문을** **“한 겹” 빠져나감**!
    - 주의할 점:
        - switch의 break; 와는 다른 개념임. 얘는 switch문을 빠져나갈 뿐..
- **continue** : 다음 반복으로 넘어가는 구문(아마 증감식을 실행시키는거겠지? ㅇㅇ맞다고 하시네)

------

# 배열, 클래스

## 자바의 배열

[TCPSCHOOL.com](http://www.tcpschool.com/java/java_member_initBlock)을 참고하여 메모 했습니다. 

- 자바 프로그램이 실행되면, JVM은 운영체제로부터 해당 프로그램을 수행할 수 있도록 필요한 메모리를 할당 받음
- 메모리를 할당 받으면 용도에 따라 우측 이미지와 같이 구분하여 관리함.
- 메소드 영역(Static 영역) : 클래스에 대한 정보와 함께 , 클래스변수(Static variable)이 저장되는 영역.
- 힙(heap) 영역 : 모든 인스턴스 변수가 저장되는 영역, 힙은 메모리의 낮은 주소부터 높은 주소의 방향으로 할당된다.
- 스택 영역 : 메소드 호출과 관련된 지역변수와 매개변수를 스택영역에 저장. 메소드 호출이 완료되면 소멸하고, 메소드 호출 정보를 스택 프레임 이라고 한다.
- 스택영역은 push-pop으로 데이터 입출력이 일어나고, 후입선출 방식으로 동작한다.
- 높은 주소부터 낮은 주소의 방향으로 할당된다.

![http://www.tcpschool.com/lectures/img_java_memory_structure.png](http://www.tcpschool.com/lectures/img_java_memory_structure.png)

## 배열

- 같은 타입의 변수들로 이뤄진 **“유한집합”**

```java
//선언과 초기화 
1. 타입[] 배열이름 = new 타입[배열 길이];

2. 타입[] 배열이름 = {배열요소1, 배열요소2, ...}; // 2, 3 같은결과
3. 타입[] 배열이름 = new 타입[]{배열요소1, 배열요소2, ...};
```

- 이런 배열들은 모두 객체(new)이다. (각 배열이 자신만의 필드와 메소드를 가진다. )

### 선언/초기화 예시

- 1차원 배열의 선언 및 초기화
    
    ```java
    int[] grade1 = new int[3]; // 길이가 3인 int형 배열의 선언 및 생성
    int[] graint[] grade1 = new int[3]; // 길이가 3인 int형 배열의 선언 및 생성
    
    int[] grade2 = new int[3]; // 길이가 3인 int형 배열의 선언 및 생성
    
    grade1[0] = 85; // 인덱스를 이용한 배열의 초기화
    grade1[1] = 65;
    grade1[2] = 90;
    
    grade2[0] = 85; // 배열의 길이보다 적은 수의 배열 요소만 초기화
    
    for (int i = 0; i < grade1.length; i++) {
        System.out.print(grade1[i] + " "); // 인덱스를 이용한 배열로의 접근
    }
    
    for (int i = 0; i < grade2.length; i++) {
        System.out.print(grade2[i] + " "); // 인덱스를 이용한 배열로의 접근
    }
    de2 = new int[3]; // 길이가 3인 int형 배열의 선언 및 생성
    
    /*
    실행결과
     
    85 65 90 
    85 0 0
    
    */
    ```
    
- 이미 선언된 1차원 배열을 초기화 할 때는
    
    ```java
    Int[] grade3;
    // grade3 = {70, 90, 80};// 이미 선언된 배열을 이 방법으로 초기화하면 오류가 발생함.
    
    int[] grade4;
    grade4 = new int[]{70, 90, 80}; //고로 그냥 항상 new를 사용해라
    ```
    
- 2차원 배열의 선언
    
    ```java
    타입[][] 배열이름 = new 타입[행길이][열길이];
    
    //열 길이를 명시하지 않으면 행마다 다른 길이의 배열을 요소로 저장할 수 있다. 
    
    int[][] arr = new int[3][];
    
    arr[0] = new int[2];
    arr[1] = new int[4];
    arr[2] = new int[1];
    ```
    
- 2차원 배열의 초기화는
    
    ```java
    int[][] arr = {
        {10, 20},
        {10, 20, 30, 40},
        {10}
    }; /// 이런식으로 하면됨. 아니면 반복문으로 하거나
    ```
    

---

### 배열의 복사

- 배열은 한 번 생성하면 그 길이를 변경할 수 없음
- 따라서 배열의 길이를 늘리려면 배열의 내용을 더 긴 배열에 복사해야함.
- 자바에서 제공하는 배열 복사 방법
    1. System 클래스의 arraycopy() 메소드 : 배열의 복사만을 위해 만들어졌기 때문에 가장 좋은 성능을 보인다. 
    2. Arrays 클래스의 copyOf() 메소드
    3. Object 클래스의 clone() 메소드 : 이전 배열과 같은 길이의  배열만 만들 수 있다.
    4. for 문과 인덱스를 이용한 복사
- 예를 들어 본다면…
    
    ```java
    int[] arr1 = new int[]{1, 2, 3, 4, 5};
    int newLen = 10;
    
    // 1. System 클래스의 arraycopy() 메소드
    int[] arr2 = new int[newLen]; //길이 10짜리 배열 만들기
    System.arraycopy(arr1, 0, arr2, 0, arr1.length);
    // arr1의 0번째원소부터 arr2의 0번째 자리에 복사하는데, arr1.length개 만큼을 복사해라.
    
    // 2. Arrays 클래스의 copyOf() 메소드
    int[] arr3 = Arrays.copyOf(arr1, 10); 
    //arr1의 원소를 포함해 10자리를 (나머지 자리는 0으로) 복사한 값
    
    // 3. Object 클래스의 clone() 메소드
    int[] arr4 = (int[])arr1.clone(); //아마 그냥 array로 return되나보다.
    
    // 4. for 문과 인덱스를 이용한 복사 - 이건 할 수 있쥬..?
    ```
    

---

### 향상된 for문의 활용!(유용!)

```java
for(타입 변수이름 : 배열이나 컬렉션 이름) {
	배열의 길이만큼 반복적으로 실행하고자 하는 명령문
}

int[] arr = new int[]{1, 2, 3, 4, 5};

for (int e : arr) {
    e += 10;
}

//1 2 3 4 5 : 실제 배열의 값에는 영향을 미치지 못한다. 참조할때만 사용할 것
```

** 파이썬 Enumerate과 비슷함

```python
a = ['A', 'B', 'C']

for e in enumerate(a) :
	print(e)
...
...
(0, 'A')
(1, 'B')
(2, 'C') # => index와 값을 튜플형식으로 출력해줌 변수를 하나 넣으면 따로 받을 수도 있음.

for i, v in enumerate(a) :
	print(i, v)
...
...
0 'A'
1 'B'
2 'C'
```

---

## Class

객체지향 프로그래밍은 모든 데이터를 객체(object)로 취급하며, 이러한 객체가 바로 프로그래밍의 중심이 된다. 객체는 ‘사물’을 말한다. 객체의 **상태와 행동을 구체화하는 형태의 프로그래밍이 객체지향 프로그래밍이고, 이 때, 객체를 만들어 내기 위한 설계도와 같은 개념을 클래스라고 한다.**

→ 이보다 명확할 수는 없음

---

정의 : 객체를 정의하는 틀, 설계도

구성 : 상태를 나타내는 필드(field), 메소드(method)로 구성된다.

⇒ 필드는 클래스에 포함된 변수를 의미하고

⇒ 메소드는 어떤 특정 작업을 수행하기 위한 명령문의 집합 이라고 할 수 있다. 

- 필드의 초기화
    - 필드는 클래스에 포함된 변수를 말한다.
    - 초기화하지 않아도 변수의 타입에 맞는 초깃값으로 자동으로 초기화 된다.
    - [http://www.tcpschool.com/java/java_member_initBlock](http://www.tcpschool.com/java/java_member_initBlock) 이어서

### 인스턴스

- 자바에서 클래스를 사용하기 위해서는 클래스 타입의 객체를 선언해야한다. ⇒ 자동차를 사용하기 위해서는 설계도로 자동차를 만들어내고 사용할 수 있는 것과 같은 이치…

Q. 이유는 아마 메모리를 효율적으로 사용하기 위해서겠지? 메소드영역(static) 영역에서 사용하면 프로그램 시작과 끝에 계속 올려놔야 하니까. - import 할 때, 올라가는건가? 프로그램 실행의 끝이라함은 메인이 끝날때까지 라고 한다면, main에서 호출 된, 그 클래스들, 그리고 그 import 된 클래스들에서 import된 클래스들까지 스테틱 영역에 올라가는건가?(별로 많지 않은가..?)

### Method

- 특정 작업을 수행하기 위한 메소드의 집합

하나의 클래스에 같은 이름의 메소드를 둘 이상 정의할 수 없지만, 메소드 오버로딩(overloading)을 이용하면 같은 이름의 메소드를 중복하여 정의하는 것이 가능.

메소드 오버로딩이란 매개변수(parameter)의 개수나 타입을 다르게 하여 **같은 이름의 다른 메소드를 작성하는 것** 이다. (함수의 기능은 당연히 어느정도다르겠지만, 함수의 역할이 다른데 굳이 오버로딩을 사용할 이유가 없으니… 그 경우는 생각 안 해도 되겠다.)

> 참고로 오버라이드(override)란?
부모 클래스의 메소드를 자식클래스가 똑같이! (선언부만) 가져와 사용하는 것. 내용은 덮어쓰지만 선언부가 똑같으면 당연히 비슷한 기능을 하는 함수를 작성하게되겠지!
> 

- 메소드 시그니처(method signature)란..?
    
    메소드의 선언부에 명시되는 매개변수의 리스트를 말한다. 만약 두 메소드가 매개변수의 개수와 타입, 그 순서까지 모두 같다면, 이 두 메소드의 시그니처는 같다고 할 수 있다.
    
- 메소드도 클래스 메소드와 인스턴스메소드가 있는데, static 키워드 포함/불포함 여부로 각각을 구분한다.
    - 클래스 메소드의 특징
        - 인스턴스 생성하지 않고 바로 사용가능
        - 인스턴스 변수는 당연히 사용할 수 없음
        - 인스턴스 변수나 인스턴스 메소드를 사용하지 않는 메소드를 클래스 메소드로 정의 하는 것이 일반적

---

### 클래스의 구성

- 클래스의 맴버(member)는 속성을 표현하는 필드(field)와 기능을 표현하는 (method)를 가진다. 또한, 클래스는 **생성된 객체의 필드를 초기화**해주는 메소드인 **생성자(constructor)**를 가진다.

```python
class Car {                    // 클래스 이름
    private String modelName;  // 필드
    private int modelYear;     // 필드

    Car(String modelName, int modelYear) { // 생성자
        this.modelName = modelName;
        this.modelYear = modelYear;
    }

    public String getModel() { // 메소드
        return this.modelYear + "년식 " + this.modelName + " " + this.color;
    }
```

- 필드 : 클래스에 포함된 변수, 선언위치에 따라 아래와 같이 구분한다.
    1. 클래스 변수
    2. 인스턴스 변수
    3. 지역 변수
    
    ```java
    class Car {
        static int modelOutput; // 클래스 변수, 모~든 인스턴스는 공통임.
        String modelName;       // 인스턴스 변수
    
        void method() {
            int something = 10; // 지역 변수
        }
    }
    ```
    

> 클래스 변수와 인스턴스 변수 그리고 지역 변수
클래스 영역에 위치한 변수 중에서 static 키워드를 갖는 변수를 클래스 변수라고 한다.
클래스 영역에 위치한 변수 중에서 static 키워드를 갖지 않은 변수를 인스턴스 변수라고 한다. 
메소드나, 생성자 블록 내에 위치한 변수를 지역 변수라고 한다.
> 

클래스 변수는 인스턴스를 생성하지 않고도 바로 사용 가능하다. (static하니까~!) 그래서 이런 클래스 변수를 **공유변수**라고도 한다. 

클래스 변수는 해당 클래스의 모든 인스턴스가 공유해야 하는 값을 유지하기위해서 사용된다.

예를 들어 car라는 클래스가 있다면 회사 이름 정도가 클래스변수가 되지 않을까? 

> 인스턴스를 생성해서 클래스 변수를 변화시킬 수 있을까?
응 변경시길 수있대. 클래스변수는 모든 인스턴스가 값을 공유하기 때문에, 내 인스턴스에서 값을 변화시키면, 다른 인스턴스, 클래스들도 모두 값을 공유한다고 생각하면 됨. 만약에 이렇게 안되게 하고싶으면 접근제어자를 잘 설정해야겠지?
> 

```java
class Field {
    static int classVar = 10; // 클래스 변수 선언
    int instanceVar = 20;     // 인스턴스 변수 선언
}
public class Member01 {
    public static void main(String[] args) {
        int var = 30;                   // 지역 변수 선언
        System.out.println(var + "\n"); // 지역 변수 참조 

        Field myField1 = new Field();   // 인스턴스 생성
        Field myField2 = new Field();   // 인스턴스 생성

        System.out.println(Field.classVar); // 클래스 변수 참조
        System.out.println(myField1.classVar);
        System.out.println(myField2.classVar + "\n");

        **myField1.classVar = 100;            // 클래스 변수의 값을 변경

        System.out.println(Field.classVar); // 클래스 변수 참조
        System.out.println(myField1.classVar);
        System.out.println(myField2.classVar + "\n");**

        System.out.println(myField1.instanceVar); // 인스턴스 변수 참조
        System.out.println(myField2.instanceVar + "\n");

        myField1.instanceVar = 200;               // 인스턴스 변수의 값을 변경
        System.out.println(myField1.instanceVar); // 인스턴스 변수 참조
        System.out.println(myField2.instanceVar);
    }
}
```

---

- 메소드를 만들면 모듈화가 자연스럽게 될 테니까 전체적인 코드의 가독성이 좋아진다.
- 유지보수도 쉽게 할 수 있다 (→ 모듈화의 장점이지…)

---

### 생성자

- 클래스로 객체를 생성하면, 해당 객체는 즉시 메모리에 생성되지만, 이렇게 생성된 객체는 모든 인스턴스 변수가 아직 초기화되지 않은 상태이다.(사실 초기값으로 초기화된 상태)
- 따라서 자바에서는 객체의 생성과 동시에 **인스턴스 변수를 원하는 값으로 초기화** 할 수 있는 생성자 메소드를 제공한다.
- 생성자의 이름은 해당 클래스 이름과 같아야 한다.
- 클래스의 instance변수 중 private인 애가 있으면, 사용자나 프로그램이 직접 접근할 수 없기 때문에 이를 초기화할 수 있는 public메소드가 필요하잖아. ..
- 그렇다면 생성자의 특징
    1. **생성자는 반환값이 없지만, 반환타입을 void로 선언하지 않음 : 안쓴다.**
    2. 초기화를 위한 데이터를 인수로 전달받음
    3. 객체를 초기화하는 방법이 여러개 존재할 경우 하나의 클래스가 여러개의 생성자를 가질 수 있음 → 생성자도 하나의 메소드이므로 메소드 오버로딩이 가능하다는 뜻.

```java
//작성예
class Car {
    private String modelName;
    private int modelYear;
    private String color;
    private int maxSpeed;
    private int currentSpeed;
 
    **Car(String modelName, int modelYear, String color, int maxSpeed)** { //반환값 안쓴다.
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }

    public String getModel() {
        return this.modelYear + "년식 " + this.modelName + " " + this.color;
    } //private니까... 정보에 접근하려면 getter, setter 이런게 필요하겠지. 
}

public class Method02 {
    public static void main(String[] args) {
        Car myCar = new Car("아반떼", 2016, "흰색", 200); // 생성자의 호
        System.out.println(myCar.getModel()); // 생성자에 의해 초기화되었는지를 확인함.
    }
}
```

만약에 생성자를 하나도 정의하지 않으면 컴파일시에 그냥 기본생성자를 알아서 추가함

⇒ 정의 했다면, 기본생성자는 알아서 추가되지 않으니까 수동으로 추가 해 주거나, 아니면 설정한 파라미터 잘 넣어서 호출해야함

(인스턴스 변수의 초기화는 생성자를 사용하여 수행할 수도 있지만, 클래스 필드에서 바로 수행할 수도 있다)

```java
클래스이름() {}
```

그럼 getter, setter 같은걸로 설정할 수 있도록 해야겠지(생성자 있어도 접근할 수 있는 public 함수를 만들어줘야하겠다. )

- 클래스란 객체지향의 **추상화라는 개념을 직접 구현**한 것
- 클래스의 선언과 구조는 우측의 그림을 참고하자.

![http://www.tcpschool.com/lectures/img_java_class_definition.png](http://www.tcpschool.com/lectures/img_java_class_definition.png)

### 접근 제어자 및 기타 제어자

- 객체 지향 프로그래밍의 특징 중 하나인 정보은닉(⇒캡슐화) 을 위함.
- 자바 접근 제어자 종류와 접근 가능 범위는 다음과 같다.
1. private
2. default 
3. protected
4. public 

같은클래스

같은클래스 같은패키지

같은클래스 같은패키지 자손클래스

같은클래스 같은패키지 자손클래스 전체

- 기타 제어자
    - final : 변경할 수 없다. 는 뜻
    
    ```java
    final class Car {                    // 이 클래스는 상속을 통해 서브 클래스를 생성할 수 없음.
        final int VAR;                   // 이 필드는 상수화되어 값을 변경할 수 없음.
        final void brake() {             // 이 메소드는 오버라이딩을 통해 재정의할 수 없음.
            final double MAX_NUM = 10.2; // 이 지역 변수는 상수화되어 값을 변경할 수 없음.
        }
    }
    
    /*
    자바에서 final 제어자를 사용할 수 있는 대상은 다음과 같다.
    - **클래스, 메소드, 필드, 지역 변수**
    */
    ```
    
    - static : 공통적이다. 라는 뜻 특징은 아래와 같다.
        1. 프로그램 시작시 최초에 단 한 번만 생성되고 초기화된다.
        2. 인스턴스를 생성하지 않고도 바로 사용할 수 있다.
        3. 해당 클래스의 모든 인스턴스가 공유한다.
        
        스테틱 제어자는 다음 대상에서 활용할 수 있다. 
        
        - 메소드, 필드, 초기화 블록
    - abstract : 추상적인, 선언부만 있고 구현부가 없는 메소드를 추상메소드라 한다.
        - 추상 클래스(추상메소드를 포함하고 있는 클래스)와 추상 메소드에는 반드시 abstract제어자를 붙여야 한다.
        - 클래스, 메소드에 사용할 수 있다.
- 제어자의 조합시 주의사항
    1. static과 abstract를 함께 사용할 수 없음 : static은 인스턴스를 생성하지 않고도 바로 사용할 수 있어야한다. 그래서 구현부가 있는 메서드에만 사용할 수 있다.
    2. 클래스에 abstract와 final을 동시에 사용할 수 없음 : 클래스에 사용되는 final은 클래스를 확장할 수 없다는 뜻이고, abstract는 상속을 통해서 완성되어야 한다는 의미이므로 모순됨.
    3. abstract 인데 private일 수 없다. : abstract메서드는 상속받아서 오버라이딩 해야만 사용할 수 있기 때문이다.
    4. 메서드에 private와 final을 같이 사용할 필요가 없다. : 접근 제어자가 private이면 어차피 오버라이딩 될 수 없기 때문에 둘 중 하나만 사용해도 충분하기 때문이다.

---

### this와 this()

**this**

- this 참조변수는 **인스턴스가 자기 자신을 참조하는데 사용하는 변수**
- this 참조변수는 **해당 인스턴스의 주소**를 가리키고 있다.

```java
class Car {
    private String modelName;
    private int modelYear;
    private String color;
    private int maxSpeed;
    private int currentSpeed;
 
    Car(String modelName, int modelYear, String color, int maxSpeed) {
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }
    ...
}
```

- 위처럼 생성자의 매개변수 이름과, 인스턴스 변수 이름이 같을 때, 인스턴스 변수 앞에 this키워드를 붙여 구분해야만 한다.
- **this 참조변수를 사용할 수 있는 영역은 인스턴스(static 키워드가 없는) 메소드**뿐이고, 클래스 메소드에서는 사용할 수 없다. 그러므로 메소드 내부에서 인스턴스 변수나 인스턴스 메소드를 사용하지 않는 메소드를 클래스 메소드로 정의하는 것이 일반적이다. (**클래스 메소드는 인스턴스 변수를 사용하지 않고, 전달된 매개변수만으로 동작하는 메소드)**
- 모든 인스턴스 메소드에는 this 참조변수가 숨겨진 지역 변수로 존재하고 있다.

---

**this() 메소드**

- this()메소드는 **생성자 내부에서만 사용**할 수 있으며, **같은 클래스의 다른 생성자를 호출할 때 사용**한다.
- **this()메소드에 인수를 전달하면, 생성자 중에서 메소드 시그니처가 일치하는 다른 생성자를 찾아 호출해준다.**

```java
class Car {
    private String modelName;
    private int modelYear;
    private String color;
    private int maxSpeed;
    private int currentSpeed;

    Car(String modelName, int modelYear, String color, int maxSpeed) {
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }

    Car() {
        this("소나타", 2012, "검정색", 160); // 다른 생성자를 호출함.
    }

    public String getModel() {
        return this.modelYear + "년식 " + this.modelName + " " + this.color;
    }
}

public class Method05 {
    public static void main(String[] args) {
        Car tcpCar = new Car(); System.out.println(tcpCar.getModel());
    }
}
// 주의 : 한 생성자에서 다른 생성자를 호출할 때에는 
//        반드시 해당 생성자의 첫 줄에서만 호출할 수 있습니다.
// 2012년식 소나타 검정색
```

---

## 메소드 오버로딩

- 메소드의 이름은 같고 메소드 시그니처는 다른 함수를 만드는 방법
- 참고 : 리턴타입은 상관없음(달라지던 말던 완전 아웃오브안중)
- 자바 컴파일러는 사용자가 오버로딩된 함수를 호출하면, 전달된 매개변수의 개수와 타입과 같은 시그니처를 가지는 메소드를 찾아 호출한다.

## 재귀호출

- 메소드 내부에서 해당 메소드가 또다시 호출되는 것을 의미.
- 자기가 자신을 계속해서 호출하므로 끝없이 반복된다.
- 따라서 메소드 내에 재귀 호출을 중단하도록 **조건이 변경 될 명령문을 반드시 포함해야함.**
- 그렇지 않으면 스택오버플로우에 의해 종료된다.
    
    > 스택 오버플로우(stack overflow)는 메모리 구조 중 스택(stack) 영역에서 해당 프로그램이 사용할 수 있는 메모리 공간 이상을 사용하려고 할 때 발생한다.(너무 많은 함수 호출이 있어서)
    > 

```java
//1부터 n까지의 누적합을 더하는 함수를 재귀적으로 작성하면,

int recursiveSum(int n) {
	if (n == 1) {
		return 1
	}
	return n+recursiveSum(n-1)
		
}
```

[코드 비주얼라이저 참고](https://pythontutor.com/visualize.html#mode=edit) 

재귀 호출시 늘 기억해야하는 것은 함수는 실행이 끝나면, 리턴값을 가지고, **호출부로 돌아간다.** 

---

## 패키지

- 패키지는 **클래스와 인터페이스의 집합**을 의미함
- 파일을 효율적으로 관리하고, 이름 충돌을 방지함
- 물리적으로는 하나의 디렉토리에 포함.
- 다른 패키지를 포함할 수 있으며, 디렉토리 계층구조는 점으로 구분.
- 자바의 모든 클래스는 반드시 하나 이상의 패키지에 포함되어야 한다.
    
    ⇒ 만약 소스파일에 패키지 선언이 없는 경우 기본적으로 “이름 없는 패키지”에 포함해 컴파일한다.(모든 패키지선언이 없는 클래스와 인터페이스는 모두 같은 패키지에 포함된다.)
    

패키지가 다른 클래스나 인터페이스를 사용할 경우 import 키워드를 이용한다.

### import

- import 문을 사용하면 다른 패키지에 속한 클래스를 패키지 이름을 제외한 클래스 이름만으로 사용할 수 있다.
- import문을 선언할 때, 별표(*)를 사용하는 것이 패키지에 포함된 다른 하위패키지의 클래스까지 포함해주는 것은 아니다 ⇒ 클래스나, 인터페이스만 *로 표현 가능함.
- java.lang 패키지에 대해서는 import문을 사용하지 않아도 클래스 이름만으로 사용할 수 있다.

---

230120

### 초기화블럭

- 클래스가 생성될 때, 무조건 수행되는 블록
- 종류 :
    - 클래스 초기화 블럭 : 클래스가 처음 로딩(메모리에 올라갈 때,) 한 번만 수행되는 블럭을 의미함
    - 인스턴스 초기화 블럭 : 인스턴스 생성시에 수행되는 블럭을 의미한다.
- 클래스 변수의 초기화, 혹은 인스턴스 변수의 초기화가 복잡할 경우, 또한 중복 코드를 줄이기 위해 사용된다.
- 형태 :
    
    ```java
    class Example{
    	static {
    		System.out.println("클래스 초기화 블럭");
    	} // 중괄호 앞에 static이 붙으면 클래스 초기화 블럭
    	
    	{ System.out.println("인스턴스 초기화 블럭");}
    } // 안 붙으면 인스턴스 초기화 블럭이다. 
    ```
    
- 활용 : 복잡한 초기화(출력, 계산 등 가능)
- 예시
    - 인스턴스 초기화 블럭
        
        ```java
        class Movie {
        	
        	public Movie() {
        			System.out.println("휴대폰을 꺼주세요.");
        			title = "타이타닉";
        			light = false;
        	}
        	
        	public Movie(String title) {
        			System.out.println("휴대폰을 꺼주세요.");
        			title = title;
        }
        
        //----------------------------------------을 인스턴스 초기화 블록을 활용하면
        
        class Screening {
        	
        	{
        			System.out.println("휴대폰을 꺼주세요.");
        	}
        	public Screening() {
        			
        			title = "타이타닉";
        			light = false;
        	}
        	
        	public Screening(String title) {
        			title = title;
        }
        
        //생성자 실행 전에 인스턴스 초기화 블록이 우선 실행된다. 중복 제거!
        
        ```
        
    - 클래스 초기화 블럭
        
        ```java
        class Screening {
        	
        	static{System.out.println("Class loading");}
        	{System.out.println("휴대폰을 꺼주세요.");}
        	public Screening() {
        			title = "타이타닉";
        			light = false;
        	}
        	
        	public Screening(String title) {
        			title = title;
        	}
        
        ```
        
    - 결과
        
        ```
        Class loading
        휴대폰을 꺼주세요.
        휴대폰을 꺼주세요. (인스턴스가 생성될 때 마다 출력되므로..2번)
        ```
        

### 필드의 초기화 순서

클래스의 필드는 다양한 방법으로 초기화 할 수 있다. 따라서 같은 필드가 여러 번 초기화 될 가능성이 있다. 

자바에서 필드의 초기화 순서는 아래와 같다. 여러번 초기화 하면 당연히 제일 마지막 값만 남는다. 

1. 클래스 변수 : 기본값 → 명시적 초기화 → 클래스 초기화 블록
2. 인스턴스 변수 : 기본값 → 명시적 초기화 → 인스턴스 초기화 블록 → **생성자**