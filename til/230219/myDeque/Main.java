package myDeque;

public class Main{
    public static void main(String[] args) {
        MyDeque dq = new MyDeque();
        System.out.println(dq.isEmpty());
        dq.push_front(1);
        dq.push_front(2);
        dq.push_front(3);
        dq.push_front(4);
        dq.push_back(100);
        dq.push_back(101);
        dq.push_back(102);
        dq.push_back(103);
        System.out.println(dq.size());
        System.out.println(dq.pop_front());
        System.out.println(dq.pop_back());
        System.out.println(dq.front());
        System.out.println(dq.back());
        System.out.println(dq.isEmpty());
        System.out.println(dq.size());

        // true
        // 8
        // 4
        // 103
        // 3
        // 102
        // false
        // 6


    }
}
public class MyDeque{
    int MX = 1000005;
    int[] deque = new int[2*MX+1];
    
    int head = MX;
    int tail = MX;

    //push_front, push_back, pop_front, pop_back, front, back
    public void push_front(int v) {
        deque[--head] = v;
    }

    public void push_back(int v) {
        deque[tail++] = v;
    }

    public boolean isEmpty() {
        return head==tail;
    }

    public int size() {
        return tail-head;
    }

    public int pop_front() {
        return isEmpty()? -1 : deque[head++];
    }

    public int pop_back() {
        return isEmpty()? -1 : deque[--tail];
    }

    public int front() {
        return isEmpty()? -1 : deque[head];
    }

    public int back() {
        return isEmpty()? -1 : deque[tail-1];
    }
}