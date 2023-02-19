
package stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//답안
public class Boj1874_답 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 수열의 size
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Integer> stack = new Stack<>();
		Queue<String> queue = new LinkedList<String>();

		int index = 1; 
		boolean flag = true;
		for(int i =0; i < n; i ++) {
			int num = Integer.parseInt(br.readLine());
			while(index<=num) {
				stack.push(index++);
				queue.add("+");
			}
			if(stack.lastElement()==num) {
				stack.pop();
				queue.add("-");
			}else if(stack.lastElement()>num) { // 둘 다 n만큼의 수가 들어있기 떄문인가?
				flag=false;
				break;
			}
		}

		if(flag==false) {
			bw.write("NO");
		} else {
			while(!queue.isEmpty()) {
				bw.write(queue.poll()+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
