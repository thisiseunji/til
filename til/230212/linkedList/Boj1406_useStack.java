package linkedList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;

public class Boj1406_useStack{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Character> chList = new LinkedList<>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		Stack<Character> input = new Stack<>();
		Stack<Character> tmpSave = new Stack<>();

		for(int i = 0; i < str.length(); i++) {
				input.push(str.charAt(i));
		} // stack에 문자 담기 

		int cnt = Integer.parseInt(br.readLine());
		// 입력되는 명령어 수 만큼 실행 
		for(int i=0; i < cnt; i++) {
			String tmp = br.readLine();
			char t = tmp.charAt(0);
			if ((t=='L') &&  !input.isEmpty()) {
				tmpSave.push(input.pop());
			} else if ((t=='D') && (!tmpSave.isEmpty())) {
				input.push(tmpSave.pop());
			} else if((t=='B') && (!input.isEmpty())){
				input.pop();
			} else if(t=='P') {
				input.push(tmp.charAt(2));
			}
		}
		tmpSave.clear();
		while(!input.isEmpty()) {
			tmpSave.push(input.pop());
		}

		while(!tmpSave.isEmpty()){
			bw.write(tmpSave.pop());
		}
			
		bw.flush();
		bw.close();
		}
}