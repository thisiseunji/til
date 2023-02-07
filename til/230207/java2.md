# 컬렉션
- 자바에서 제공하는 자료구조를 담당하는 프레임워크
- 추가, 삭제, 정렬 등의 기능처리가 간단하게 해결 되어 자료구조적 알고리즘을 구현할 필요 없음
- java.util 패키지에 포함

## 자료구조

- 메모리상에서 자료를 구조적으로 처리하는 방법

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/13a1159a-5428-4caa-8934-d6c2a5a48c69/Untitled.png)

**선형구조를 먼저 보자.

일반리스트 : 배열처럼 생겼음. 

# 컬렉션(Collection)

- 자료구조가 내장되어있는 클래스로 자바에서 제공하는 “자료구조”를 담당하는 “프레임워크”이다.
    - 자료구조 : 방대한 데이터를 효율적(구조적)으로 다룰 때 필요한개념
    - 프레임워크 : 효율적인 기능들이 이미 정의되어있는 틀
- 정리해보면 데이터들이 새로 추가되거나, 삭제되거나, 수정이 되는 기능(알고리즘)들이 이미 정의되어있는 틀이 있다. == 컬렉션
- 다량의 데이터를 관리하고자 할 때, 배열을 가지고 충분히 사용할 수 있었음
- 단, 배열의 단점들을 보완한 개념이 컬렉션이다!!

### 배열과 컬렉션의 차이점

**배열**

- 사이즈 변경이 안된다.
- 배열에 기록된 데이터에 대한 중간 위치의 추가, 삭제가 불편하다.
- 한 타입의 데이터만 보관 가능하다.

**컬렉션**

- 선언 시에 크기 지정을 해줄 필요도 없고, 만일 크기 지정을 하더라도 알아서 크기가 늘어나면서 새로운 데이터들을 계속 추가할 수 있음
- 중간에 값을 추가하거나 삭제하는 경우, 이미 값을 당기거나 밀어주는 코드가 메소드로 정의되어있음 ⇒ 메소드 정의에 따라 호출만 잘 하면 된다.
- 기본적으로 여러 타입의 데이터들을 저장할 수 있음
    
    > 단, Generic(제네릭)설정을 통해 한 타입의 데이터들만 들어올 수 있게 설정할 수도 있다.
    > 

따라서 

조회를 목적으로 하는 데이터 ⇒ 배열(Array)

추가, 수정, 삭제 등이 빈번하게 일어날 때, ⇒ 컬렉션(Collection) 사용 

### 컬렉션의 3가지 분류

Map은 Collection의 자식이 아니기 때문에, 엄연히 말하면 Collection은 아님

** Vector는 검사하는 수정 시마다 검사하는 과정을 거친다. 

**Set은 순서의 개념이 없으므로 반복문에 응용하지 못한다.

