// 테스트 필요

package linkedList;

import java.util.Arrays;

public class LinkedList {

// value 하나, node 하나
/*
 * 단일 연결리스트 : 각 원소가 다음 원소의 주소를 들고있음  
 *  이중 : 이전/다음 원소의 주소를 들고있음
 *  원형 : 처음과 끝의 주소가 연결되어있고, 단일/이중  둘 다 상관 없음
 *  메모리를 4또는 8N  만큼 추가로 쓰게 된다.
 */
	static final int MX = 1000005;
	// 값,이전/다음 원소 인덱스,
	// 0번째 더미노드에 해
	static int length = 0;
	static int[] dat = new int[MX];
	static int[] pre = new int[MX];
	static int[] nxt = new int[MX];
	static int unused = 1; // 아직 사용되지 않은 인덱스 
	
	public static void main(String[] args) {
		Arrays.fill(dat, -1);
		Arrays.fill(pre, -1);
		Arrays.fill(nxt, -1);
		treverse();
		insert(1,10);
		insert(2,30);
		insert(3,65);
		insert(4,91);
		insert(3,29);
		insert(5,21);
		insert(6,2);
		treverse();
		erase(2);
		treverse();
		
	}
	
	static void treverse() {
		int cur = nxt[0];
		while (cur != -1) { // 
			System.out.println(dat[cur]);
			cur = nxt[cur];
		}
	}
	// 예를 들어 원소 13이 2번지이고 13 뒤에 20을 새로 추가하고 싶은거면 insert(2, 20)을 호출
						// addr는 앞에서 말한 각 원소의 주소, 즉 배열 상에서 몇 번지인지를 의미
						// pre와 nxt는 상관 없음
	static void insert(int addr, int dat1) {
		dat[unused] = dat1; // 새 데이터 삽입
		pre[unused] = addr; // 이전 데이터를 가리킴 
		nxt[unused] = nxt[addr]; //다음 데이터를 가리킴 
		if(nxt[addr] != -1) { //다음데이터가 없는  경우를 거르는 조
			nxt[addr] = unused; // 이전 데이터가 나를 가리키도록 하고 
			pre[nxt[addr]] = unused; //다음 데이터가 다를 가리키도록 한다.
		}
		unused++;
	}
	//아니? 이 예시에서 어떻게 1번에 원소가 있는거져?.? ?????
	
	static void erase(int addr) {
		// addr 삭제할 인덱스
		if(nxt[addr] != -1){
			nxt[addr-1] = nxt[addr];
			pre[addr+1] = pre[addr];
		}
//		dat[addr] = -1; // 어차피 이 원소는 아무 원소도 접근하지 않기 때문에 값을 초기화 할 필요도 없
//		nxt[addr] = -1;
//		pre[addr] = -1;
		
	}
}
