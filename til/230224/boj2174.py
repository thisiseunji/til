import sys
from collections import deque
input = sys.stdin.readline

##bfs는 최단거리 보장 => DFS는 안그런가? 왜지?????
n, m = tuple(map(int, input().split()))
board = [list(map(int, input().strip())) for _ in range(n)]
vis = [[False for _ in range(m)] for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x,y) : # 시작지점이 x,y일 때 bfs구현
    vis[x][y] = True
    q = q.append((x,y,1)) #튜플로 더하자. 마지막 원소는 거리값(원점도 +1하기 때문에 1로 넣어줌)
    while(q.len()!=0): #큐에 원소가 있는 동안 반복
        cur = q.popleft() #(x, y, dis) 가장 앞의 원소 꺼내 담기
        if cur[0] == (n-1) and cur[1] == (m-1) : # 범위의 끝일 때
            return cur[2] #최단거리
        for i in range(4):
            newX = cur[0]+dx[i]
            newY = cur[1]+dy[i] #사방을 확인해서 방문 해야하는 곳이면 vis check후에 append()
            if newX < 0 or n < newX or newY < 0 or m < newY : continue #board의 범위를 벗어나면 skip
            if vis[newX][newY] == True or board[newX][newY] == 0 : continue #방문 할 필요가 없으면 skip
            vis[newX][newY] == True ## 방문표시하고
            q.append((newX, newY, cur[2]+1)) ## q에 추가, 거리 : 현재 위치 +1(인접한 애들만 간다.)
            
bfs(0,0)

#런타임 에러 나는데 왜 나지?