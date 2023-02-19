package myQueue;

public class Main {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(5);
        q.push(6);
        q.push(77);
        System.out.println(q.size()); // 4 
        System.out.println(q.pop()); // 1
        System.out.println(q.front()); // 5
        System.out.println(q.back()); // 77
        
    }
}
public class MyQueue {

    // 들어갈 때는 tail 들어가고
    // 나올때는 head에서 나옴
    int[] queue = new int[1000005];
    int head, tail = 0;

    public MyQueue() {}

    public void push(int v){
        queue[tail++] = v;
    }

    public int pop() {
        return isEmpty()? -1: queue[head++];
    }
    public boolean isEmpty() {
        return (tail-head) > 0? false : true;
    }

    public int size() {
        return tail-head;
    }

    public int front() {
        return isEmpty()? -1 : queue[head];
    }
    
    public int back() {
        return isEmpty()? -1 : queue[tail-1];
    }
}
