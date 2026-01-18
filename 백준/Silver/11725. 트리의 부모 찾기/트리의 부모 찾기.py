import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
graph = [[0] for _ in range(n+1)]

for _ in range(n-1):
    a,b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
# for g in graph:
#     print(g)
parent = [0]*(n+1)

parent[1] = 0
q = deque()
q.append(1)

while q:
    current = q.popleft()

    for v in graph[current]:
        if parent[v] == 0:
            parent[v] = current
            q.append(v)


for i in range(2,n+1):
    print(parent[i])
