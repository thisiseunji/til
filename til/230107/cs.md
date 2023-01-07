싱글톤 패턴 구현하기
=====
사용언어 : JAVA

* 메서드 호출
```java
//원자성이 결여 된 싱글톤 패턴
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
    > T1, T2(각각이 스레드)에서 getInstance를 호출했다고 가정하면, T1에서 instance의 존재를 검증만 진행하고 아직 instance를 할당하지 않은 채 T2에서 instance의 존재를 검증한 후, T2에서 instance생성, 이후 T1로 복귀해서 instance생성 하는 순으로 진행되는 경우가 있다.(싱글톤 패턴이 아니게 된다.)

위의 문제는 'Synchronized'키워드를 사용하여  multi-thread에 대한 동시접근을 막음으로써 해결할 수 있다. 
 ```java
//synchronized 키워드를 사용
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }
    public staic synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
> Synchronized 키워드가 포함 된 함수는 최초로 접근한 스레드가 해당 메서드를 호출 할 때, 다른 스레드가 접근하지 못 하도록 lock을 건다.
> 이는 동기화 문제를 해결하는 가장 간단한 방법이지만 getInstance()를 호출 할 때마다 lock이 걸려 성능저하가 발생한다.
[Java의 동기화 Synchronized 개념 정리#1](https://tourspace.tistory.com/54) 
----


* 정적멤버
Static 멤버나 블록은 런타임이 아닌 JVM이 클래스를 로드할 때 미리 인스턴스를 생성한다는 성질을 이용하여, 클래스 로딩과 동시에 싱글톤 인스턴스를 만들고 인스턴스를 요청하는 모듈이 있을 때, 만들어진 인스턴스를 반환하는 방법으로 싱글톤 패턴을 구현한다.

문제점 : 싱글톤 인스턴스가 필요하지 않을 때도 인스턴스를 생성하므로 불필요하게 자원이 낭비된다는 문제가 발생한다. 

```java
// 정적멤버
public class Singleton {
    private final static Singleton instance = new Singleton();

    private Singleton(){}

    public staic Singleton getInstance() {
        return instance;
    }
}
```
```java
//정적 블록
public class Singleton {
    private static Singleton instance = null;

    static {
        instance = new Singleton();
    }

    private Singleton(){}

    public staic Singleton getInstance() {
        return instance;
    }
}
```
----

* 정적 멤버와 Lazy Holder(중첩 클래스)
중첩 클래스를 통해 getInstance가 호출 됐을 때만 자원을 할당한다.

```java
class Singleton {
    private static class singleInstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton(){}

    public staic Singleton getInstance() {
        return singleInstanceHolder.INSTANCE;
    }
}
```
* 이중 확인 잠금(Double Checked Locking)
인스턴스 생성여부를 싱글톤 패턴 lock 전에 한 번, 객체를 생성하기 전에 한 번 확인한다. 
```java
public class Singleton {
    private volatile Singleton instance;
    private Singleton() {}
    public Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
> volatile 키워드는 자바 코드의 변수를 '메인 메모리에 저장'할 것을 명시하기 위해 쓰인다.(CPU캐시가 아닌 메인 메모리에 데이터를 저장) java는 다중 스레드를 처리할 때, Task를 수행하는 동안 성능 향상을 위해 Main Memory에서 읽은 변수 값을 CPU Cache에 저장한다.
> Multi Thread환경에서 Thread가 변수 값을 읽어올 때 각각의 CPU Cache에 저장된 값이 다르기 때문에 변수 값 불일치 문제가 발생하게 된다.
[출처 : 자바 volatile이란](https://nesoy.github.io/articles/2018-06/Java-volatile)

* enum
> Enum의 성질 : 
> * 상수들만 모아놓은 클래스라고 할 수 있다. 때문에 클래스처럼 메소드, 생성자를 모두 가질 수 있다.
> * enum은 고정된 상수들의 집합으로써, 런타임이 아닌 컴파일타임에 모든 값을 알고 있어야 한다. 즉, 생성자가 private로 제한된다. 런타임시에 값이 설정되면 안되기 때문이다.  때문에 enum클래스 내에서도 인스턴스 생성이 불가능하다. 때문에 사용자 입장에서 보면 인스턴스는 없지만 선언된 enum 은 존재한다.

Enum은 private 생성자로 인스턴스 생성을 제어하며, 상수만 갖는 특별한 클래스이기 때문에 싱글톤의 성질을 일반적으로 갖게된다. 때문에 싱글톤을 간단히 구현할 때 사용되곤 한다.

```java 
public enum SingletonEnum {
    INSTANCE;
}
```
[[JAVA] Enum을 이용해서 Singleton을 구현하자](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kbh3983&logNo=220907314096)
