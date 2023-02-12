package stack;

public class stack {
    
    static int pos = 0; //다음 원소가 추가될 때 삽입해야 하는 곳
    
    public void main(String[] args) {
        static final int MX = 1000005;
        int[] stack = new int[MX];
        push(stack, 1);
        push(stack, 3);
        push(stack, 5);
    }

    static void push(int[] stack, int x) {
        stack[pos++] = x;
        // pos++; 이거 될까?
    }

    static int top(int[] stack) {
        return stack[--pos];
    }

    static int pop(int[] stack) {
        int answer = stack[pos];
        pos--;
        return answer;
    }

}

