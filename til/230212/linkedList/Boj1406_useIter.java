package linkedList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Boj1406_useIter{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Character> chList = new LinkedList<>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		int curser = str.length();

		for(int i = 0; i < curser; i++) {
				chList.add(str.charAt(i));
		} // LinkedList에 문자 담기

		// iterator 메소드 호출
		// iterator는 양방향 탐색이 가능하다.
		ListIterator<Character> iter = chList.listIterator();
		// 커서를 맨 뒤로 이동시키는 작업 
		while(iter.hasNext()) {
			iter.next();
		}
		
		int cnt = Integer.parseInt(br.readLine());
		// 입력되는 명령어 수 만큼 실행 
		for(int i=0; i<cnt; i++) {
			String tmp = br.readLine(); // i번째 명령어
			char t = tmp.charAt(0);
			if ((t=='L') && (iter.hasPrevious()) ) {
				iter.previous();
			} else if ((t=='D') && (iter.hasNext())) {
				iter.next();
			} else if((t=='B') && (iter.hasPrevious())){
				iter.previous();
				iter.remove();
			} else if(t=='P') {
				iter.add(tmp.charAt(2));
			}
		}
		
		for(Character c : chList) {
			bw.write(c);
		}
		bw.flush();
		bw.close();
	}
}