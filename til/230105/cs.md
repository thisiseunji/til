CS 메모
--- 
디자인패턴
   1. 싱글톤 패턴
-----
디자인 패턴의 정의
---
> 프로그램을 설계할 때 발생했던 문제점들을 객체 간의 상호 관계 등을 이용하여 해결할 수 있도록 하나의 '규약' 형태로 만들어 놓은 것

* 규약에 맞춰 모듈화 시킨 것

디자인 패턴을 기반으로 만들어지는 것이 **라이브러리와** **프레임워크**!

**라이브러리** : 
공통으로 사용될 수 있는 특정한 기능들을 모듈화 한 것으로 폴더명, 파일명 등에 대한 규칙이 없고, 프레임 워크에 비해 자유롭다.

**프레임워크의** : 
공통으로 사용될 수 있는 특정한 기능들을 모듈화 한 것. 폴더명 파일명 등에 대한 규칙이 있으며, 라이브러리에 비해 좀 더 엄격하다.

----

1. 싱글톤 패턴 
하나의 클래스에 오직 하나의 인스턴스만 가지는 패턴. 보통 데이터베이스 연결모듈에 많이 쓰인다.
인스턴스 생성 비용이 줄어든다는 장점이 있기 때문에 I/O바운드 작업에 많이 사용한다. 
(I/O bound 프로세스는 I/O burst 작업이 많은 프로세스를 의미한다. 네트워크, Database, File System에 연결하는 작업은 보통은 시간이 걸리는 작업)
[참고 : IO bound와 CPU bound의 차이](https://blog.naver.com/ding-dong/222623232879)

    ```java
    //java로 싱글톤 패턴 구현하기 
    class Singleton {
        class SingleInstanceHolder {
            private static final Singleton INSTANCE = new Singleton();
        }
        public static synchronized Singleton getInstance() {
            return singleInastanceHolder.INSTANCE;
        }
    }
    ```
    싱글톤 패턴의 단점 :
    * 의존성(종속성)이 높아진다.
        > 'A가 B를 의존한다'는 말은 의존대상인 B가 변하면 그것이 A에 영향을 미친다는 뜻이다.
        * 의존성 주입(Dependency Injection) : 모듈간의 결합을 느슨하게 만듦
          * 의존성 주입의 개념
            ```java
            //1. Composition has a
            class A {
                private B b;

                public A() {
                    b = new B();
                }
            }
            ```
            ```java
            //2. Association has a
            class A {
                private B b;

                public A() {
                    
                }

                public void setB(B b){
                    this.b = b;
                }
            }
            ```
            1은 일체형, 2는 조립형인데 각각의 프로그램을 사용할 때 아래와 같이 사용할 수 있다.
            ```java
            A a = new A();
            ```
            ```java
            B b = new B(); //<-의존성
            A a = new A();

            a.setB(b); //<-주입 
            ```
            a 입장에서는 b가 부품. 이 부품을 a에 꽂는다고 한다면 '주입'
            1번의 방법을 쓰면 부품을 조립해야 한다는 단점이 있는 반면, 부품을 바꿔 끼울 수 있다는 장점이 있다.

            부품을 조립하는 두 가지 방법으로
            1. Setter Injection
            ```java
            B b = new B();
            A a = new A();

            a.setB(b);
            ```
            2. Construction Injection
            ```java
            B b = new B();
            A a = new A(b);
            ```
            이 있다.

            [출처:스프링 프레임워크 강의 3강 - DI(Dependency Injection)](https://www.youtube.com/watch?v=WjsDN_aFfyw)

    * TDD의 걸림돌이 된다. 
        > 단위테스트는 테스트가 서로 독립적이고, 어떤 순서로든 실행할 수 있어야 하는데, 싱글톤 패턴은 미리 생성된 하나의 인스턴스를 기반으로 구현하는 패턴이기 때문에 각 테스트 때 마다 독립적인 인스턴스를 만들기가 힘들다.
----

* 싱글톤 패턴 구현하기(java)
    1. 단순한 메서드 호출
    ```java
    public class Singleton {
        private static Singleton instance;

        private Singleton() {
        }
        public staic Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```
    * 문제점:
        - 자바는 멀티스레드 언어
        - 여러개의 스레드에서의 로직을 생각해야 한다. 
        > T1, T2(각각이 스레드)에서 getInstance를 호출했다고 가정하면, T1에서 instance의 존재를 검증하고 instance를 할당하지 않은 채 T2에서 instance의 존재를 검증한 후, T2에서 instance생성, 이후 T1로 복귀해서 instance생성 하는 순으로 진행될 수 있다.(싱글톤 패턴이 아니게 된다.)
      