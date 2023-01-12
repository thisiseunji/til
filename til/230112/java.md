* 변수
* 데이터 오버플로우
* scanner
  
------
# 변수

- 변하는 수
- 메모리(RAM)에 값(Data)을 기록하기 위한 공간

## 변수에 값을 기록하는 이유?

프로그램을 실행하려면 RAM에 값을 올려야 한다. 데이터는 RAM의 지정된 영역(상자)안에 기록 되는 구조임. 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/048630f3-a304-43d8-a70a-5af4697379d9/Untitled.png)

RAM에 올라간 메모리를 이용해 CPU가 연산을 실행하고, 다시 RAM에 결과를 기록해서 반환하는 형태.

- **버퍼란? (buffer)**
표준입/출력 함수를 통해서 데이터를 입/출력 하는 경우 운영체제가 제공하는 메모리버퍼를 통과 하게 됩니다. 메모리버퍼란 **데이터를 임시로 모아두는 메모리 공간**을 뜻함

## 변수의 선언

- 메모리 공간에 데이터를 저장할 수 있는 **공간**을 할당하는 것 → 자료형 변수명 형태로 선언한다.

→ 실제 데이터가 저장될 수 있는 **공간**만 만들어짐 

## 자료형(Type)

** 자료형 저장 단위가 byte라는 것을 기억하자

** float는 소수점 뒤 7자리, double는 소수점 뒤 15자리까지 붙일 수 있다. 

** 0.0도 실수형이다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fbd5fd90-ccc6-42b3-b28f-791446ef15c1/Untitled.png)


컴퓨터에서  CPU가 한 번에 처리할 수 있는 데이터가 딱 4바이트이기 때문임 > 추가학습필요

→ JVM의 피연산자 스택(Operand Stack)이 피연산자를 4byte 단위로 저장하기 때문에, 4byte 보다 작은 자료형(byte, short)의 값을 계산할 때는 4byte로 변환하여 연산이 수행된다. 그래서 오히려 int를 바로 사용하는 것이 더 효율적이다. 

참고 : [https://kldp.org/node/133963](https://kldp.org/node/133963) → 버스 얘기 나오는데

**실수형의 기본형이 double인 이유는 정확도가 높기 때문이다.


## 변수의 할당

- 변수가 **선언**되어야만 **할당** 할 수 있음
- Long타입의 경우 접미사 L을 통해 Long타입임을 표기해준다.(숫자 1과 헷갈리니 대문자를 사용할 수 있도록 하자)
- Float타입의 경우 접미사 f를 통해 Float타입임을 표기해준다.(대,소문자 상관 없음)

> 선언 후 제일 처음 값을 할당하는 것 = **‘초기화’**


### String(참조형 데이터)

- String은 ‘참조형’이라 사이즈를 지정하지 않았다? →처음에 4byte 상자가 만들어짐.
- Heep에 실제 데이터를 담은 후, 데이터가 저장된 heap영역의 주소지를 할당 해준다.

![제목 없음.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/83952cdc-e85a-45b5-aa61-293799d5ff8c/%EC%A0%9C%EB%AA%A9_%EC%97%86%EC%9D%8C.png)


### 자바는 왜 참조형 데이터를 사용할까?

**클래스 영역이 static을 말하겠지? 맞다!

→ 참조형 데이터의 실제 값은 일종의 instance라고 생각하면 편하겠다. 

객체는 생성시 heap 영역에 저장되고,힙 메모리의 특정 영역이 꽉 차면 Garbage Collector가 더 이상 Stack에서 참조되지 않은 **힙(Heap) 영역의 객체들을 제거하고, 그 과정에서 GC에 의해 제거되지 않은 객체들도 힙 메모리의 다른 영역으로 이동**된다. 결국 **Heap 영역에서 객체의 주소는 고정적이지 않다. → JVM과 GC가 프로그래머 대신 메모리 공간 관리를 돕기 때문이다.** 

출처 : **[자바는 왜 포인터가 아닌 참조 자료형을 사용할까?](https://you88.tistory.com/34)**

- 상수 선언 방법 → final int AGE = 20; *값 변경하려고 하면 The final local variable age cannot be assigned. It must be blank and not using a compound assignment 나옴.
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/22501ad1-17c4-4c49-84c9-f7d19cb8c7f5/Untitled.png)


## 데이터 오버플로우

### bit, byte

- bit : data 저장의 최소단위, 2진수 값 1개를 저장할 수 있는 메모리 공간.
- byte :  8bit == 1byte, 데이터 처리 또는 문자의 최소 단위

### 변수의 저장 가능 범위

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aa11d935-4d82-425b-ae05-fc494b688b7c/Untitled.png)

byte 타입   → 1 바이트 ⇒  0 또는 1이 8개 모여있음

short 타입 → 1 바이트 ⇒  0 또는 1이 16개 모여있음

int 타입     → 1 바이트 ⇒  0 또는 1이 32개 모여있음

long 타입 → 1 바이트 ⇒  0 또는 1이 64개 모여있음

- 2진수 : 숫자 2개(0과 1)로 모든 수를 표현

byte 타입으로 예를 들어보자.

8개의 비트가 주어지는데, 가장 왼쪽의 비트는 “부호 비트”의 역할을 하기 때문에, 

양수 일 경우(0) : 0000 0000(0) ~ 0111 1111(+127)

음수 일 경우(0) : 1000 0000(-0) ~ 1111 1111(-127)

인데 -0은 없어. 1000 0000은 “2의 보수”의 개념으로(not x +1) → 이게 남는 숫자이기 때문에 

→ -128~127의 범위를 나타냄

 

만약 byte타입에 리터럴(데이터 그 자체 **상수는 변경되지 않는 수, 둘 이 다른 개념임 ) 127이 들어 있다면, +1을 할 경우 덧셈의 올림이 적용되어서 값을 나타내는 bit가 모두 0으로 바뀌게 되고, 올림 된 1은 버려진다(표현 가능한 비트를 초과했기 때문).

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dab085d7-3b4f-4f9a-9b9e-bb8c3f159c7f/Untitled.png)

------

### Scannner 실행과정 이해에 참고

![제목 없음1.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/caf8cd3d-3d60-4a8d-8039-5db26117ae17/%EC%A0%9C%EB%AA%A9_%EC%97%86%EC%9D%8C1.png)

**nextLine()은 버퍼를 전부 비워주니까!!! 누적된 개행문이 몇 개든 안 남아있음.**
