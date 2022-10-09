# K번째 약수
# 백준 2501

n = input()

p, q = map(int, n.split(' '))

cnt = 0

for i in range(1, p+1):
    if p % i == 0:
        cnt += 1
        
    if cnt == q:
        print(i)
        break

if cnt < q :
    print(0)