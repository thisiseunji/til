// 틀렸음
package stack;

import java.io.BufferedReader;
// import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
// import java.util.Stack;

public class Boj1874 {

	public static void main(String[] args) {
		// 입력 : 8개의 숫자가 입력된다.
		// 4, 3, 6, 8, 7, 5, 2, 1
		
		/* 1부터 n까지에 수에 대해 차례로 
		 * [push, push, push, push, 1234
		 * pop, pop, 12 (4, 3)
		 * push, push, 1256
		 * pop, 125 (4, 3, 6)
		 * push, push, 12578
		 * pop, pop, pop, pop, pop] (4, 3, 6, 8, 7, 5, 2, 1) 
		 *  연산을 수행하면 				스택의 가장 밑 부분
		 *  수열 [4, 3, 6, 8, 7, 5, 2, 1]을 얻을 수 있다. => 문제는 일단 주어진 수열을 넣었다 뺐다 하면서 얻을 수 있는가 하는 문제네
		 *  입력은 오름 차순으로 주어진다. 반드시 작은 수부터 (1-8까지 순서대로 숫자가 주어지는데,)
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int size = Integer.parseInt(br.readLine()); // 주어지는 수열의 크기
			int[] input = new int[size]; 
			for(int i = 0; i < size; i++) {
				input[i] = Integer.parseInt(br.readLine());
			}  //입력받은 숫자 배열, 0번 인덱스부터 찾아야 함
			
//			1. stack이 비었을 경우 push
//			2. push 한 후, 배열의 현재 인덱스 값과 비교
//			3-1. 같으면 pop 하고 arr의 인덱스를 +1한다(다음값과의 비교 진행)
//			3-2. 만약에 arr의 첫 번째 값이 더 크면 push한다. - push 할 숫자가 없으면 끝
//			3-3. 만약에 arr의 첫 번째 값이 더 작으면 pop 한다. - stack이 비면 끝
//			
//			언제 종료되지?
//			1. arr이 끝나면 - 모든 배열을 숫자로 작성할 수 있는 것이므로 끝 
//			-> 스택도 비었다는 것./ 근데 중간에 빌 수도 있음.
//			2. stack에 size까지의 값이 담겼다가 빠져나오면 깃발에 상관 없이, => 사이즈 까지의 숫자가 기준이 되어야 한다. () 저장되는 숫자가 기준이 되어야 하고 얘는 고정되어야 함.
//			
//			stack의 index를 for문으로 돌리쟈
			
			int[] stack = new int[size]; // 걍 배열을 끝까지 담을 수 있어야하므로 
			
			int iIdx = 0; // iIdx 내가 지금 찾아야 하는 숫자의 인덱스 - input[iIdx] 꼴 - 얘를 사이즈와 비교해야하지. 
			int sIdx = 0; // sIdx 스텍에 다음! 저장값의 인덱스
			int num = 1; // 스텍에 넣어야 하는 숫자 1-8 => push를 해야하는데, 더 이상 넣을 숫자가 없을 때 끝나야 함 size와 iNum, 들어갈 숫자 구나 얘를 input[iIdx]와 비교
			//String answer = ""; // '+,','-' 저장 - 메모리 초과의 원인임
			Queue<Character> answer = new LinkedList<Character>();

			while(num <= size) { //1~n까지니까 
				System.out.println("i : "+ iIdx);

				if(sIdx == 0 || stack[sIdx-1] < input[iIdx]) { 
					//stack이 비어있거나, stack의 top보다 찾아야 할 값이 더 클 때 : push가 일어나야 하는 상황
					stack[sIdx++] = num++; // 스택에 현재 값을 넣고, sIdx+1
					answer.add('+');
				} else { //pop이 일어나야 하는 상황 : 입력받은 수의 값이 stack의 top보다 작거나 같을 때, 
					if(input[iIdx] == stack[sIdx-1]) {
						 iIdx++; //다음 수를 찾아야 해, 에러 안 날거임
					}
					stack[--sIdx] = 0;
					answer.add('-');
				}
			}
			
			// 우선 num이 8까지 저장하고, 만약에 탐색이 안 끝났으면 (stack이 비어있거나, iIdx가 size보다 작을 때  같은지를 비교해!) 
			while(iIdx < size && 0 < sIdx ) {
				System.out.println("i after : "+ iIdx);
				//stack의 top이 현재 iIdx와 같지 않으면 NO임
				if(stack[sIdx-1] != input[iIdx]) {
					System.out.println("NO");
					return;
				} else {
					iIdx++;
					stack[--sIdx] = 0;
					answer.add('-');
				}
			}

			while(!answer.isEmpty()){
				System.out.println(answer.poll());
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

// 틀렸음
// public class Boj1874_stack {

// 	public static void main(String[] args) {

// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		try {
// 			int size = Integer.parseInt(br.readLine()); 
// 			int[] input = new int[size]; 
// 			for(int i = 0; i < size; i++) {
// 				input[i] = Integer.parseInt(br.readLine());
// 			} 

// 			Stack<Integer> stack = new Stack<>();
// 			int idx = 0; 
// 			int num = 1; 
// 			Queue<Character> answer = new LinkedList<Character>();
// 			while(num <= size) { 
// 				if(stack.empty() || stack.peek() < input[idx]) { 
// 					stack.push(num++);
// 					answer.add('+');
// 				} else {
// 					if(input[idx] == stack.peek()) {
// 						idx++; 
// 					}
// 					stack.pop();
// 					answer.add('-');
// 				}
// 			}
			
// 			while(idx < size && !stack.empty()) {
// 				if(stack.peek() != input[idx]) {
// 					System.out.println("NO");
// 					return;
// 				} else {
// 					idx++;
// 					stack.pop();
// 					answer.add('-');
// 				}
// 			}

// 			while(!answer.isEmpty()) {
// 				System.out.println(answer.poll());
// 			}
			
// 		} catch (NumberFormatException e) {
// 			e.printStackTrace();
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}

// 	}
// }