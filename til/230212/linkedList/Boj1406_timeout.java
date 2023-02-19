package linkedList;
//시간초과 난다... 시간초과 나지 않는 방법을 찾아보자!
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Boj1406 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Character> chList = new LinkedList<>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		int curser = str.length(); //커서의 시작 위치

		for(int i = 0; i < curser; i++) {
				chList.add(str.charAt(i));
		} // LinkedList에 문자 담기

		int cnt = Integer.parseInt(br.readLine());
		// 입력되는 명령어 수 만큼 실행 
		for(int i=0; i<cnt; i++) {
			String tmp = br.readLine();
			char t = tmp.charAt(0);
			if ((t=='L') && (curser != 0) ) {
				curser--;
			} else if ((t=='D') && (curser<str.length())) {
				curser++;
			} else if((t=='B')&&(curser != 0)){
				chList.remove(--curser);
			} else if(t=='P') {
				chList.add(curser++,tmp.charAt(2));
			}
		}
		for(Character c : chList) {
			bw.write(c);
		}
	}
}