```java
//ArrayList 관련학습

package com.kh.chap01_list.part01_arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.chap01_list.part01_arrayList.model.vo.Music;

public class ListRun {

	public static void main(String[] args) {
		// 배열의 경우
		// 자료형[] 배열명 = new 자료형[사이즈];
		
		// ArrayList의 경우
		// ArrayList list = new ArrayList(); 
		// 내부적으로 크기 10칸짜리인 배열이 생성됨 (아무거나 다 담을 수 있음 Object타입이라서) -> 이거 그냥 평소랑 똑같은 배열이 생기는거야
		ArrayList list = new ArrayList(3); //내부적으로 크기가 3인 배열이 생성된다. 
		System.out.println(list); // [] : 빈 리스트
		
		// E --> Element : 제네릭(현재는 Object 타입을 지칭)
		// 1. add(E e) : 해당 리스트의 끝에 매개변수로 전달된 e를 추가시켜주는 메소드
		list.add(new Music("Ditto", "뉴진스"));
		list.add(new Music("OMG", "뉴진스"));
		list.add(new Music("Hype boy", "뉴진스"));
		list.add("end");
		System.out.println(list);
		// 순서가 유지 되면서 값이 추가
		// 사이즈 확장 가능
		// 크기에 제약 없음
		
		// 2. add(int index, E e) : 리스트에 전달되는 index위치에 전달되는 e를 추가시켜주는 메소드 
		list.add(1, new Music("VIBE", "태양")); // 이게... 삽입이네 insert네..!
		System.out.println(list);
		
		//3. set(int index, E e) : index방에 있는 애를 e로 바꿔줘 list[index] = e; 가 되겠다.
		list.set(0, new Music("사건의 지평선", "윤하")); //set이 replace인가봐
		System.out.println(list);
		
		//4. remove(int index) : 리스트의 해당 인덱스의 값을 삭제시켜주는 메소드, 값을 삭제 시키고 빈 자리를 채워준다.
		list.remove(1);
		System.out.println(list);
		//중간에 값 삭제 시 알아서 내부적으로 기존의 값들을 알아서 앞으로 한 칸씩 당겨주는 작업
		
		//index가 있으니 반복문 사용 가능, size()를 통해 현재 리스트에 담겨있는 데이터의 갯수를 반환해주는 메소드
		System.out.println("리스트에 담긴 데이터의 갯수 : " + list.size());
		System.out.println("리스트의 마지막 인덱스 : " + (list.size()-1));
		
		//배열의 경우
		//배열명[index]
		//ArrayList의 경우 메소드를 호풀해서 데이터를 조회
		
		//6. get(int index) : 반환 타입 e(현재는 Object 타입)
		//리스트에 현재 위치의 데이터를 반환해주는 메소드
//		Music m = (Music)list.get(1); //Object to Music 
		System.out.println(((Music)list.get(1)).getTitle()); //형변환 먼저 - 이거 괄호가 없으면 .이 먼저 호출 돼서 에러임
	
		System.out.println("==================================");
		
		//0-마지막 인덱스까지 출력
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 향상된 for문
		for(Object o: list) {
			System.out.println(o);
		}
		
		System.out.println("==================================");

		//7.subList(int beginIndex, int endIndex):해당 리스트로부터 endIndex-1까지의 데이터 값들을 추출해서 새로운 List(!= ArrayList)로 반환		
		List sub = list.subList(0, 2); //java.util의 List, 인터페이스라서  객체 생성은 안돼
		System.out.println(sub); //=>원본 리스트는 훼손되지 않는다. 
	
		System.out.println("==================================");
		
		//8. addAll(Collection c)
		System.out.println(list.addAll(sub)); //true false 가 나오네
		
		System.out.println(list);
	
		System.out.println("==================================");
		
		//9. isEmpty() : 해당 리스트가 비어있는지 묻는 리스트(비어있으면 true 아니면 false 반환)
		System.out.println(list.isEmpty());
		
		System.out.println("==================================");

		//10. clear() : 리스트비우기
		list.clear(); //=>리턴이 void잖아
		System.out.println(list);
		System.out.println(list.isEmpty());
		System.out.println(list.size());
		
	}

	
}

/*
[]
[Music [title=Ditto, artist=뉴진스], Music [title=OMG, artist=뉴진스], Music [title=Hype boy, artist=뉴진스], end]
[Music [title=Ditto, artist=뉴진스], Music [title=VIBE, artist=태양], Music [title=OMG, artist=뉴진스], Music [title=Hype boy, artist=뉴진스], end]
[Music [title=사건의 지평선, artist=윤하], Music [title=VIBE, artist=태양], Music [title=OMG, artist=뉴진스], Music [title=Hype boy, artist=뉴진스], end]
[Music [title=사건의 지평선, artist=윤하], Music [title=OMG, artist=뉴진스], Music [title=Hype boy, artist=뉴진스], end]
리스트에 담긴 데이터의 갯수 : 4
리스트의 마지막 인덱스 : 3
OMG
==================================
Music [title=사건의 지평선, artist=윤하]
Music [title=OMG, artist=뉴진스]
Music [title=Hype boy, artist=뉴진스]
end
Music [title=사건의 지평선, artist=윤하]
Music [title=OMG, artist=뉴진스]
Music [title=Hype boy, artist=뉴진스]
end
==================================
[Music [title=사건의 지평선, artist=윤하], Music [title=OMG, artist=뉴진스]]
==================================
true
[Music [title=사건의 지평선, artist=윤하], Music [title=OMG, artist=뉴진스], Music [title=Hype boy, artist=뉴진스], end, Music [title=사건의 지평선, artist=윤하], Music [title=OMG, artist=뉴진스]]
==================================
false
[]
true
0

*/
```

---

## 제네릭

[제네릭(Generic) 문법](https://dabok407.tistory.com/31)

- 제네릭스는 다양한 타입의 객체들을 다루는 메소드나 컬렉션 클래스에 컴파일시 타입체크를 해주는 기능이다.
- 제네릭은 클래스나, 메소드에 선언할 수 있다.

```java
class Pet<Species> {
	private T t;

	public void set(T t) {
		this.t = t;
	}
	public T get() {
		return t;
	}
}

// 만일, 제네릭으로 선언된 데이터를 반환형으로 사용하고자 할 때는 제네릭을 함께 기술해준다. 
....
public <String> Pet<Species> method1() {
		return new Pet<>();
}
```

<E> 별도의 제네릭 제시 없이 컬렉션 객체를 생성하면 (E==Object)

다형성에 의해 해당 컬렉션에 다양한 타입의 데이터 값들이 담길 수 잇다.

별도의 제네릭 설정으로 <Music>으로 하게 되면 (E = Music)

마치 배열처럼 Music 타입의 데이터만 담을 수 있다.

즉, <여기>에 해당 컬렉션이 사용할 객체의 타입을 지정하는 개념

⇒ 내가 사용하고 싶은 데이터 타입만 사용할 수 있게 제한하는 효과를 준다.

### 제네릭 설정을 하는 이유

1. 객체의 타입을 컴파일 시 체크하기 때문에, 객체의 타입 안정성을 높인다.
2. 1의 장점에 이어 형변환의 번거로움이 줄어든다. 

 타입 안정성을 높인다는 것은 의도하지 않는 타입의 객체가 저장되는 것을 막고, 저장된 객체를 꺼내올 때 원래의 타입과 다른 타입으로 잘못 형변환되어 발생할 수 있는 오류를 줄여준다는 것.

### 제네릭 설정시 주의사항

- “객체”만 제네릭으로 설정 가능하다.(기본형은 안된다.) ⇒  Wrapper 형식으로는 기술 가능하다. AutoBoxing 일어나면서 들어갈 수 있음.