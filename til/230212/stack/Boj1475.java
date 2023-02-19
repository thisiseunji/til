package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1475 {

	public static void main(String[] args) {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String roomNum;
		try {
		 roomNum = br.readLine(); 
		 int[] cnt = new int[9]; // 0-8까지의 배열 만들기 *9는 6과 함께 저장
         int tmp = 0;
		 for(int i = 0; i < roomNum.length(); i++) {
            tmp = roomNum.charAt(i)-48;
            if (tmp == 9) {
                cnt[6]++;
            } else {
                cnt[tmp]++;
            }
		 }
		 
		 if (cnt[6]++!= 0) {
			 cnt[6]/=2;
		 }
		 int max = 0;
		 for(int i = 0; i < cnt.length; i++) {
			 if(max < cnt[i]) {
				 max = cnt[i];
			 }
		 }
		 System.out.println(max);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} 
	}
}
