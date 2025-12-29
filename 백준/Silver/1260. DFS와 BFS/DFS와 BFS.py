# DFS와 BFS
# 그래프 기초
# dfs 알고리즘은 스택과 재귀함수를 사용하여 구현하고, 
# bfs 알고리즘은 while문과 deque를 이용하여 구현하자.(시간복잡도 낮은 popleft를 사용하자)

from collections import deque
n, m, v = map(int, input().split())

graph = [[False] * (n+1) for _ in range(n+1)]

for i in range(m):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1

visited1 = [False] * (n+1)
visited2 = [False] * (n+1)

def dfs(v):
    visited1[v] = True
    print(v, end=' ')
    
    for i in range(1, n+1):
        if visited1[i] == False and graph[v][i] == 1:
            visited1[i] = True
            dfs(i)

def bfs(v):
    q = deque([v]) # 첫번째꺼 넣고 시작
    visited2[v] = True
    while(q): # 데크에 값 없을 때까지 계속 돌아
        v = q.popleft()
        print(v, end=' ')
        for i in range(1, n+1):
            if visited2[i] == False and graph[v][i] == 1:
                q.append(i)
                visited2[i] = True          
dfs(v)
print()
bfs(v)