# 테스트 케이스의 수 : t
# 주어지는 숫자 수 : n
# 정렬을 시작할 위치 : s-1
# 정렬을 끝낼 위치 : e
# 정렬 범위를 기준으로 리턴할 숫자의 위치 : k

t = int(input())

for i in range(t):
    n, s, e, k = map(int, input().split())

    num_list = list(map(int, input().split()))

    num_list = num_list[s:e]
    
    num_list.sort()

    print(f'{i} {num_list[k-1]}')