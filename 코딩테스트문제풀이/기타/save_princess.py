#주어진 배열에서 요소의 수가 1개 남을 때 까지, 매 k 번째 수를 제거한다. 

from collections import deque

#n은 왕자의 수, 반복적으로 k는 제거할 순서
def save_princess(n, k):
    
    prince = deque([i for i in range(1, n+1)])
    
    while n>1:  
        for _ in range(k-1):
            prince.append(prince.popleft())
        prince.popleft()
        n -= 1
    
    return prince[0]

print(save_princess(8,3))  