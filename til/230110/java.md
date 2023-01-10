# 프로그래밍 기초

- 프로그램 : 컴퓨터가 인식할 수 있는 **명령어**의 집합
- 프로그래밍 언어 : 프로그램 작성을 위한 **언어체계**

컴퓨터는 기계어(0,1/ 이진수)만 알아들을 수 있다.

**자연어와 기계어의 중간다리 역할**을 하는 언어 → 프로그래밍 언어

## JAVA

### 자바의 특징

- 운영체제(컴퓨터를 구동시키기 위한 SW)에 독립적(이식성이 높음)
- 사용하기 쉬운 언어(포인터, 메모리) → 기능구현에 집중하기 좋다.
    - 다른 언어의 단점 보완(C : 메모리 할당, 반납 수동으로 해야함)
    - 객체 지향 언어 → 명령어를 **부품화** 한다.
    - 능률적이고 명확한 코드 작성 가능(자연어와 비슷한 단어가 多)
- 자동 메모리 관리(GC : Garbage Collector) : 비어있는 공간이 있는지 주기적으로 검사. 공간이 계속 비어있으면 자동으로 청소
- 네트워크와 분산환경 지원 : ZOOM처럼 물리적 거리에 상관 없이 소통이 가능하도록 하는 기능
- 멀티쓰래드 지원
    - 멀티쓰래드 : 하나의 프로그램이 동시에 여러가지 일을 수행할 수 있는 것(ex: 멜론 음악 들으며 음악검색, 후기 남기기)

*맨 밑의 두 기능은 다른 언어들도 지원한다. 

### 자바의 실행 원리

- 자바의 실행 과정
    1. 자바 코드 짜기(.java 확장자로 저장) *확장자란 파일의 종류 나타내는 개념
    2. 컴파일러가 .java → .class 확장자로 파일 번역(프로그래밍 언어에서 byte code로) : 컴파일과정 / 컴파일러는 jdk에 있음 
    3. .class파일이 JVM에 의해 실행
    

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0ff2ca2f-9ae2-441e-9504-83dc91859aaa/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/174c0d49-ed1f-4221-8e3a-c3df1375ab8b/Untitled.png)

- 핵심은 **JVM(JAVA Virtual Machine)** *Virtual이라는 말이 붙으면 보통 SW임
    
    → 자바로 만든 프로그램을 실행시킬 수 있도록 도와주는 프로그램
    
    → 자바가 운영체제 독립적일 수 있도록 하는 자바의 핵심 부품
    
    → byte code(.class)를 **해석**하고 **실행**하는 interpreter
    

### 자바의 설치 범위

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68d5b623-4328-4f8e-b1d5-f812a58ffd2e/Untitled.png)

- 사용자로서 자바로 만들어진 프로그램을 사용하기 위해서는 JRE(JVM, Java API)만 설치하면 됨
- 개발자는 Compiler, java Tool이 함께 있는 JDK설치 필요(버전이 다름)
    - 컴퓨터 프로그램을 설치해서 사용하는 프로그램은 SE면 된다.
    - 웹 개발 용으로는 EE가 필요 → 일반 자바 버전 + alpha(웹개발용)