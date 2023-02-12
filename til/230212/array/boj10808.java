package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10808 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] cnt = new int[26];
		//소문자 97-122까지
		for(char c : str.toCharArray()) {
			 cnt[c-97]++;
		}
		for(int i = 0; i < 26; i++) {
			System.out.print(cnt[i] + " ");
		}
	}
}
