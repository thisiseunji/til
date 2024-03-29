- IP주소와 Network
    
    > Network와 IP주소의 개념을 비유로 이해해보자.
     대한민국의 행정 체계 안에서 개인에 대한 식별을 하는 것은 주민등록 번호다. 
    Network는 대한민국의 행정 체계에, IP는 개인을 식별하는 주민등록 번호에 비유할 수 있다. 
    네트워크 환경 안에서 Host를 식별해주는 것이 IP이다.
    > 
    
    ### IP주소
    
    - Host에 대한 식별자
    - IP(Internet Protocol)에는 두 가지 버전이 있다.
        - IPv4 : 32bit(약 43억 미만)의 주소 체계 사용 : 2^32개의 Host로 네트워크 사용자가 제한됨.
        - IPv6 : 128bit(약 ) 주소 체계사용
    
    현재 일반적으로 인터넷 프로토콜은 IPv4를 사용한다.
    
    ### IPv4(이하 IP)
    
    ```java
    IPv4 주소 : 192.168.60.14
    서브넷 마스크 : 255.255.255.0
    ```
    
    ```java
    IPv4 주소 : 192.168.60.14/24
    ** 위와 같은 표현(서브넷 마스크에서 1의 갯수가 24개 임을 나타낸다.)
    ** 이와같이 표현하는 방법을 -> "Prefix표기법"이라 한다. 
    ```
    
    - IP는 ‘.’을 기준으로 0~255(2^8)까지의 숫자가 부여된다. (8bit씩 끊어서 표시, 192 ⇒ 1100 0000(2))
    - IP는 192.168.60 | 14로 구별 되는데 각각 Network ID, Host ID를 나타낸다.
    - 위의 예에서는 각각 32 중 Network ID | Host ID ⇒ 24 | 8 bit 로 표현된다.
    - IP 주소에서 Network ID의 길이가 얼마인지 나타내는게 바로 “(서브)넷마스크”
    - IP주소와 서브넷 마스크의 bit AND연산을 하면 Network ID를 얻을 수 있다.
        
        > 맨 앞에 비트부터 1이 연속된 구간까지를 공통 비트로 처리하여 Network ID로 사용하고 0으로 끝나는 마지막 구간까지를 공통으로 하지 않는 비트로 처리하여 Host ID로 사용한다.
        > 
    
- Port번호는 Process 식별자
    
    ### 포트번호란?
    
    - kernel 수준에서 구현되어있는 TCP/IP에, user모듈의 Process에서 접근하기 위해 인터페이스가 제공된다.
    - 이 인터페이스는 기본적으로 파일의 형태로 구현된다.
    - 이 인터페이스가 ‘프로토콜’을 추상화 했을 때, 이 파일을 ‘Socket(소켓)’이라고 한다.
    - **포트번호**는 이 소켓이 TCP소켓인 경우, 생성되면서 Attach 되는 정보 중 하나이다.
    - 즉 ⇒ 프로세스에서 TCP/IP에 접근하기 위한 인터페이스를 식별하는 정보인거네..!
    
    ### TCP/IP포트 번호
    
    - 기본적으로 16bit-2 의 경우의 수를 가진다(0-65535까지 인데, 0과 65535는 쓰면 안된다. 언젠가 프로토콜 분석하는 프로그램을 직접 개발해보면 쉽게 이해할 수 있다고 한다. 이런것도 있구나…)
    
    > 192.168.0.10 이라는 IP가 있고, 이 Host에서 두 개의 프로세스( 예를 들어 엣지, 크롬)가 실행되고 있다고 가정하자 . 엣지의 포트번호가 30000이라고 하면 크롬의 포트번호는 이와 달라야 한다.
    > 
    - TCP/IP의 경우, 인스턴스가 여러개 생기는 것은 아니다.
    - 패킷이 데이터 전송 흐름을 타고 쭉 전송 되다가, 어떤 프로세스로 갈지가 포트번호로 결정되는 것이다.
    
- Switch가 하는 일은 Switching
    
    > 네트워크는 ‘고속도로’에 비유하면…
    > 
    
    [네트워크는 스위치로 이뤄져있다](https://www.notion.so/eb1f77651c994d22a4bf47aed8ed5eff). 네트워크가 고속도로라면 Switching은 경로 혹은 인터페이스를 ‘선택’하는 역할을 한다. (Switch는 교차로의 역할을 하는 것)
    
    > 인터넷은 Router의 집합체다.
    > 
    
    라우터는 기본적으로 [L3 스위칭](https://www.notion.so/eb1f77651c994d22a4bf47aed8ed5eff)을 한다. 고속도로가 ‘인터넷’이라고 한다면, 라우터가 교차로의 역할을 하는 것이다. 4거리 교차로일 경우 ⇒ 네트워크 인터페이스가 4개인 라우터가 되는 것이다. 어떤 자동차(정보,  패킷)가 도착했을 때, 도착지점을 제외한 3개의 경로 중 하나를 선택하는 것이 스위칭 이고, 이 때 가장 효율적인 길로 가기 위해 라우터끼리 통신을 통해 결정한다. 
    
    효율적인 길로 가기 위해 고속도로에 **이정표**가 존재하는 것 처럼 라우터에게도 비슷한 개념이 존재하는데, 이를 **“라우팅 테이블(의사결정의 기준)”**이라고 한다. 
    
- 네트워크 데이터 단위 정리(매우 중요!)
  - user모드의 ‘어플리케이션 계층’ ⇒ 소켓수준
  - kernel을 추상화한 인터페이스를 “소켓”이라고 한다. 여기까지 일단 OK

  > 파일 입출력을 생각해보자.
  > 

  파일은 입력하면 길이가 늘어난다. ⇒ “Stream”

  - Stream은 시작은 있지만, 끝은 정해지지 않아서 길이가 무척 늘어날 수 도 있는 데이터이다.  ⇒ IP에서 쓰는 데이터 단위(packet)보다 커질 수도 있다는 뜻
  - TCP에서 다루는 데이터 단위는 Segment
  - 하드웨어 모드에서 다루는 데이터 단위는 Frame이라고 한다.

  > 어플리케이션 프로세스가 파일에 Steam data를 Write한다.
  > 

  그럼 User 모드에서 커널로 내려갈 때, 이 스트림 데이터는 Segment 단위로 분해되는데, 이를 Segmentation이라고 한다. 

  - 분해된 길이의 최대 크기는 정해져 있고 이를 Maximum Segment Size:MMS라고 한다.
  - MMS는 패킷의 최대 크기(MTU: Maximum Transport Unit)에 기반해 정해진다.
  - MTU는 특별한 이유가 없다면 1500byte(1.5kb) ⇒ 1.5메가 바이트의 스트림 데이터를 전송하면 적어도 1000개의 패킷이 나오겠지..!
  - 이 패킷은 또 Frame으로 **Encapsulation**이 된다. (나중에 설명)

  정리하면,

  - 데이터는 유저모드 어플리케이션 프로세스 수준, 즉 소켓 수준에서는 스트림 데이터 이다.
  - 스트림 데이터는 일렬로 늘어선 아주 긴~~~데이터다.
  - 그걸 네트워크로 보낼때는 세그멘테이션이 일어나고, 그 잘려진 조각을 세그먼트라고 한다.
  - 그걸 인터넷 환경에서 전송이 가능하도록 일종의 포장한 것을 패킷이라고 한다.
  - 패킷의 최대 크기는 1500byte.
  - 이런 패킷을 실어나를 때는 다시 프레임 데이터에 넣는다 ⇒ 이게 인캡슐레이션.