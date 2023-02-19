import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10773 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			/*
			 * size를 입력받아 그만큼의 배열을 만든다. 
			 * next를 통해 다음 값이 들어갈 인덱스를 저장한다. 
			 * answer에 반환할 값을 누적 한다. 
			 * 
			 * 1. 입력을 받을 때 마다 0인지 확인하고
			 * 2. 0이 아닐 경우 arr[next]에 값을 저장하고, next++ 한다. 
			 * 2-1. 0일 경우, next-1 한 후, arr[next] = 0 한다.
			 * 3. arr의 모든 값을 더해서 반환한다. 
			 * 
			 */
			int size = Integer.parseInt(br.readLine());
			int next = 0; 
			int answer = 0;
			
			int[] arr = new int[size]; // 이게 스텍임
			
			for (int i = 0; i < size; i++) {
				int tmp = Integer.parseInt(br.readLine());
				if(tmp != 0) {
					arr[next] = tmp;
					next++;
				} else {					
					arr[--next] = 0;
				}
			}
			for(int i=0; i <size; i++) {
				answer+=arr[i];
			}
			System.out.println(answer);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}