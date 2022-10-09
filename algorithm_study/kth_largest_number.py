# 주어지는 숫자 카드의 수 : n
# n개의 카드 중 랜덤한 세 카드의 합 중 k번째로 큰 수 구하기

n, k     = map(int, input().split())
num_list = list(map(int, input().split()))

answer   = set()

for i in range(n-2):
    for j in range(i+1, n-1):        
        for x in range(j+1, n):
            answer.add(num_list[i]+num_list[j]+num_list[x])

answer = list(answer).sort(reverse=True)

print(answer[k-1])

